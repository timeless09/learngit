package servlet;

import Until.Export;
import Until.GetJson;
import entity.PaySalary;

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
import service.PaySalaryService;

/**
 * 根据条件搜索并显示派薪记录
 *
 * @author 10303
 */
@WebServlet(name = "SearchPaySalaryServlet", urlPatterns = {"/SearchPaySalaryServlet"})
public class SearchPaySalaryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //导出领薪文件时使用的表头和数据
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Export ex = new Export();
        String[] data = {"ID", "领薪人编码", "领薪人姓名", "薪水", "计算开始日期", "计算结束日期"};
        String[] keys = {"id", "code", "name", "salary", "start", "end"};
        String path = "./SearchPaySalarys.xls";
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
            PaySalaryService service = new PaySalaryService();
            ArrayList<PaySalary> paySalarys = service.getAll(parts[1]);
            getResult(paySalarys, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }//try_end

    }

    //将从数据库查找的结果一一存入json里，并传回前端
    public void getResult(ArrayList<PaySalary> paySalarys, HttpServletResponse response) throws IOException {
        json = new JSONArray();
        resp = response;
        for (PaySalary paySalary : paySalarys) {
            JSONObject jo = new JSONObject();
            jo.put("id", paySalary.getSalary_ID());
            jo.put("code", paySalary.getSalary_Person_Code());
            jo.put("name", paySalary.getSalary_Person_Name());
            jo.put("salary", paySalary.getSalary());
            jo.put("start", paySalary.getSalary_Start_Time());
            jo.put("end", paySalary.getSalary_End_Time());
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
