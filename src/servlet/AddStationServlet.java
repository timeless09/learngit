package servlet;

import Until.GetJson;
import Until.StringSegmentation;
import entity.Station;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StationService;

/**
 * 添加或更新岗位信息
 *
 * @author cleju
 */
@WebServlet(name = "AddStationServlet", urlPatterns = {"/AddStationServlet"})
public class AddStationServlet extends HttpServlet {

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

        //将字符串进行分割，然后把属性添加到对象中进行增加或更新
        Station station = new Station();
        station.setStation_Code(s.get(0).toString());
        station.setStation_Name(s.get(1).toString());
        station.setStation_Department(s.get(2).toString());
        station.setStation_Superior(s.get(3).toString());
        station.setStation_Category(s.get(4).toString());
        station.setStation_Describe(s.get(5).toString());
        station.setStation_ID(Integer.parseInt(s.get(6).toString()));

        try {
            StationService addStation = new StationService();
            addStation.add(station);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
