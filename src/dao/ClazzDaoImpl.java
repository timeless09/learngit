package dao;

import entity.Clazz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 班次表
 *
 * @author 10303
 */
public class ClazzDaoImpl implements ClazzDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public ClazzDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    /**
     * 根据班次的名称或者编码获取所需的班次
     *
     * @param s 班次的名称或者编码
     * @return 所需的班次列表
     * @throws Exception
     */
    @Override
    public ArrayList<Clazz> getAll(String s) throws Exception {
        ArrayList<Clazz> clazzs = null;
        Clazz clazz = null;
        clazzs = new ArrayList<>();
        String sql = "select Class_ID,Class_Code,Class_Name,Class_Start_Time,Class_End_Time from tclasses"
                + " where Class_Code Like '%" + s + "%' OR Class_Name Like '%" + s + "%'";
        this.stmt = this.dbconn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);
        while (rs.next()) {
            clazz = new Clazz();
            clazz.setClass_ID(rs.getInt(1));
            clazz.setClass_Code(rs.getString(2));
            clazz.setClass_Name(rs.getString(3));
            clazz.setClass_Start_Time(rs.getString(4));
            clazz.setClass_End_Time(rs.getString(5));
            clazzs.add(clazz);
        }
        this.stmt.close();
        return clazzs;
    }

    /**
     * @param id 班次id
     * @return 根据id获取班次信息
     * @throws Exception
     */
    @Override
    public Clazz get(int id) throws Exception {
        Clazz clazz = null;
        String sql = "SELECT Class_Code,Class_Name,Class_Start_Time,Class_End_Time,"
                + "Class_Note FROM tclasses WHERE Class_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            clazz = new Clazz();
            clazz.setClass_Code(rs.getString(1));
            clazz.setClass_Name(rs.getString(2));
            clazz.setClass_Start_Time(rs.getString(3));
            clazz.setClass_End_Time(rs.getString(4));
            clazz.setClass_Note(rs.getString(4));
        }
        this.pstmt.close();
        return clazz;
    }

    /**
     * 更新班次
     *
     * @param clazz 班次对象
     * @throws Exception
     */
    @Override
    public void update(Clazz clazz) throws Exception {
        String sql = "UPDATE tclasses SET Class_Code=?,Class_Name=?,Class_Start_Time=?,"
                + "Class_End_Time=?,Class_Note=? WHERE Class_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, clazz.getClass_Code());
        this.pstmt.setString(2, clazz.getClass_Name());
        this.pstmt.setString(3, clazz.getClass_Start_Time());
        this.pstmt.setString(4, clazz.getClass_End_Time());
        this.pstmt.setString(5, clazz.getClass_Note());
        this.pstmt.setInt(6, clazz.getClass_ID());
        this.pstmt.execute();
        this.pstmt.close();
    }

    /**
     * 添加班次
     *
     * @param clazz
     * @throws Exception
     */
    @Override
    public void add(Clazz clazz) throws Exception {
        String sql = "INSERT INTO tclasses (Class_Code,Class_Name,Class_Start_Time,Class_End_Time,Class_Note)"
                + " VALUES(?,?,?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, clazz.getClass_Code());
        this.pstmt.setString(2, clazz.getClass_Name());
        this.pstmt.setString(3, clazz.getClass_Start_Time());
        this.pstmt.setString(4, clazz.getClass_End_Time());
        this.pstmt.setString(5, clazz.getClass_Note());
        this.pstmt.execute();
        this.pstmt.close();
    }

    /**
     * 删除班次
     *
     * @param id 要删除班次的id
     * @throws Exception
     */
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM tclasses WHERE Class_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
        this.pstmt.close();
    }

}
