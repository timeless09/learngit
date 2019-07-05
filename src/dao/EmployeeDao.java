package dao;

import entity.Employee;
import java.util.ArrayList;

/**
 *
 * @author cleju
 */
public interface EmployeeDao extends BaseDao<Employee>{
    @Override
    public ArrayList<Employee> getAll(String s) throws Exception;
    @Override
    public Employee get(int id) throws Exception;
    @Override
    public void update(Employee employee) throws Exception;
    @Override
    public void add(Employee employee) throws Exception;
}
