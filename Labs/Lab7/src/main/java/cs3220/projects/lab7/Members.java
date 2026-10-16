package cs3220.projects.lab7;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Members", value = "/Members")
public class Members extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Login"));
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String logoutUrl = response.encodeURL(request.getContextPath() + "/Logout");
        out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>");
        out.println("<title>Members</title></head><body>");
        out.println("<p>Hi, " + Login.escapeHtml(user.getName()) + "! Welcome to the Members Area!</p>");
        out.println("<p><a href='" + Login.escapeHtml(logoutUrl) + "'>Logout</a></p>");
        out.println("</body></html>");
    }
}
