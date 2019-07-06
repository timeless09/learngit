package dao;

import entity.CheckReport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 考勤表
 *
 * @author 10303
 */
public class CheckReportDaoImpl implements CheckReportDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CheckReportDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    //根据条件获取所需的考勤员工的集合
    @Override
    public ArrayList<CheckReport> getAll(String s) throws Exception {
        ArrayList<CheckReport> checkReports = null;
        CheckReport checkReport = null;
        String start;
        String end;
        checkReports = new ArrayList<>();
        String sql = "SELECT CheckReport_ID,CheckReport_Employee_Code,Employees_Name,"
                + "CheckReport_Start_Time,CheckReport_End_Time "
                + "FROM tattendance,temployees WHERE Employees_ID_Code=CheckReport_Employee_Code ";
        this.pstmt = this.dbconn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();

        while (rs.next()) {
            checkReport = new CheckReport();
            checkReport.setCheckReport_ID(rs.getInt(1));
            checkReport.setCheckReport_Employee_Code(rs.getString(2));
            checkReport.setCheckReport_Employee_Name(rs.getString(3));
            if (rs.getTimestamp(4) == null) {
                checkReport.setCheckReport_Start_Time("未知");
            } else {
                start = sdf.format(rs.getTimestamp(4));
                checkReport.setCheckReport_Start_Time(start);
            }
            if (rs.getTimestamp(5) == null) {
                checkReport.setCheckReport_End_Time("未知");
            } else {
                end = sdf.format(rs.getTimestamp(5));
                checkReport.setCheckReport_End_Time(end);
            }

            checkReports.add(checkReport);
        }
        this.pstmt.close();
        return checkReports;
    }

    @Override
    public CheckReport get(int id) throws Exception {
        return null;
    }

    @Override
    public void update(CheckReport t) throws Exception {
    }

    @Override
    public void add(CheckReport t) throws Exception {
    }

    @Override
    public void delete(int id) throws Exception {
    }

    /**
     * 将是早上的打卡或补卡记录插入或更新到考勤表中
     *
     * @param code 员工的编码
     * @param date 输入的时间
     * @param startOrEnd 早上或者是下午
     * @throws Exception
     */
    @Override
    public void addAttendanceStartTime(String code, String date, String startOrEnd) throws Exception {
        boolean flag = attendanceTime(code, date, startOrEnd);
        String sql;
        if (flag) {
            sql = "update tattendance set CheckReport_Employee_Code=?,CheckReport_Start_Time=?"
                    + " WHERE CheckReport_Employee_Code='" + code + "' "
                    + "AND TO_DAYS(CheckReport_End_Time)=TO_DAYS('" + date + "')";
        } else {
            sql = "INSERT into tattendance(CheckReport_Employee_Code,CheckReport_Start_Time) VALUES(?,?)";
        }
        System.out.println("attendance:" + sql);
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, code);
        this.pstmt.setString(2, date);
        System.out.println(sql);
        this.pstmt.execute();
        this.pstmt.close();
    }

    /**
     * 将是下午的打卡或补卡记录插入或更新到考勤表中
     *
     * @param code 员工的编码
     * @param date 输入的时间
     * @param startOrEnd 早上或者是下午
     * @throws Exception
     */
    @Override
    public void addAttendanceEndTime(String code, String date, String startOrEnd) throws Exception {
        boolean flag = attendanceTime(code, date, startOrEnd);
        String sql;
        if (flag) {
            sql = "update tattendance set CheckReport_Employee_Code=?,CheckReport_End_Time=?"
                    + " WHERE CheckReport_Employee_Code='" + code + "' "
                    + "AND TO_DAYS(CheckReport_Start_Time)=TO_DAYS('" + date + "')";
        } else {
            sql = "INSERT into tattendance(CheckReport_Employee_Code,CheckReport_End_Time) VALUES(?,?)";
        }
        System.out.println("attendance:" + sql);
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, code);
        this.pstmt.setString(2, date);
        System.out.println(sql);
        this.pstmt.execute();
        this.pstmt.close();
    }

    /*
    *判断是否存在该用户今天的打卡记录
     */
    public boolean attendanceTime(String code, String date, String startOrEnd) throws Exception {
        boolean flag = false;
        String sql = "select count(CheckReport_Employee_Code) rowCount from tattendance "
                + "where CheckReport_Employee_Code=? "
                + "and TO_DAYS(" + startOrEnd + ")=TO_DAYS(?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, code);
        this.pstmt.setString(2, date);
        System.out.println(sql);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("rowCount");
            System.out.println(count);
            System.out.println(rs.getInt("rowCount"));
            if (count == 1) {
                flag = true;
            }
        }
        System.out.println("flag:" + flag);
        return flag;
    }

    //补卡记录删除时，同时删除attendance中的补卡记录
    @Override
    public void deleteAttendanceTime(String code, String date, String startOrEnd) throws Exception {
        String sql = "update tattendance set " + startOrEnd + "=null "
                + "WHERE CheckReport_Employee_Code=? AND " + startOrEnd + "=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, code);
        this.pstmt.setString(2, date);
        System.out.println("ddddddd:" + sql);
        this.pstmt.execute();
        this.pstmt.close();
    }

    /**
     *
     * @param str 搜索输入的员工的编码或姓名
     * @param startTime 搜索的开始时间
     * @param endTime 搜索的结束时间
     * @return 返回一个员工的集合
     * @throws Exception
     */
    public ArrayList<CheckReport> search(String str, String startTime, String endTime) throws Exception {
        ArrayList<CheckReport> checkReports = null;
        CheckReport checkReport = null;
        String start;
        String end;
        checkReports = new ArrayList<>();
        String sql = "SELECT CheckReport_ID,CheckReport_Employee_Code,Employees_Name,"
                + "CheckReport_Start_Time,CheckReport_End_Time "
                + "FROM tattendance,temployees WHERE Employees_ID_Code=CheckReport_Employee_Code "
                + "AND ( CheckReport_Employee_Code Like '%" + str + "%' OR Employees_Name Like '%" + str + "%')"
                + " AND ( CheckReport_Start_Time >= '" + startTime + "' AND " + "'" + endTime + " 23:59:59' >= CheckReport_Start_Time"
                + " OR CheckReport_End_Time >= '" + startTime + "' AND " + "'" + endTime + " 23:59:59' >= CheckReport_End_Time)";
        System.out.println(sql);
        this.pstmt = this.dbconn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();

        while (rs.next()) {
            checkReport = new CheckReport();
            checkReport.setCheckReport_ID(rs.getInt(1));
            checkReport.setCheckReport_Employee_Code(rs.getString(2));
            checkReport.setCheckReport_Employee_Name(rs.getString(3));
            if (rs.getTimestamp(4) == null) {
                checkReport.setCheckReport_Start_Time("未知");
            } else {
                start = sdf.format(rs.getTimestamp(4));
                checkReport.setCheckReport_Start_Time(start);
            }
            if (rs.getTimestamp(5) == null) {
                checkReport.setCheckReport_End_Time("未知");
            } else {
                end = sdf.format(rs.getTimestamp(5));
                checkReport.setCheckReport_End_Time(end);
            }
            checkReports.add(checkReport);
        }
        this.pstmt.close();
        return checkReports;
    }

}
