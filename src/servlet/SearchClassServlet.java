package servlet;

import Until.Export;
import Until.GetJson;
import entity.Clazz;

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
import service.ClazzService;

/**
 * 根据条件搜索并显示班次信息
 *
 * @author cleju
 */
@WebServlet(name = "SearchClassServlet", urlPatterns = {"/SearchClassServlet"})
public class SearchClassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //导出班次文件时使用的表头和数据
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Export ex = new Export();
        String[] data = {"ID", "班次编码", "班次名称", "早上上班时间", "下午下班时间"};
        String[] keys = {"id", "code", "name", "startTime", "endTime"};
        String path = "./SearchClasses.xls";
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
            ClazzService service = new ClazzService();
            ArrayList<Clazz> clazzs = service.getAll(parts[1]);
            getResult(clazzs, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }//try_end
    }

    //将从数据库查找的结果一一存入json里，并传回前端
    private void getResult(ArrayList<Clazz> clazzs, HttpServletResponse response) throws IOException {
        json = new JSONArray();
        resp = response;
        for (Clazz clazz : clazzs) {
            JSONObject jo = new JSONObject();
            jo.put("id", clazz.getClass_ID());
            jo.put("code", clazz.getClass_Code());
            jo.put("name", clazz.getClass_Name());
            jo.put("startTime", clazz.getClass_Start_Time());
            jo.put("endTime", clazz.getClass_End_Time());
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
