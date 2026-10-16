package cs3220.projects.lab7;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Login", value = "/Login", loadOnStartup = 1)
public class Login extends HttpServlet {

    @Override
    public void init() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "jdoe", "abcd"));
        users.add(new User(2, "Jane Smith", "jsmith", "1234"));
        getServletContext().setAttribute("users", users);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = response.encodeURL(request.getContextPath() + "/Login");
        out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>");
        out.println("<title>Login</title></head><body><h2>Login</h2>");
        out.println("<form method='post' action='" + escapeHtml(action) + "'>");
        out.println("<p><label for='username'>Username:</label> <input type='text' id='username' name='username'></p>");
        out.println("<p><label for='password'>Password:</label> <input type='password' id='password' name='password'></p>");
        out.println("<p><input type='submit' name='login' value='Login'></p>");
        out.println("</form></body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<User> users = (List<User>) getServletContext().getAttribute("users");
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Members"));
                return;
            }
        }
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Login"));
    }

    static String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
