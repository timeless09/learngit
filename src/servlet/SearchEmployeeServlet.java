package servlet;

import Until.Export;
import Until.GetJson;
import entity.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.EmployeeService;

/**
 * 根据条件搜索并显示员工信息
 *
 * @author 10958
 */
@WebServlet(name = "SearchEmployeeServlet", urlPatterns = {"/SearchEmployeeServlet"})
public class SearchEmployeeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //导出员工文件时使用的表头和数据
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Export ex = new Export();
        String[] data = {"ID", "员工编码", "姓名", "性别", "年龄", "民族", "岗位"};
        String[] keys = {"id", "code", "name", "sex", "age", "nation", "stationName"};
        String path = "./SearchEmployees.xls";
        ex.save(json, data, keys, path, resp);
    }

    JSONArray json;
    HttpServletResponse resp;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //调用工具类GetJson将前端传回的json转换成String
        String str = GetJson.getJson(request);
        System.out.println(URLDecoder.decode(str, "UTF-8"));

        str = URLDecoder.decode(str, "UTF-8");
        //对字符串进行分割，获得要搜索的内容
        String[] parts = str.split("=");
        System.out.println(parts[1]);
        try {
            //调用service的getAll方法，显示所需要的内容
            EmployeeService service = new EmployeeService();
            ArrayList<Employee> employees = service.getAll(parts[1]);
            getResult(employees, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }//try_end

    }

    //将从数据库查找的结果一一存入json里，并传回前端
    public void getResult(ArrayList<Employee> employees, HttpServletResponse response) throws IOException {
        json = new JSONArray();
        resp = response;
        for (Employee employee : employees) {
            JSONObject jo = new JSONObject();
            jo.put("id", employee.getEmployees_ID());
            jo.put("code", employee.getEmployees_ID_Code());
            jo.put("name", employee.getEmployees_Name());
            jo.put("sex", employee.getEmployees_Sex());
            jo.put("age", employee.getEmployees_Age());
            jo.put("nation", employee.getEmployees_Nation());
            jo.put("stationName", employee.getEmployees_Station_Name());

            json.add(jo);
        }
        System.out.println(json);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
