package service;

import Until.DBConnection;
import dao.DepartmentDao;
import dao.DepartmentDaoImpl;
import entity.Department;
import java.util.ArrayList;

/**
 * 部门
 *
 * @author 10303
 */
public class DepartmentService implements DepartmentDao {

    private DBConnection dbconn = null;
    private DepartmentDaoImpl dao = null;

    public DepartmentService() throws Exception {
        this.dbconn = new DBConnection();
        this.dao = new DepartmentDaoImpl(this.dbconn.getConnection());
    }

    //根据条件搜索部门信息
    @Override
    public ArrayList<Department> getAll(String s) throws Exception {
        ArrayList<Department> departments = null;
        try {
            departments = this.dao.getAll(s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return departments;
    }

    //根据id获得一个部门信息
    @Override
    public Department get(int id) throws Exception {
        Department departments = null;
        try {
            departments = this.dao.get(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
        return departments;
    }

    //更新部门
    @Override
    public void update(Department departments) throws Exception {
        try {
            this.dao.update(departments);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //添加部门
    @Override
    public void add(Department departments) throws Exception {
        try {
            if (departments.getDepartment_ID() >= 0) {
                this.dao.update(departments);
            } else {
                this.dao.add(departments);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbconn.close();
        }
    }

    //删除部门
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
