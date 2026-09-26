package cs3220.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ListStudents", value = "/ListStudents")
public class ListStudents extends HttpServlet {

    public void init() {

        List<Student> students = new ArrayList<>();

        int currentYear = Year.now().getValue();

        students.add(new Student(
                "Josh",
                1,
                currentYear - 7,
                "Minnows",
                "10am",
                "9am"
        ));

        students.add(new Student(
                "Eva",
                1,
                currentYear - 6,
                "Minnows",
                "10am",
                "1pm"
        ));

        students.add(new Student(
                "Lucy",
                1,
                currentYear - 4,
                "Starfish",
                "1pm",
                "2pm"
        ));

        students.add(new Student(
                "Matt",
                2,
                currentYear - 8,
                "Dolphins",
                "9am",
                "2pm"
        ));

        getServletContext().setAttribute("students", students);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        List<Student> students =
                (List<Student>) getServletContext().getAttribute("students");

        out.println("<html>");
        out.println("<body>");
        out.println("<h2>Students</h2>");
        out.println("<table border='1' cellpadding='10' cellspacing='0'>");

        out.println("<tr>");
        out.println("<th>Name</th>");
        out.println("<th>Session</th>");
        out.println("<th>Age</th>");
        out.println("<th>Level</th>");
        out.println("<th>Time (1st Choice)</th>");
        out.println("<th>Time (2nd Choice)</th>");
        out.println("</tr>");

        for (Student student : students) {
            int age = Year.now().getValue() - student.getBirthYear();
            out.println("<tr>");
            out.println("<td>" + student.getName() + "</td>");
            out.println("<td>" + student.getSession() + "</td>");
            out.println("<td>" + age + "</td>");
            out.println("<td>" + student.getLevel() + "</td>");
            out.println("<td>" + student.getchoice1() + "</td>");
            out.println("<td>" + student.getchoice2() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}