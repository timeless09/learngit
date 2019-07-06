package dao;

import entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 部门表
 *
 * @author 10303
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;

    public DepartmentDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    /**
     * 根据条件获取部门的集合
     *
     * @param s 部门的名称或者编码
     * @return 所需的部门的集合
     * @throws Exception
     */
    @Override
    public ArrayList<Department> getAll(String s) throws Exception {
        ArrayList<Department> al = new ArrayList<>();
        Department department = null;
        String sql = "select c1.Department_ID,c1.Department_ID_Code,c1.Department_Name,c1.Department_Leader,"
                + "c1.Department_Duty,c2.Department_Name as Department_Superior_Name from tdepartment c1, tdepartment c2 "
                + "where c2.Department_ID=c1.Department_Superior_ID"
                + " AND ( c1.Department_ID_Code Like '%" + s + "%' OR c1.Department_Name Like '%" + s + "%')";
        this.pstmt = this.dbconn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            department = new Department();
            department.setDepartment_ID(rs.getInt("Department_ID"));
            department.setDepartment_ID_Code(rs.getString("Department_ID_Code"));
            department.setDepartment_Name(rs.getString("Department_Name"));
            department.setDepartment_Leader(rs.getString("Department_Leader"));
            department.setDepartment_Duty(rs.getString("Department_Duty"));
            department.setDepartment_Superior_Name(rs.getString("Department_Superior_Name"));
            al.add(department);
        }
        this.pstmt.close();
        return al;
    }

    /**
     * 根据id获得部门信息
     *
     * @param id 要获得的部门的id
     * @return 得到部门的对象
     * @throws Exception
     */
    @Override
    public Department get(int id) throws Exception {
        Department department = null;
        String sql = "SELECT c1.Department_ID,c1.Department_ID_Code,c1.Department_Name,c1.Department_Leader,"
                + " c1.Department_Duty,c2.Department_Name AS Department_Superior_Name FROM tdepartment c1, tdepartment c2 "
                + "WHERE c2.Department_ID=c1.Department_Superior_ID AND c1.Department_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            department = new Department();
            department.setDepartment_ID_Code(rs.getString("Department_ID_Code"));
            department.setDepartment_Name(rs.getString("Department_Name"));
            department.setDepartment_Leader(rs.getString("Department_Leader"));
            department.setDepartment_Duty(rs.getString("Department_Duty"));
            department.setDepartment_Superior_Name(rs.getString("Department_Superior_Name"));
        }
        this.pstmt.close();
        return department;
    }

    /**
     * 更新部门
     *
     * @param department 部门
     * @throws Exception
     */
    @Override
    public void update(Department department) throws Exception {
        String sql = "UPDATE tdepartment SET Department_ID_Code=?,Department_Name=?,Department_Leader=?,"
                + "Department_Duty=?,Department_Superior_ID=? WHERE Department_ID=?";

        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, department.getDepartment_ID_Code());
        this.pstmt.setString(2, department.getDepartment_Name());
        this.pstmt.setString(3, department.getDepartment_Leader());
        this.pstmt.setString(4, department.getDepartment_Duty());
        this.pstmt.setInt(5, department.getDepartment_Superior_ID());
        this.pstmt.setInt(6, department.getDepartment_ID());
        System.out.println(sql);
        this.pstmt.execute();
        this.pstmt.close();
    }

    /**
     * 添加部门
     *
     * @param department
     * @throws Exception
     */
    @Override
    public void add(Department department) throws Exception {
        String sql = "INSERT INTO tdepartment (Department_ID_Code,Department_Name,Department_Leader,"
                + "Department_Duty,Department_Superior_ID)"
                + " VALUES(?,?,?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, department.getDepartment_ID_Code());
        this.pstmt.setString(2, department.getDepartment_Name());
        this.pstmt.setString(3, department.getDepartment_Leader());
        this.pstmt.setString(4, department.getDepartment_Duty());
        this.pstmt.setInt(5, department.getDepartment_Superior_ID());
        this.pstmt.execute();
        this.pstmt.close();
    }

    /**
     * 根据id删除部门
     *
     * @param id 要删除部门的id
     * @throws Exception
     */
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM tdepartment WHERE Department_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
        this.pstmt.close();
    }

}
