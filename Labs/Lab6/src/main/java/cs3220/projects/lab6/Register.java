package cs3220.projects.lab6;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    private static final String[] LEVELS = {"Starfish", "Minnows", "Guppies", "Dolphins"};
    private static final String[] TIMES = {"9am", "10am", "1pm", "2pm"};

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = response.encodeURL(request.getContextPath() + "/Register");
        String studentsUrl = response.encodeURL(request.getContextPath() + "/ListStudents");
        out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>");
        out.println("<title>Register</title></head><body><h2>Register</h2>");
        out.println("<p><a href='" + ListStudents.escapeHtml(studentsUrl) + "'>Students</a> | <strong>Register</strong></p>");
        out.println("<form method='post' action='" + ListStudents.escapeHtml(action) + "'>");
        out.println("<div><label for='session'>Session:</label> <select id='session' name='session'>");
        for (int session = 1; session <= 4; session++) {
            out.println("<option value='" + session + "'>Session " + session + "</option>");
        }
        out.println("</select></div>");
        out.println("<div><label for='name'>Name:</label> <input type='text' id='name' name='name' required></div>");
        out.println("<div><label for='year'>Birth Year:</label> <input type='number' id='year' name='year' min='1' max='"
                + Year.now().getValue() + "' required></div>");
        out.println("<div><label for='level'>Level:</label> <select id='level' name='level'>");
        for (String level : LEVELS) {
            out.println("<option value='" + level + "'>" + level + "</option>");
        }
        out.println("</select></div>");
        for (int choice = 1; choice <= 2; choice++) {
            out.println("<div>Time (" + (choice == 1 ? "1st" : "2nd") + " Choice):");
            for (int index = 0; index < TIMES.length; index++) {
                String id = "choice" + choice + "-" + index;
                out.println("<input type='radio' id='" + id + "' name='choice" + choice
                        + "' value='" + TIMES[index] + "' required> <label for='" + id + "'>"
                        + TIMES[index] + "</label>");
            }
            out.println("</div>");
        }
        out.println("<button type='submit'>Register</button></form></body></html>");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        int session;
        int birthYear;
        try {
            session = Integer.parseInt(request.getParameter("session"));
            birthYear = Integer.parseInt(request.getParameter("year"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Session and birth year must be numbers.");
            return;
        }
        if (name == null || name.trim().isEmpty() || session < 1 || session > 4
                || birthYear < 1 || birthYear > Year.now().getValue()
                || !Arrays.asList(LEVELS).contains(level)
                || !Arrays.asList(TIMES).contains(choice1)
                || !Arrays.asList(TIMES).contains(choice2)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please complete all registration fields with valid values.");
            return;
        }
        Student student = new Student(name.trim(), session, birthYear, level, choice1, choice2);
        List<Student> students =
                (List<Student>) getServletContext().getAttribute("students");
        synchronized (students) {
            students.add(student);
        }
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/ListStudents"));
    }
}
