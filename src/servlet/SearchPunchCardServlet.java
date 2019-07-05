package servlet;

import Until.Export;
import Until.GetJson;
import entity.PunchCard;

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
import service.PunchCardService;

/**
 * 根据条件搜索并显示打卡记录
 *
 * @author cleju
 */
@WebServlet(name = "SearchPunchCardServlet", urlPatterns = {"/SearchPunchCardServlet"})
public class SearchPunchCardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //导出打卡文件时使用的表头和数据
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Export ex = new Export();
        String[] data = {"ID", "打卡人编码", "打卡人姓名", "打卡日期"};
        String[] keys = {"id", "code", "name", "date"};
        String path = "./SearchPunchCard.xls";
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
            PunchCardService service = new PunchCardService();
            ArrayList<PunchCard> punchCards = service.getAll(parts[1]);
            getResult(punchCards, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }//try_end

    }

    //将从数据库查找的结果一一存入json里，并传回前端
    private void getResult(ArrayList<PunchCard> punchCards, HttpServletResponse response) throws IOException {
        json = new JSONArray();
        resp = response;
        for (PunchCard punchCard : punchCards) {
            JSONObject jo = new JSONObject();
            jo.put("id", punchCard.getPunchCard_ID());
            jo.put("code", punchCard.getPunchCard_Person_Code());
            jo.put("name", punchCard.getPunchCard_Person_Name());
            jo.put("date", punchCard.getPunchCard_Date());
            jo.put("note", punchCard.getPunchCard_Note());
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
