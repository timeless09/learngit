package servlet;

import Until.Export;
import Until.GetJson;
import entity.Department;

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
import service.DepartmentService;

/**
 * 根据条件搜索并显示部门信息
 *
 * @author cleju
 */
@WebServlet(name = "SearchDepartmentServlet", urlPatterns = {"/SearchDepartmentServlet"})
public class SearchDepartmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //导出部门文件时使用的表头和数据
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Export ex = new Export();
        String[] data = {"ID", "部门编码", "部门名称", "部门负责人", "部门职责", "上级部门"};
        String[] keys = {"id", "code", "name", "leader", "duty", "superior_Name"};
        String path = "./SearchDepartments.xls";
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
            DepartmentService service = new DepartmentService();
            ArrayList<Department> departments = service.getAll(parts[1]);
            getResult(departments, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }//try_end

    }

    //将从数据库查找的结果一一存入json里，并传回前端
    public void getResult(ArrayList<Department> departments, HttpServletResponse response) throws IOException {
        json = new JSONArray();
        resp = response;
        for (Department department : departments) {
            JSONObject jo = new JSONObject();
            jo.put("id", department.getDepartment_ID());
            jo.put("code", department.getDepartment_ID_Code());
            jo.put("name", department.getDepartment_Name());
            jo.put("leader", department.getDepartment_Leader());
            jo.put("duty", department.getDepartment_Duty());
            jo.put("superior_Name", department.getDepartment_Superior_Name());
            json.add(jo);
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
