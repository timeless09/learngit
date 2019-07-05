package servlet;

import Until.GetJson;
import Until.StringSegmentation;
import entity.Department;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.DepartmentService;

/**
 * 添加或更新部门
 *
 * @author cleju
 */
@WebServlet(name = "AddDepartmentServlet", urlPatterns = {"/AddDepartmentServlet"})
public class AddDepartmentServlet extends HttpServlet {

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
        Department department = new Department();
        department.setDepartment_ID_Code(s.get(0).toString());
        department.setDepartment_Name(s.get(1).toString());
        department.setDepartment_Leader(s.get(2).toString());
        department.setDepartment_Duty(s.get(3).toString());
        String superior_Name = s.get(4).toString();
        int superior_ID = 0;
        try {
            //获取部门的ID和名称map
            Map<Integer, String> map = StringSegmentation.getDepartmentName();

            //将员工的名称转换成ID存入数据库
            superior_ID = StringSegmentation.getIntegerKey(map, superior_Name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        department.setDepartment_Superior_ID(superior_ID);
        department.setDepartment_ID(Integer.parseInt(s.get(5).toString()));
        try {
            DepartmentService addDepartment = new DepartmentService();
            addDepartment.add(department);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
