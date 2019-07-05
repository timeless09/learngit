package servlet;

import Until.GetJson;
import entity.PaySalary;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import service.PaySalaryService;

/**
 * 通过id获得派薪记录
 *
 * @author 10303
 */
@WebServlet(name = "GetPaySalaryServlet", urlPatterns = {"/GetPaySalaryServlet"})
public class GetPaySalaryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        double salary;
        String startTime;
        String endTime;
        try {
            PaySalaryService service = new PaySalaryService();
            PaySalary paySalary = service.get(id);
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("name", paySalary.getSalary_Person_Name());
            salary = paySalary.getSalary();
            startTime = paySalary.getSalary_Start_Time();
            endTime = paySalary.getSalary_End_Time();
            salary = calSalary(startTime, endTime, salary);
            jo.put("salary", salary);
            jo.put("start", startTime.replace(' ', 'T'));
            jo.put("end", endTime.replace(' ', 'T'));
            jo.put("hour", paySalary.getSalary_Time());
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(jo.toString());
        } catch (Exception ex) {
            Logger.getLogger(GetEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //根据考勤的小时数来计算薪水
    private double calSalary(String startTime, String endTime, double salary) throws ParseException {
        double newSalary = 0;
        long nd = 1000 * 24 * 60 * 60;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = sdf.parse(endTime).getTime() - sdf.parse(startTime).getTime();
        System.out.println(time);
        int day = (int) (time / nd);
        int hour = day * 9;
        newSalary = salary * hour / (day * 9);
        System.out.println(newSalary);
        return newSalary;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
