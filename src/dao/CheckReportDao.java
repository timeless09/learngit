package dao;

import entity.CheckReport;
import java.util.ArrayList;

/**
 * 考勤表
 *
 * @author 10303
 */
public interface CheckReportDao extends BaseDao<CheckReport> {

    //根据条件获取所需的考勤员工的集合
    @Override
    public ArrayList<CheckReport> getAll(String s) throws Exception;

    //将是早上的打卡或补卡记录插入或更新到考勤表中
    public void addAttendanceStartTime(String code, String date, String startOrEnd) throws Exception;

    //将是下午的打卡或补卡记录插入或更新到考勤表中
    public void addAttendanceEndTime(String code, String date, String startOrEnd) throws Exception;

    //补卡记录删除时，同时删除attendance中的补卡记录
    public void deleteAttendanceTime(String code, String date, String startOrEnd) throws Exception;
}
