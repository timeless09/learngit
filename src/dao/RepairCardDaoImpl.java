package dao;

import entity.RepairCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 补卡表
 *
 * @author 10303
 */
public class RepairCardDaoImpl implements RepairCardDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public RepairCardDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    /**
     * 根据条件搜索补卡记录
     *
     * @param s 搜索的条件，补卡人的编码或姓名
     * @return 返回所有的补卡人集合
     * @throws Exception
     */
    @Override
    public ArrayList<RepairCard> getAll(String s) throws Exception {
        ArrayList<RepairCard> repairCards = null;
        RepairCard repairCard = null;
        repairCards = new ArrayList<>();
        String sql = "select Repair_ID,Repair_Person_Code,Employees_Name,Repair_Date,Repair_Reason "
                + "from temployees,trepaircrad where Employees_ID_Code=Repair_Person_Code"
                + " AND ( Repair_Person_Code Like '%" + s + "%' OR Employees_Name Like '%" + s + "%')";
        this.pstmt = this.dbconn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            repairCard = new RepairCard();
            repairCard.setRepair_ID(rs.getInt("Repair_ID"));
            repairCard.setRepair_Person_Code(rs.getString("Repair_Person_Code"));
            repairCard.setRepair_Person_Name(rs.getString("Employees_Name"));
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("Repair_Date"));
            repairCard.setRepair_Date(timeStamp);
            repairCard.setRepair_Reason(rs.getString("Repair_Reason"));
            repairCards.add(repairCard);
        }
        this.pstmt.close();
        return repairCards;
    }

    /**
     * @param id 补卡的id
     * @return 获得根据补卡id返回的对象
     * @throws Exception
     */
    @Override
    public RepairCard get(int id) throws Exception {
        RepairCard repairCard = null;
        String sql = "SELECT te.Employees_Name,tr.Repair_Date,tr.Repair_Reason,tr.Repair_Person_Code FROM temployees te,"
                + "trepaircrad tr WHERE tr.Repair_ID=? AND te.Employees_ID_Code=tr.Repair_Person_Code";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            repairCard = new RepairCard();
            repairCard.setRepair_Person_Name(rs.getString(1));
            repairCard.setRepair_Date(rs.getString(2));
            repairCard.setRepair_Reason(rs.getString(3));
            repairCard.setRepair_Person_Code(rs.getString(4));
        }
        this.pstmt.close();
        return repairCard;
    }

    //更新补卡信息
    @Override
    public void update(RepairCard repairCard) throws Exception {
        String sql = "UPDATE trepaircrad SET Repair_Person_Code=?,Repair_Date=?,Repair_Reason=? "
                + "WHERE Repair_ID = ? ";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, repairCard.getRepair_Person_Code());
        this.pstmt.setString(2, repairCard.getRepair_Date());
        this.pstmt.setString(3, repairCard.getRepair_Reason());
        this.pstmt.setInt(4, repairCard.getRepair_ID());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //添加补卡信息
    @Override
    public void add(RepairCard repairCard) throws Exception {
        String sql = "INSERT INTO  trepaircrad (Repair_Person_Code,Repair_Date,Repair_Reason) VALUES(?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, repairCard.getRepair_Person_Code());
        this.pstmt.setString(2, repairCard.getRepair_Date());
        this.pstmt.setString(3, repairCard.getRepair_Reason());

        this.pstmt.execute();
        this.pstmt.close();
    }

    //删除补卡信息
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM trepaircrad WHERE Repair_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
        this.pstmt.close();
    }

}
