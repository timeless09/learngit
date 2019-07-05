package service;

import Until.DBConnection;
import dao.PunchCardDao;
import dao.PunchCardDaoImpl;
import entity.PunchCard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 打卡
 *
 * @author zzj
 */
public class PunchCardService implements PunchCardDao {

    private DBConnection dbconn = null;
    private PunchCardDao dao = null;

    public PunchCardService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new PunchCardDaoImpl(this.dbconn.getConnection()) {
        };
    }

    //根据条件搜索打卡记录
    @Override
    public ArrayList<PunchCard> getAll(String s) throws Exception {
        ArrayList<PunchCard> punchcards = null;
        try {
            punchcards = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return punchcards;
    }

    //根据id获得一个打卡记录
    @Override
    public PunchCard get(int id) throws Exception {
        PunchCard punchcard = null;
        try {
            punchcard = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return punchcard;
    }

    @Override
    public void update(PunchCard t) throws Exception {
    }

    //添加打卡记录
    @Override
    public void add(PunchCard punchard) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        CheckReportService checkReportService = new CheckReportService();
        try {
            this.dao.add(punchard);
            System.out.println(punchard.getPunchCard_Date().split(" |\\.")[1]);
            Date time = sdf.parse(punchard.getPunchCard_Date().split(" |\\.")[1]);
            String code = punchard.getPunchCard_Person_Code();
            String date = punchard.getPunchCard_Date();
            if (time.before(sdf.parse("14:00:00"))) {
                checkReportService.addAttendanceStartTime(code, date, "CheckReport_End_Time");
            } else {
                checkReportService.addAttendanceEndTime(code, date, "CheckReport_Start_Time");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
    }

}
