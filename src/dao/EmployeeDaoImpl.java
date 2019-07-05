package dao;

import entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 员工表
 *
 * @author 10303
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection dbconn;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public EmployeeDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    /**
     * @param s 搜索的条件
     * @return 获得所需的员工集合
     * @throws Exception
     */
    @Override
    public ArrayList<Employee> getAll(String s) throws Exception {
        ArrayList<Employee> employees = null;
        Employee employee = null;
        employees = new ArrayList<>();
        String sql = "select Employees_ID,Employees_ID_Code,Employees_Name,Employees_Sex,Employees_Age,"
                + "Employees_Nation,Station_Name from temployees,tstation "
                + "where temployees.Employees_Station_ID=tstation.Station_ID "
                + "AND ( Employees_ID_Code Like '%" + s + "%' OR Employees_Name Like '%" + s + "%')";
        this.stmt = this.dbconn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);
        while (rs.next()) {
            employee = new Employee();
            employee.setEmployees_ID(rs.getInt(1));
            employee.setEmployees_ID_Code(rs.getString(2));
            employee.setEmployees_Name(rs.getString(3));
            employee.setEmployees_Sex(rs.getString(4));
            employee.setEmployees_Age(rs.getInt(5));
            employee.setEmployees_Nation(rs.getString(6));
            employee.setEmployees_Station_Name(rs.getString(7));
            employees.add(employee);
        }
        this.stmt.close();
        return employees;
    }

    /**
     * @param id 要获取的员工的id
     * @return 得到员工的对象
     * @throws Exception
     */
    @Override
    public Employee get(int id) throws Exception {
        Employee employee = null;
        String sql = "SELECT Employees_ID_Code,Employees_Name,Employees_Sex,Employees_Age,Employees_Nation,Employees_ID_Card,"
                + "Employees_Salary,Employees_Phone,Employees_Urgent_Name,Employees_Urgent_Phone,Employees_Station_ID,Employees_Self_Describe "
                + "FROM temployees WHERE Employees_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            employee = new Employee();
            employee.setEmployees_ID_Code(rs.getString(1));
            employee.setEmployees_Name(rs.getString(2));
            employee.setEmployees_Sex(rs.getString(3));
            employee.setEmployees_Age(rs.getInt(4));
            employee.setEmployees_Nation(rs.getString(5));
            employee.setEmployees_ID_Card(rs.getString(6));
            employee.setEmployees_Salary(rs.getDouble(7));
            employee.setEmployees_Phone(rs.getString(8));
            employee.setEmployees_Urgent_Name(rs.getString(9));
            employee.setEmployees_Urgent_Phone(rs.getString(10));
            employee.setEmployees_Station_ID(rs.getInt(11));
            employee.setEmployees_Self_Describe(rs.getString(12));
        }
        this.pstmt.close();
        return employee;
    }

    //更新员工
    @Override
    public void update(Employee employee) throws Exception {
        String sql = "UPDATE temployees SET Employees_Name=?,Employees_Sex=?,Employees_Age=?,"
                + "Employees_Nation=?,Employees_ID_Card=?, Employees_Salary=?,Employees_Phone=?,"
                + "Employees_Urgent_Name=?,Employees_Urgent_Phone=?,Employees_Station_ID=?,"
                + "Employees_Self_Describe=? WHERE Employees_ID_Code=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, employee.getEmployees_Name());
        this.pstmt.setString(2, employee.getEmployees_Sex());
        this.pstmt.setInt(3, employee.getEmployees_Age());
        this.pstmt.setString(4, employee.getEmployees_Nation());
        this.pstmt.setString(5, employee.getEmployees_ID_Card());
        this.pstmt.setDouble(6, employee.getEmployees_Salary());
        this.pstmt.setString(7, employee.getEmployees_Phone());
        this.pstmt.setString(8, employee.getEmployees_Urgent_Name());
        this.pstmt.setString(9, employee.getEmployees_Urgent_Phone());
        this.pstmt.setInt(10, employee.getEmployees_Station_ID());
        this.pstmt.setString(11, employee.getEmployees_Self_Describe());
        this.pstmt.setString(12, employee.getEmployees_ID_Code());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //添加员工
    @Override
    public void add(Employee employee) throws Exception {
        String sql = "INSERT INTO  tEmployees (Employees_ID_Code,Employees_Name,Employees_Sex,"
                + "Employees_Age,Employees_Nation,Employees_ID_Card,Employees_Salary,Employees_Phone,Employees_Urgent_Name,"
                + "Employees_Urgent_Phone,Employees_Station_ID,Employees_Self_Describe) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setString(1, employee.getEmployees_ID_Code());
        this.pstmt.setString(2, employee.getEmployees_Name());
        this.pstmt.setString(3, employee.getEmployees_Sex());
        this.pstmt.setInt(4, employee.getEmployees_Age());
        this.pstmt.setString(5, employee.getEmployees_Nation());
        this.pstmt.setString(6, employee.getEmployees_ID_Card());
        this.pstmt.setDouble(7, employee.getEmployees_Salary());
        this.pstmt.setString(8, employee.getEmployees_Phone());
        this.pstmt.setString(9, employee.getEmployees_Urgent_Name());
        this.pstmt.setString(10, employee.getEmployees_Urgent_Phone());
        this.pstmt.setInt(11, employee.getEmployees_Station_ID());
        this.pstmt.setString(12, employee.getEmployees_Self_Describe());
        this.pstmt.execute();
        this.pstmt.close();
    }

    //根据id删除员工
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM temployees WHERE Employees_ID=?";
        this.pstmt = this.dbconn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        this.pstmt.execute();
        this.pstmt.close();
    }

}
