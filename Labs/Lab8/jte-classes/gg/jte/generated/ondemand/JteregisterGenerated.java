package gg.jte.generated.ondemand;
import cs3220.lab8.Student;
import java.util.List;
@SuppressWarnings("unchecked")
@javax.annotation.processing.Generated("gg.jte.TemplateEngine")
public final class JteregisterGenerated {
	public static final String JTE_NAME = "register.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,2,57,57,57,57,57,57,58,58,58,58,58,59,59,59,59,59,60,60,60,60,60,82,82,84,84,85,85,87,87,87,88,88,88,89,89,89,90,90,90,91,91,91,92,92,92,94,94,114,114,114,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<Student> students, String session) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html>\n<head>\n    <title>Students</title>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n\n    <link href=\"https://cdn.jsdelivr.net/npm/bootswatch@5.3.8/dist/cerulean/bootstrap.min.css\" rel=\"stylesheet\">\n</head>\n\n<body>\n\n    <nav class=\"navbar navbar-expand-lg navbar-dark bg-primary bg-body-tertiary\">\n        <div class=\"container-fluid p-3\">\n\n            <a class=\"navbar-brand\" href=\"/\" >\n                <img src=\"/dolphin128x128.png\" width=\"25\" alt=\"Dolphin\">\n                Dolphins Swim Academy\n            </a>\n\n            <button class=\"navbar-toggler\" type=\"button\"\n                    data-bs-toggle=\"collapse\"\n                    data-bs-target=\"#navbarSupportedContent\"\n                    aria-controls=\"navbarSupportedContent\"\n                    aria-expanded=\"false\"\n                    aria-label=\"Toggle navigation\">\n\n                <span class=\"navbar-toggler-icon\"></span>\n            </button>\n\n            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n\n                <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\n\n                    <li class=\"nav-item\">\n                        <a class=\"nav-link\" href=\"#\">Classes</a>\n                    </li>\n                    <li class=\"nav-item\">\n                        <a class=\"nav-link active\" aria-current=\"page\" href=\"/students\">Students</a>\n                    </li>\n                    <li class=\"nav-item\">\n                        <a class=\"nav-link\" href=\"#\">Coaches</a>\n                    </li>\n\n                </ul>\n            </div>\n        </div>\n    </nav>\n\n\n    <form method=\"get\" action=\"/students\" class=\"d-inline-flex gap-2\">\n        <select name=\"session\" class=\"form-select w-auto\" aria-label=\"Select session\">\n            <option value=\"Session 1\"");
		var __jte_html_attribute_0 = session.equals("Session 1");
		if (__jte_html_attribute_0) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Session 1: June 17 - June 27</option>\n            <option value=\"Session 2\"");
		var __jte_html_attribute_1 = session.equals("Session 2");
		if (__jte_html_attribute_1) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Session 2: July 8 - July 18</option>\n            <option value=\"Session 3\"");
		var __jte_html_attribute_2 = session.equals("Session 3");
		if (__jte_html_attribute_2) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Session 3: July 22 - August 1</option>\n            <option value=\"Session 4\"");
		var __jte_html_attribute_3 = session.equals("Session 4");
		if (__jte_html_attribute_3) {
		jteOutput.writeContent(" selected");
		}
		jteOutput.writeContent(">Session 4: August 5 - August 15</option>\n        </select>\n\n        <input type=\"submit\" value=\"Select\" class=\"btn btn-primary\">\n    </form>\n\n\n\n    <table class=\"table table-striped mt-4\">\n\n        <thead>\n            <tr>\n                <th scope=\"col\">Id</th>\n                <th scope=\"col\">Name</th>\n                <th scope=\"col\">Age</th>\n                <th scope=\"col\">Level</th>\n                <th scope=\"col\">Time (1st Choice)</th>\n                <th scope=\"col\">Time (2nd Choice)</th>\n            </tr>\n        </thead>\n\n        <tbody>\n            ");
		if (students.isEmpty()) {
			jteOutput.writeContent("\n                <tr><td colspan=\"6\" class=\"text-center\">No students registered for this session.</td></tr>\n            ");
		}
		jteOutput.writeContent("\n            ");
		for (Student student : students) {
			jteOutput.writeContent("\n                <tr>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(student.getId());
			jteOutput.writeContent("</td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(student.getName());
			jteOutput.writeContent("</td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(student.getAge());
			jteOutput.writeContent("</td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(student.getLevel());
			jteOutput.writeContent("</td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(student.getFirstPreferredTime());
			jteOutput.writeContent("</td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(student.getSecondPreferredTime());
			jteOutput.writeContent("</td>\n                </tr>\n            ");
		}
		jteOutput.writeContent("\n        </tbody>\n\n    </table>\n\n\n\n    <div class=\" text-center p-3 mt-4\">\n        Copyright &copy; Dolphins Swim Academy 2024<br>\n        Dolphin icon is created by\n        <a href=\"https://www.flaticon.com/authors/freepik\">Freepik</a>\n        from\n        <a href=\"https://www.flaticon.com/\">Flaticon</a>\n    </div>\n\n\n\n    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js\"></script>\n\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<Student> students = (List<Student>)params.get("students");
		String session = (String)params.get("session");
		render(jteOutput, jteHtmlInterceptor, students, session);
	}
}
