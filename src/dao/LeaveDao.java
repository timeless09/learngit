package dao;

import entity.Leave;
import java.util.ArrayList;

/**
 * 请假表
 *
 * @author cleju
 */
public interface LeaveDao extends BaseDao<Leave> {

    //根据条件获得请假表的集合
    @Override
    public ArrayList<Leave> getAll(String s) throws Exception;

    //根据id获得请假记录
    @Override
    public Leave get(int id) throws Exception;

    //更新请假记录
    @Override
    public void update(Leave leave) throws Exception;

    //添加请假记录
    @Override
    public void add(Leave leave) throws Exception;

    //根据id删除请假记录
    @Override
    public void delete(int id) throws Exception;
}
