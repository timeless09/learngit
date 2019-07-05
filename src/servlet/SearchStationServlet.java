package servlet;

import Until.Export;
import Until.GetJson;
import entity.Station;

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
import service.StationService;

/**
 * 根据条件搜索并显示岗位信息
 *
 * @author cleju
 */
@WebServlet(name = "SearchStationServlet", urlPatterns = {"/SearchStationServlet"})
public class SearchStationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //导出岗位文件时使用的表头和数据
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Export ex = new Export();
        String[] data = {"ID", "岗位编码", "岗位名称", "所在部门", "直接上级", "岗位类别"};
        String[] keys = {"id", "code", "name", "department", "superior", "category"};
        String path = "./SearchStations.xls";
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
            StationService service = new StationService();
            ArrayList<Station> stations = service.getAll(parts[1]);
            getResult(stations, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }//try_end
    }

    //将从数据库查找的结果一一存入json里，并传回前端
    private void getResult(ArrayList<Station> stations, HttpServletResponse response) throws IOException {
        json = new JSONArray();
        resp = response;
        for (Station station : stations) {
            JSONObject jo = new JSONObject();
            jo.put("id", station.getStation_ID());
            jo.put("code", station.getStation_Code());
            jo.put("name", station.getStation_Name());
            jo.put("department", station.getStation_Department());
            jo.put("superior", station.getStation_Superior());
            jo.put("category", station.getStation_Category());
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
