package servlet;

import Until.GetJson;
import Until.StringSegmentation;
import entity.RepairCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RepairCardService;

/**
 * 添加或更新补卡记录
 *
 * @author 10303
 */
@WebServlet(name = "AddRepairCardServlet", urlPatterns = {"/AddRepairCardServlet"})
public class AddRepairCardServlet extends HttpServlet {

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
        RepairCard repairCard = new RepairCard();
        repairCard.setRepair_ID(Integer.parseInt(s.get(3).toString()));

        String name = s.get(0).toString();
        String code = "ZG";
        try {
            //获取员工编码和姓名map
            Map<String, String> map = StringSegmentation.getEmployeeName();
            //将员工的姓名转换成编码存入数据库
            code = StringSegmentation.getKey(map, name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        repairCard.setRepair_Person_Code(code);
        String str22 = s.get(1).toString().replace('T', ' ');
        System.out.println(str22);
        repairCard.setRepair_Date(str22);
        repairCard.setRepair_Reason(s.get(2).toString());

        try {
            RepairCardService repairCardService = new RepairCardService();
            repairCardService.add(repairCard);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
