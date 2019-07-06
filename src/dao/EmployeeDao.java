package dao;

import entity.Employee;
import java.util.ArrayList;

/**
 * 员工表
 *
 * @author cleju
 */
public interface EmployeeDao extends BaseDao<Employee> {

    //根据搜索条件获取员工的集合
    @Override
    public ArrayList<Employee> getAll(String s) throws Exception;

    //根据id获取部门信息
    @Override
    public Employee get(int id) throws Exception;

    //更新员工
    @Override
    public void update(Employee employee) throws Exception;

    //添加员工
    @Override
    public void add(Employee employee) throws Exception;

    //删除员工
    @Override
    public void delete(int id) throws Exception;
}
