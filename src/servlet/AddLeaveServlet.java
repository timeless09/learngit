package servlet;

import Until.GetJson;
import Until.StringSegmentation;
import entity.Leave;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LeaveService;

/**
 * 添加或更新请假记录
 *
 * @author cleju
 */
@WebServlet(name = "AddLeaveServlet", urlPatterns = {"/AddLeaveServlet"})
public class AddLeaveServlet extends HttpServlet {

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
        //调用工具类GetJson将前端传回的json转换成String
        String str = GetJson.getJson(request);
        System.out.println(URLDecoder.decode(str, "UTF-8"));
        ArrayList s = StringSegmentation.update(str);
        System.out.println(s);
        //将字符串进行分割，然后把属性添加到对象中进行增加或更新
        Leave leave = new Leave();
        String Person_Code = "LS";
        String Person_Name = s.get(0).toString();
        try {
            //获取员工编码和姓名map
            Map<String, String> map = StringSegmentation.getEmployeeName();
            //将员工的姓名转换成编码存入数据库
            Person_Code = StringSegmentation.getKey(map, Person_Name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        leave.setLeave_Person_Code(Person_Code);
        leave.setLeave_Start_Time(s.get(1).toString().replace("T", " "));
        leave.setLeave_End_Time(s.get(2).toString().replace("T", " "));
        leave.setLeave_Reason(s.get(3).toString());
        leave.setLeave_ID(Integer.parseInt(s.get(4).toString()));
        try {
            LeaveService service = new LeaveService();
            service.add(leave);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
