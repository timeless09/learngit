package servlet;

import Until.GetJson;
import entity.PunchCard;

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
import service.PunchCardService;

/**
 * 通过id获得打卡记录
 *
 * @author zzj
 */
@WebServlet(name = "GetPunchCardServlet", urlPatterns = {"/GetPunchCardServlet"})
public class GetPunchCardServlet extends HttpServlet {

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
        PunchCard punchcard = null;
        try {
            response.setCharacterEncoding("utf-8");
            //调用工具类GetJson将前端传回的json转换为String
            String str = GetJson.getJson(request);
            System.err.println(str);
            String patr[] = str.split("=");
            int id = Integer.parseInt(patr[1]);
            System.out.println(id);
            PunchCardService service = new PunchCardService();
            punchcard = service.get(id);
            JSONObject jo = new JSONObject();
            jo.put("pc_person_name", punchcard.getPunchCard_Person_Name());
            jo.put("pc_date", punchcard.getPunchCard_Date());
            jo.put("pc_note", punchcard.getPunchCard_Note());
            PrintWriter out = response.getWriter();
            out.print(jo.toString());
            System.out.println(jo.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetPunchCardServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
