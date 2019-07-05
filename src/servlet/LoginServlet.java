package servlet;

import Until.GetJson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.LoginService;

/**
 * 登录
 *
 * @author 10958
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        //调用工具类GetJson将前端传回的json转换为String
        String str = GetJson.getJson(request);
        str = URLDecoder.decode(str, "UTF-8");
        String[] parts = str.split("&");
        System.out.println(str);
        String part1 = parts[1];
        String part2 = parts[0];
        String[] usernamepart = part2.split("=");
        String[] passwordpart = part1.split("=");
        String username = usernamepart[1];
        String password = passwordpart[1];
        HttpSession session = request.getSession();
        try {
            LoginService login = new LoginService();
            if (login.Login(username, password)) {
                out.print("1");
                session.removeAttribute("userName");
                session.setAttribute("userName", username);
            } else {
                out.print("2");
            }
        } catch (Exception ex) {
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
