package Until;

import entity.Department;
import entity.Employee;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import service.DepartmentService;
import service.EmployeeService;

/**
 * 工具
 *
 * @author 10958
 */
public class StringSegmentation {

    ArrayList s = new ArrayList();

    //分割
    public static ArrayList update(String str) throws UnsupportedEncodingException {
        String[] parts = str.split("&");
        ArrayList s = new ArrayList();
        for (int i = 0; i < parts.length; i++) {
//            System.out.println(parts[i]);
            String[] emp = parts[i].split("=");
            if (emp.length == 2) {
                s.add(URLDecoder.decode(emp[1], "UTF-8"));
            } else {

                s.add("");
            }
            System.out.println(s.get(i).toString());
        }
        return s;
    }

    //获取所有员工的姓名和编码
    public static Map<String, String> getEmployeeName() throws Exception {
        EmployeeService employeeService = new EmployeeService();
        ArrayList<Employee> employees = employeeService.getAll("%");
        Map map = new HashMap();
        for (Employee employee : employees) {
            map.put(employee.getEmployees_ID_Code(), employee.getEmployees_Name());
        }
        return map;
    }

    //获取所有部门的名称和编码
    public static Map<Integer, String> getDepartmentName() throws Exception {
        DepartmentService departmentService = new DepartmentService();
        ArrayList<Department> departments = departmentService.getAll("%");
        Map map = new HashMap();
        for (Department department : departments) {
            map.put(department.getDepartment_ID(), department.getDepartment_Name());
        }
        return map;
    }

    //根据value值获取到对应的一个key
    public static String getKey(Map<String, String> map, String value) {
        String key = null;
        for (String getKey : map.keySet()) {
            if (map.get(getKey).equals(value)) {
                key = getKey;
            }
        }
        return key;
    }

    public static int getIntegerKey(Map<Integer, String> map, String value) {
        int key = 0;
        for (int getKey : map.keySet()) {
            if (map.get(getKey).equals(value)) {
                key = getKey;
            }
        }
        return key;
    }
}
