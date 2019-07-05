package dao;

import entity.Department;
import java.util.ArrayList;

/**
 *
 * @author 10303
 */
public interface DepartmentDao extends BaseDao<Department> {

    @Override
    public ArrayList<Department> getAll(String s) throws Exception;

    @Override
    public Department get(int id) throws Exception;

    @Override
    public void update(Department t) throws Exception;

    @Override
    public void add(Department t) throws Exception;

    @Override
    public void delete(int id) throws Exception;
}
