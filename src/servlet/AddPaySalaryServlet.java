package servlet;

import Until.GetJson;
import Until.StringSegmentation;
import entity.PaySalary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PaySalaryService;

/**
 * 添加或更新派薪表
 *
 * @author 10303
 */
@WebServlet(name = "AddPaySalaryServlet", urlPatterns = {"/AddPaySalaryServlet"})
public class AddPaySalaryServlet extends HttpServlet {

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
        ArrayList s = StringSegmentation.update(str);
        //将字符串进行分割，然后把属性添加到对象中进行增加或更新
        PaySalary paySalary = new PaySalary();
        paySalary.setSalary_ID(Integer.parseInt(s.get(4).toString()));
        String name = s.get(0).toString();
        String code = "ZS";
        try {
            //获取员工编码和姓名map
            Map<String, String> map = StringSegmentation.getEmployeeName();
            //将员工的姓名转换成编码存入数据库
            code = StringSegmentation.getKey(map, name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        paySalary.setSalary_Person_Code(code);
        paySalary.setSalary_Start_Time(s.get(1).toString().replace('T', ' '));
        paySalary.setSalary_End_Time(s.get(2).toString().replace('T', ' '));
        paySalary.setSalary(Double.parseDouble(s.get(3).toString()));

        try {
            PaySalaryService service = new PaySalaryService();
            service.add(paySalary);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
