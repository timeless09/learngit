package servlet;

import Until.GetJson;
import entity.Employee;
import Until.StringSegmentation;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmployeeService;

/**
 * 添加或更新员工
 *
 * @author 10958
 */
@WebServlet("/AddEmployeesServlet")
public class AddEmployeesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //调用工具类GetJson将前端传回的json转换成String
        String str = GetJson.getJson(request);
        System.out.println(URLDecoder.decode(str, "UTF-8"));
        ArrayList s = StringSegmentation.update(str);

        //如果年龄或薪水为空值，设置默认值
        if (s.get(3) == "") {
            s.set(3, 0);
        }
        if (s.get(6) == "") {
            s.set(6, 0);
        }
        //将字符串进行分割，然后把属性添加到对象中进行增加或更新
        Employee employee = new Employee();
        employee.setEmployees_ID_Code(s.get(0).toString());
        employee.setEmployees_Name(s.get(1).toString());
        employee.setEmployees_Sex(s.get(2).toString());
        employee.setEmployees_Age(Integer.parseInt(s.get(3).toString()));
        employee.setEmployees_Nation(s.get(4).toString());
        employee.setEmployees_ID_Card(s.get(5).toString());
        employee.setEmployees_Salary(Double.parseDouble(s.get(6).toString()));
        employee.setEmployees_Phone(s.get(7).toString());
        employee.setEmployees_Urgent_Name(s.get(8).toString());
        employee.setEmployees_Urgent_Phone(s.get(9).toString());
        employee.setEmployees_Station_ID(Integer.parseInt(s.get(10).toString()));
        employee.setEmployees_Self_Describe(s.get(11).toString());
        employee.setEmployees_ID(Integer.parseInt(s.get(12).toString()));

        try {
            EmployeeService addEmployee = new EmployeeService();
            addEmployee.add(employee);
        } catch (Exception ex) {
            Logger.getLogger(AddEmployeesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
