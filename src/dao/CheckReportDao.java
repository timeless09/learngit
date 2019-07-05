package dao;

import entity.CheckReport;
import java.util.ArrayList;

/**
 *
 * @author 10303
 */
public interface CheckReportDao extends BaseDao<CheckReport> {

    @Override
    public ArrayList<CheckReport> getAll(String s) throws Exception;

    public void addAttendanceStartTime(String code, String date, String startOrEnd) throws Exception;

    public void addAttendanceEndTime(String code, String date, String startOrEnd) throws Exception;

    public void deleteAttendanceTime(String code, String date, String startOrEnd) throws Exception;
}
