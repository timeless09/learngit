package servlet;

import Until.GetJson;
import entity.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import service.EmployeeService;

/**
 * 通过id获得员工
 *
 * @author 10958
 */
@WebServlet(name = "GetEmployeeServlet", urlPatterns = {"/GetEmployeeServlet"})
public class GetEmployeeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //调用工具类GetJson将前端传回的json转换为String
        String str = GetJson.getJson(request);
        System.out.println(str);
        String part[] = str.split("=");
        int id = Integer.parseInt(part[1]);
        System.out.println(id);
        try {
            EmployeeService service = new EmployeeService();

            Employee employee = service.get(id);

            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("code", employee.getEmployees_ID_Code());
            jo.put("name", employee.getEmployees_Name());
            jo.put("sex", employee.getEmployees_Sex());
            jo.put("age", employee.getEmployees_Age());
            jo.put("nation", employee.getEmployees_Nation());

            jo.put("idCard", employee.getEmployees_ID_Card());
            jo.put("salary", employee.getEmployees_Salary());
            jo.put("phone", employee.getEmployees_Phone());
            jo.put("urgentName", employee.getEmployees_Urgent_Name());
            jo.put("urgentPhone", employee.getEmployees_Urgent_Phone());
            jo.put("stationID", employee.getEmployees_Station_ID());
            jo.put("selfDes", employee.getEmployees_Self_Describe());

            System.out.println(jo);

            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(jo.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
