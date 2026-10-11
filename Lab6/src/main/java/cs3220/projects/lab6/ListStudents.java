package cs3220.projects.lab6;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ListStudents", value = "/ListStudents", loadOnStartup = 1)
public class ListStudents extends HttpServlet {

    @Override
    public void init() {
        List<Student> students = new ArrayList<>();
        int year = Year.now().getValue();
        students.add(new Student("Josh", 1, year - 7, "Minnows", "10am", "9am"));
        students.add(new Student("Eva", 1, year - 6, "Minnows", "10am", "1pm"));
        students.add(new Student("Lucy", 1, year - 4, "Starfish", "1pm", "2pm"));
        students.add(new Student("Matt", 2, year - 8, "Dolphins", "9am", "2pm"));
        getServletContext().setAttribute("students", students);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<Student> students =
                (List<Student>) getServletContext().getAttribute("students");
        String registerUrl = response.encodeURL(request.getContextPath() + "/Register");
        out.println("<h2>Students</h2>");
        out.println("<p><strong>Students</strong> | <a href='" + escapeHtml(registerUrl) + "'>Register</a></p>");
        out.println("<table border='1' cellpadding='10' cellspacing='0'>");
        out.println("<thead><tr><th>Name</th><th>Session</th><th>Age</th><th>Level</th>");
        out.println("<th>Time (1st Choice)</th><th>Time (2nd Choice)</th></tr></thead><tbody>");
        synchronized (students) {
            for (Student student : students) {
                int age = Year.now().getValue() - student.getBirthYear();
                out.println("<tr><td>" + escapeHtml(student.getName()) + "</td>");
                out.println("<td>" + student.getSession() + "</td><td>" + age + "</td>");
                out.println("<td>" + escapeHtml(student.getLevel()) + "</td>");
                out.println("<td>" + escapeHtml(student.getchoice1()) + "</td>");
                out.println("<td>" + escapeHtml(student.getchoice2()) + "</td></tr>");
            }
        }
        out.println("</tbody></table></body></html>");
    }

    static String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
