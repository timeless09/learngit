package dao;

import entity.Leave;
import java.util.ArrayList;

/**
 *
 * @author cleju
 */
public interface LeaveDao extends BaseDao<Leave>{
    @Override
    public ArrayList<Leave> getAll(String s) throws Exception;

    @Override
    public Leave get(int id) throws Exception;

    @Override
    public void update(Leave leave) throws Exception;

    @Override
    public void add(Leave leave) throws Exception;

    @Override
    public void delete(int id) throws Exception;
}
