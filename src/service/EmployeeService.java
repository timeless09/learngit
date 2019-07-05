package service;

import Until.DBConnection;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import entity.Employee;
import java.util.ArrayList;

/**
 * 员工
 *
 * @author cleju
 */
public class EmployeeService implements EmployeeDao {

    private DBConnection dbconn = null;
    private EmployeeDaoImpl dao = null;

    public EmployeeService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new EmployeeDaoImpl(this.dbconn.getConnection());
    }

    //根据条件搜索员工
    @Override
    public ArrayList<Employee> getAll(String s) throws Exception {
        ArrayList<Employee> employees = null;
        try {
            employees = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return employees;
    }

    //根据id获得一个员工信息
    @Override
    public Employee get(int id) throws Exception {
        Employee employee = null;
        try {
            employee = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return employee;
    }

    //更新员工
    @Override
    public void update(Employee employee) throws Exception {
        try {
            this.dao.update(employee);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //添加员工
    @Override
    public void add(Employee employee) throws Exception {
        try {
            if (employee.getEmployees_ID() >= 0) {
                this.dao.update(employee);
            } else {
                this.dao.add(employee);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //删除员工
    @Override
    public void delete(int id) throws Exception {
        try {
            this.dao.delete(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }
}
