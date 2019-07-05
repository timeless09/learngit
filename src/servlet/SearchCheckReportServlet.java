package servlet;

import Until.Export;
import Until.GetJson;
import entity.CheckReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CheckReportService;

/**
 * 根据条件搜索并显示考勤表
 *
 * @author 10303
 */
@WebServlet(name = "SearchCheckReportServlet", urlPatterns = {"/SearchCheckReportServlet"})
public class SearchCheckReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    //导出考勤文件时使用的表头和数据
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Export ex = new Export();
        String[] data = {"ID", "员工编码", "员工姓名", "早上上班时间", "下午下班时间", "出勤情况"};
        String[] keys = {"id", "code", "name", "start", "end", "condition"};
        String path = "./SearchCheckReport.xls";
        ex.save(json, data, keys, path, resp);
    }

    JSONArray json;
    HttpServletResponse resp;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        sdf.applyPattern("yyyy-MM-dd"); // 设置显示时间格式
        String firstDayOfMonth = sdf.format(calendar.getTime());//本月第一天
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String lastDayOfMonth = sdf.format(calendar.getTime());//本月最后一天

        String str = GetJson.getJson(request);//调用工具类GetJson将前端传回的json转换成String
        str = URLDecoder.decode(str, "UTF-8");

        if (str.contains("%")) {
            try {
                System.out.println("1111");
                CheckReportService service = new CheckReportService();
                ArrayList<CheckReport> checkReports = service.getAll("%");
                getResult(checkReports, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                String[] parts = new String[5];
                parts = str.split("=|&"); //对字符串进行分割，获得要搜索的内容

                if (str.contains("*")) {
                    parts[3] = firstDayOfMonth;
                    parts[5] = lastDayOfMonth;
                }
                CheckReportService service = new CheckReportService();//调用service的getAll方法，显示所需要的内容
                ArrayList<CheckReport> checkReports;

                checkReports = service.search(parts[1], parts[3], parts[5]);
                getResult(checkReports, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    //将从数据库查找的结果一一存入json里，并传回前端
    private void getResult(ArrayList<CheckReport> checkReports, HttpServletResponse response) throws Exception {
        json = new JSONArray();
        resp = response;
        for (CheckReport checkReport : checkReports) {
            JSONObject jo = new JSONObject();
            jo.put("id", checkReport.getCheckReport_ID());
            jo.put("code", checkReport.getCheckReport_Employee_Code());
            jo.put("name", checkReport.getCheckReport_Employee_Name());
            String start = checkReport.getCheckReport_Start_Time();
            String end = checkReport.getCheckReport_End_Time();
            jo.put("start", start);
            jo.put("end", end);
            jo.put("condition", attendanceCondition(start, end));
            json.add(jo);
        }
        System.out.println("CheckReport:" + json);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    //根据打卡的开始时间和打卡的结束时间来判断员工的出勤状况
    private String attendanceCondition(String startTime, String endTime) throws ParseException {
        String condition;
        if (startTime.equals("未知") || endTime.equals("未知")) {
            condition = "旷工";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String class_start_time = "9:00:00";
            String class_end_time = "18:00:00";
            String start[] = startTime.split(" ");
            String end[] = endTime.split(" ");
            startTime = start[1];
            endTime = end[1];
            if (sdf.parse(startTime).getTime() > sdf.parse(class_start_time).getTime()) {
                condition = "迟到";
            } else if (sdf.parse(endTime).getTime() < sdf.parse(class_end_time).getTime()) {
                condition = "早退";
            } else {
                condition = "正常";
            }
        }

        return condition;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
