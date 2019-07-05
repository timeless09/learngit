package servlet;

import Until.GetJson;
import entity.PunchCard;
import Until.StringSegmentation;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PunchCardService;

/**
 * 添加或更新打卡记录
 *
 * @author 10303
 */
@WebServlet("/AddPunchCardServlet")
public class AddPunchCardServlet extends HttpServlet {

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
        System.out.println(s);
        //将字符串进行分割，然后把属性添加到对象中进行增加或更新
        PunchCard punchCard = new PunchCard();
        String code = "LS";
        String name = s.get(0).toString();
        try {
            //获取员工编码和姓名map
            Map<String, String> map = StringSegmentation.getEmployeeName();
            //将员工的姓名转换成编码存入数据库
            code = StringSegmentation.getKey(map, name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        punchCard.setPunchCard_Person_Code(code);
        punchCard.setPunchCard_Date(s.get(1).toString().replace("T", " "));
        punchCard.setPunchCard_Note(s.get(2).toString());
        punchCard.setPunchCard_ID(Integer.parseInt(s.get(3).toString()));
        try {
            PunchCardService service = new PunchCardService();
            service.add(punchCard);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
