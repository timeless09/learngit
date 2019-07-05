package servlet;

import Until.GetJson;
import entity.Station;

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
import service.StationService;

/**
 * 通过id获得岗位信息
 *
 * @author cleju
 */
@WebServlet(name = "GetStationServlet", urlPatterns = {"/GetStationServlet"})
public class GetStationServlet extends HttpServlet {

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
            StationService service = new StationService();
            Station station = service.get(id);
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("code", station.getStation_Code());
            jo.put("name", station.getStation_Name());
            jo.put("department", station.getStation_Department());
            jo.put("superior", station.getStation_Superior());
            jo.put("category", station.getStation_Category());
            jo.put("describe", station.getStation_Describe());
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
