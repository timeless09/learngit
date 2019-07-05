package servlet;

import Until.GetJson;
import entity.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import service.PersonalInfoService;

/**
 * 个人信息
 *
 * @author cleju
 */
@WebServlet(name = "PersonalInfoServlet", urlPatterns = {"/PersonalInfoServlet"})
public class PersonalInfoServlet extends HttpServlet {

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
        //调用工具类GetJson将前端传回的json转换为String
        String str = GetJson.getJson(request);
        str = URLDecoder.decode(str, "UTF-8");
        System.out.println(str);
        String part[] = str.split("=");
        String username = part[1];
        try {
            PersonalInfoService service = new PersonalInfoService();

            Employee employee = service.get(username);
            JSONObject jo = new JSONObject();
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
