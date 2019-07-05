package dao;

import entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 个人信息
 * @author 10303
 */
public class PersonalInfoDaoImpl implements PersonalInfoDao {

    private Connection dbconn;
    private PreparedStatement pstmt = null;

    public PersonalInfoDaoImpl(Connection dbconn) {
        this.dbconn = dbconn;
    }

    /**
     * 
     * @param username 用户名
     * @return 获得当前用户的对象
     * @throws Exception 
     */
    @Override
    public Employee get(String username) throws Exception{
        Employee employee = null;
        String sql = "select Employees_ID_Code,Employees_Name,Employees_Sex,Employees_Age,Employees_Nation,Employees_ID_Card,"
                + "Employees_Salary,Employees_Phone,Employees_Urgent_Name,Employees_Urgent_Phone,Employees_Station_ID,Employees_Self_Describe "
                + "from temployees where Employees_Name='"+username+"'";
        this.pstmt = this.dbconn.prepareStatement(sql);
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

}
