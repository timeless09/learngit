package dao;

import entity.PunchCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 打卡表
 *
 * @author 10303
 */
public class PunchCardDaoImpl implements PunchCardDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    public static ArrayList<PunchCard> punchcards = null;

    public PunchCardDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    /**
     * 根据条件搜索打卡记录
     *
     * @param s 搜索的条件，打卡人的编码或姓名
     * @return 返回所需的打卡集合
     * @throws Exception
     */
    @Override
    public ArrayList<PunchCard> getAll(String s) throws Exception {
        PunchCard punchcard = null;
        punchcards = new ArrayList<>();
        String sql = "SELECT PunchCard_ID,PunchCard_Person_Code,Employees_Name,PunchCard_Date,PunchCard_Note "
                + "FROM tpunchcard,temployees WHERE tpunchcard.PunchCard_Person_Code = temployees.Employees_ID_Code"
                + " AND ( PunchCard_Person_Code Like '%" + s + "%' OR Employees_Name Like '%" + s + "%')";
        this.stmt = this.dbconn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);

        while (rs.next()) {
            punchcard = new PunchCard();
            punchcard.setPunchCard_ID(rs.getInt(1));
            punchcard.setPunchCard_Person_Code(rs.getString(2));
            punchcard.setPunchCard_Person_Name(rs.getString(3));
            punchcard.setPunchCard_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp(4)));
            punchcard.setPunchCard_Note(rs.getString(5));
            punchcards.add(punchcard);
        }
        this.stmt.close();
        return punchcards;
    }

    /**
     * 根据id获得打卡记录
     *
     * @param id 要获取打卡记录的id
     * @return 返回打卡记录
     * @throws Exception
     */
    @Override
    public PunchCard get(int id) throws Exception {
        PunchCard punchcard = null;
        String sql = "SELECT Employees_Name,PunchCard_Date,PunchCard_Note "
                + "FROM tpunchcard,temployees"
                + " WHERE tpunchcard.PunchCard_Person_Code = temployees.Employees_ID_Code AND PunchCard_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            punchcard = new PunchCard();
            punchcard.setPunchCard_Person_Name(rs.getString(1));
            punchcard.setPunchCard_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp(2)));
            punchcard.setPunchCard_Note(rs.getString(3));
        }
        this.pstmt.close();
        return punchcard;
    }

    @Override
    public void update(PunchCard t) throws Exception {
    }

    /**
     * 添加打卡记录
     *
     * @param punchcard 添加打卡记录的对象
     * @throws Exception
     */
    @Override
    public void add(PunchCard punchcard) throws Exception {
        String sql = "INSERT INTO tpunchcard (PunchCard_Person_Code,PunchCard_Date,PunchCard_Note) VALUES(?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, punchcard.getPunchCard_Person_Code());
        this.pstmt.setString(2, punchcard.getPunchCard_Date());
        this.pstmt.setString(3, punchcard.getPunchCard_Note());
        this.pstmt.execute();
        this.pstmt.close();
    }

    @Override
    public void delete(int id) throws Exception {
    }

}
