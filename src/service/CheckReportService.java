package service;

import Until.DBConnection;
import dao.CheckReportDao;
import dao.CheckReportDaoImpl;
import entity.CheckReport;

import java.util.ArrayList;

/**
 * 考勤
 *
 * @author 10303
 */
public class CheckReportService implements CheckReportDao {

    private DBConnection dbconn = null;
    private CheckReportDaoImpl dao = null;

    public CheckReportService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new CheckReportDaoImpl(this.dbconn.getConnection());
    }

    //获得全部的考勤记录
    @Override
    public ArrayList<CheckReport> getAll(String s) throws Exception {
        ArrayList<CheckReport> checkReports = null;
        try {
            checkReports = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return checkReports;
    }

    //根据id获得一个考勤记录
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

    //根据条件搜索考勤记录
    public ArrayList<CheckReport> search(String str, String startTime, String endTime) throws Exception {
        ArrayList<CheckReport> checkReports = null;
        try {
            checkReports = this.dao.search(str, startTime, endTime);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return checkReports;
    }

    //将早上的打卡或补卡记录添加到考勤表中
    @Override
    public void addAttendanceStartTime(String code, String date, String startOrEnd) throws Exception {
        this.dao.addAttendanceStartTime(code, date, startOrEnd);
    }

    //将下午的打卡或补卡记录添加到考勤表中
    @Override
    public void addAttendanceEndTime(String code, String date, String startOrEnd) throws Exception {
        this.dao.addAttendanceEndTime(code, date, startOrEnd);

    }

    //补卡记录若删除，同时删除考勤记录中的补卡时间
    @Override
    public void deleteAttendanceTime(String code, String date, String startOrEnd) throws Exception {
        this.dao.deleteAttendanceTime(code, date, startOrEnd);

    }
}
