package dao;

import entity.PaySalary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 派薪表
 *
 * @author 10303
 */
public class PaySalaryDaoImpl implements PaySalaryDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public PaySalaryDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    //根据条件搜索派薪记录
    @Override
    public ArrayList<PaySalary> getAll(String s) throws Exception {
        ArrayList<PaySalary> paySalarys = null;
        PaySalary paySalary = null;
        paySalarys = new ArrayList<>();
        String sql = "select Salary_ID,Salary_Person_Code,Employees_Name,Salary,Salary_Start_Time, Salary_End_Time "
                + "from temployees,tpaysalary where Employees_ID_Code=Salary_Person_Code"
                + " AND ( Salary_Person_Code Like '%" + s + "%' OR Employees_Name Like '%" + s + "%')";
        this.pstmt = this.dbconn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            paySalary = new PaySalary();
            paySalary.setSalary_ID(rs.getInt("Salary_ID"));
            paySalary.setSalary_Person_Code(rs.getString("Salary_Person_Code"));
            paySalary.setSalary_Person_Name(rs.getString("Employees_Name"));
            paySalary.setSalary(rs.getDouble("Salary"));
            String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("Salary_Start_Time"));
            String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("Salary_End_Time"));
            paySalary.setSalary_Start_Time(start);
            paySalary.setSalary_End_Time(end);
            paySalarys.add(paySalary);
        }
        this.pstmt.close();
        return paySalarys;
    }

    //根据id获得一个派薪记录
    @Override
    public PaySalary get(int id) throws Exception {
        PaySalary paySalary = null;
        String sql = "SELECT te.Employees_Name,ts.Salary_Start_Time,ts.Salary_End_Time,Salary,ts.Salary_Person_Code "
                + "FROM temployees te, tpaysalary ts "
                + "WHERE ts.Salary_ID=? AND te.Employees_ID_Code=ts.Salary_Person_Code";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            paySalary = new PaySalary();
            paySalary.setSalary_Person_Name(rs.getString(1));
            paySalary.setSalary_Start_Time(rs.getString(2));
            paySalary.setSalary_End_Time(rs.getString(3));
            paySalary.setSalary(rs.getDouble(4));
            int hours = paySalaryTime(rs.getString(5));
            paySalary.setSalary_Time(hours);
        }
        this.pstmt.close();
        return paySalary;
    }

    //更新派薪记录
    @Override
    public void update(PaySalary paySalary) throws Exception {
        String sql = "UPDATE tpaysalary SET Salary_Person_Code=?,Salary=?,Salary_Start_Time=?,Salary_End_Time=? "
                + "WHERE Salary_ID = ? ";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, paySalary.getSalary_Person_Code());
        this.pstmt.setDouble(2, paySalary.getSalary());
        this.pstmt.setString(3, paySalary.getSalary_Start_Time());
        this.pstmt.setString(4, paySalary.getSalary_End_Time());
        this.pstmt.setInt(5, paySalary.getSalary_ID());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //添加派薪记录
    @Override
    public void add(PaySalary paySalary) throws Exception {
        String sql = "INSERT INTO  tpaysalary (Salary_Person_Code,Salary,Salary_Start_Time,Salary_End_Time) VALUES(?,?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, paySalary.getSalary_Person_Code());
        this.pstmt.setDouble(2, paySalary.getSalary());
        this.pstmt.setString(3, paySalary.getSalary_Start_Time());
        this.pstmt.setString(4, paySalary.getSalary_End_Time());

        this.pstmt.execute();
        this.pstmt.close();
    }

    //根据id删除派薪记录
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM tpaysalary WHERE Salary_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
        this.pstmt.close();
    }

    //根据考勤表查询员工工作总时间
    public int paySalaryTime(String code) throws Exception {
        int salaryTime = 0;
        String sql = "SELECT hour(timediff(CheckReport_End_Time,CheckReport_Start_Time)) FROM tattendance "
                + "WHERE CheckReport_Employee_Code=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, code);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            salaryTime += rs.getInt(1);
        }
        return salaryTime;
    }
}
