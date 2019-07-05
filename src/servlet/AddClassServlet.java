package servlet;

import Until.GetJson;
import Until.StringSegmentation;
import entity.Clazz;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ClazzService;

/**
 * 添加或更新班次
 *
 * @author cleju
 */
@WebServlet(name = "AddClassServlet", urlPatterns = {"/AddClassServlet"})
public class AddClassServlet extends HttpServlet {

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
        Clazz clazz = new Clazz();
        clazz.setClass_Code(s.get(0).toString());
        clazz.setClass_Name(s.get(1).toString());
        clazz.setClass_Start_Time(s.get(2).toString());
        clazz.setClass_End_Time(s.get(3).toString());
        clazz.setClass_Note(s.get(4).toString());
        clazz.setClass_ID(Integer.parseInt(s.get(5).toString()));

        try {
            ClazzService service = new ClazzService();
            service.add(clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
