package service;

import Until.DBConnection;
import dao.RepairCardDao;
import dao.RepairCardDaoImpl;
import entity.RepairCard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 补卡记录
 *
 * @author cleju
 */
public class RepairCardService implements RepairCardDao {

    private DBConnection dbconn = null;
    private RepairCardDaoImpl dao = null;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    CheckReportService checkReportService = new CheckReportService();

    public RepairCardService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new RepairCardDaoImpl(this.dbconn.getConnection());
    }

    //根据条件搜索补卡记录
    @Override
    public ArrayList<RepairCard> getAll(String s) throws Exception {
        ArrayList<RepairCard> repairCards = null;
        try {
            repairCards = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return repairCards;
    }

    //根据id获得一个补卡记录
    @Override
    public RepairCard get(int id) throws Exception {
        RepairCard repairCard = null;
        try {
            repairCard = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        }
        return repairCard;
    }

    //更新补卡记录
    @Override
    public void update(RepairCard repairCard) throws Exception {
        try {
            this.dao.update(repairCard);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //添加补卡记录
    @Override
    public void add(RepairCard repairCard) throws Exception {
        Date time = sdf.parse(repairCard.getRepair_Date().split(" |\\.")[1]);
        String code = repairCard.getRepair_Person_Code();
        String date = repairCard.getRepair_Date();
        try {
            if (repairCard.getRepair_ID() >= 0) {
                this.dao.update(repairCard);
                if (time.before(sdf.parse("14:00:00"))) {
                    checkReportService.addAttendanceStartTime(code, date, "CheckReport_Start_Time");
                } else {
                    checkReportService.addAttendanceEndTime(code, date, "CheckReport_End_Time");
                }
            } else {
                this.dao.add(repairCard);
                if (time.before(sdf.parse("14:00:00"))) {
                    checkReportService.addAttendanceStartTime(code, date, "CheckReport_End_Time");
                } else {
                    checkReportService.addAttendanceEndTime(code, date, "CheckReport_Start_Time");
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //删除补卡记录
    @Override
    public void delete(int id) throws Exception {
        try {
            RepairCard repairCard = get(id);
            this.dao.delete(id);
            Date time = sdf.parse(repairCard.getRepair_Date().split(" |\\.")[1]);
            String code = repairCard.getRepair_Person_Code();
            String date = repairCard.getRepair_Date();
            if (time.before(sdf.parse("14:00"))) {
                checkReportService.deleteAttendanceTime(code, date, "CheckReport_Start_Time");
            } else {
                checkReportService.deleteAttendanceTime(code, date, "CheckReport_End_Time");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }
}
