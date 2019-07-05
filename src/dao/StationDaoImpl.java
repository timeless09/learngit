package dao;

import entity.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 岗位表
 *
 * @author 10303
 */
public class StationDaoImpl implements StationDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public StationDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    //根据条件搜索所需的岗位信息
    @Override
    public ArrayList<Station> getAll(String s) throws Exception {
        ArrayList<Station> stations = null;
        Station station = null;
        stations = new ArrayList<>();
        String sql = "select Station_ID,Station_Code,Station_Name,Station_Department,Station_Superior,Station_Category"
                + " from tstation where  Station_Code Like '%" + s + "%' OR Station_Name Like '%" + s + "%'";
        this.stmt = this.dbconn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);
        while (rs.next()) {
            station = new Station();
            station.setStation_ID(rs.getInt(1));
            station.setStation_Code(rs.getString(2));
            station.setStation_Name(rs.getString(3));
            station.setStation_Department(rs.getString(4));
            station.setStation_Superior(rs.getString(5));
            station.setStation_Category(rs.getString(6));
            stations.add(station);
        }
        this.stmt.close();
        return stations;
    }

    //根据id获得岗位
    @Override
    public Station get(int id) throws Exception {
        Station station = null;
        String sql = "select Station_Code,Station_Name,Station_Department,Station_Superior,Station_Category,Station_Describe"
                + " from tstation where Station_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            station = new Station();
            station.setStation_Code(rs.getString(1));
            station.setStation_Name(rs.getString(2));
            station.setStation_Department(rs.getString(3));
            station.setStation_Superior(rs.getString(4));
            station.setStation_Category(rs.getString(5));
            station.setStation_Describe(rs.getString(6));
        }
        this.pstmt.close();
        return station;
    }

    //更新岗位信息
    @Override
    public void update(Station station) throws Exception {
        String sql = "update tstation set Station_Code=?,Station_Name=?,Station_Department=?,"
                + "Station_Superior=?,Station_Category=?,Station_Describe=?"
                + " where Station_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, station.getStation_Code());
        this.pstmt.setString(2, station.getStation_Name());
        this.pstmt.setString(3, station.getStation_Department());
        this.pstmt.setString(4, station.getStation_Superior());
        this.pstmt.setString(5, station.getStation_Category());
        this.pstmt.setString(6, station.getStation_Describe());
        this.pstmt.setInt(7, station.getStation_ID());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //添加岗位
    @Override
    public void add(Station station) throws Exception {
        String sql = "insert into tstation (Station_Code,Station_Name,Station_Department,Station_Superior,Station_Category,Station_Describe)"
                + " values(?,?,?,?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, station.getStation_Code());
        this.pstmt.setString(2, station.getStation_Name());
        this.pstmt.setString(3, station.getStation_Department());
        this.pstmt.setString(4, station.getStation_Superior());
        this.pstmt.setString(5, station.getStation_Category());
        this.pstmt.setString(6, station.getStation_Describe());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //根据id删除岗位
    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from tstation where Station_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
        this.pstmt.close();
    }

}
