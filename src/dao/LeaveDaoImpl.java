package dao;

import entity.Leave;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 请假表
 *
 * @author 10303
 */
public class LeaveDaoImpl implements LeaveDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public LeaveDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    /**
     * @param s 搜索的条件，请假记录中员工的编码或姓名
     * @return 获得所需的请假集合
     * @throws Exception
     */
    @Override
    public ArrayList<Leave> getAll(String s) throws Exception {
        ArrayList<Leave> leaves = null;
        Leave leave = null;
        leaves = new ArrayList<>();
        String sql = "select Leave_ID,Leave_Person_Code,Employees_Name,Leave_Start_Time,Leave_End_Time"
                + " from tleave,temployees where tleave.Leave_Person_Code = temployees.Employees_ID_Code"
                + " AND ( Leave_Person_Code Like '%" + s + "%' OR Employees_Name Like '%" + s + "%')";
        this.stmt = this.dbconn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);
        while (rs.next()) {
            leave = new Leave();
            leave.setLeave_ID(rs.getInt(1));
            leave.setLeave_Person_Code(rs.getString(2));
            leave.setLeave_Person_Name(rs.getString(3));
            String start = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp(4));
            leave.setLeave_Start_Time(start);
            String end = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp(5));
            leave.setLeave_End_Time(end);
            leaves.add(leave);
        }
        this.stmt.close();
        return leaves;
    }

    /**
     * @param id 要获得的请假的id
     * @return 所需的请假对象
     * @throws Exception
     */
    @Override
    public Leave get(int id) throws Exception {
        Leave leave = null;
        String sql = "SELECT Employees_Name,Leave_Start_Time,Leave_End_Time,Leave_Reason"
                + " FROM tleave,temployees WHERE Leave_ID=? AND tleave.Leave_Person_Code = temployees.Employees_ID_Code";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            leave = new Leave();
            leave.setLeave_Person_Name(rs.getString(1));
            leave.setLeave_Start_Time(rs.getString(2));
            leave.setLeave_End_Time(rs.getString(3));
            leave.setLeave_Reason(rs.getString(4));
        }
        this.pstmt.close();
        return leave;
    }

    //更新请假表
    @Override
    public void update(Leave leave) throws Exception {
        String sql = "UPDATE tleave SET Leave_Person_Code=?,Leave_Start_Time=?,Leave_End_Time=?,Leave_Reason=? WHERE Leave_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, leave.getLeave_Person_Code());
        this.pstmt.setString(2, leave.getLeave_Start_Time());
        this.pstmt.setString(3, leave.getLeave_End_Time());
        this.pstmt.setString(4, leave.getLeave_Reason());
        this.pstmt.setInt(5, leave.getLeave_ID());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //添加请假
    @Override
    public void add(Leave leave) throws Exception {
        String sql = "INSERT INTO tleave (Leave_Person_Code,Leave_Start_Time,Leave_End_Time,Leave_Reason)"
                + " VALUES(?,?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, leave.getLeave_Person_Code());
        this.pstmt.setString(2, leave.getLeave_Start_Time());
        this.pstmt.setString(3, leave.getLeave_End_Time());
        this.pstmt.setString(4, leave.getLeave_Reason());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //根据id删除请假
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM tleave WHERE Leave_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
        this.pstmt.close();
    }
}
