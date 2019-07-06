package dao;

import entity.Department;
import java.util.ArrayList;

/**
 * 部门表
 *
 * @author 10303
 */
public interface DepartmentDao extends BaseDao<Department> {

    //根据条件搜索获取部门的集合
    @Override
    public ArrayList<Department> getAll(String s) throws Exception;

    //根据id获得部门信息
    @Override
    public Department get(int id) throws Exception;

    //更新部门
    @Override
    public void update(Department t) throws Exception;

    //添加部门
    @Override
    public void add(Department t) throws Exception;

    //根据id删除部门
    @Override
    public void delete(int id) throws Exception;
}
