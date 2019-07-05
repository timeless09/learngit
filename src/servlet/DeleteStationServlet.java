package servlet;

import Until.GetJson;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.StationService;

/**
 * 删除岗位信息
 *
 * @author cleju
 */
@WebServlet(name = "DeleteStationServlet", urlPatterns = {"/DeleteStationServlet"})
public class DeleteStationServlet extends HttpServlet {

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
        System.out.println(URLDecoder.decode(str, "UTF-8"));
        //若字符串包含字符“a”，则为批量删除，否则为单个删除
        if (str.contains("a")) {
            System.out.println("存在");
            String[] emp = str.split("=");
            System.out.println(emp[1]);
            //将每个id分隔开后存入数组part中，循环调用del方法
            String[] part = emp[1].split("a");
            System.out.println(part.length);
            for (String part1 : part) {
                del(Integer.parseInt(part1));
            }
        } else {
            System.out.println("不存在!");
            String[] emp = str.split("=");
            int id = Integer.parseInt(emp[1]);
            del(id);
        }
    }

    public void del(int id) {
        try {
            StationService service = new StationService();
            service.delete(id);
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
