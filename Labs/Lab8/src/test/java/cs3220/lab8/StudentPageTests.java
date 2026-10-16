package cs3220.lab8;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentPageTests {
    @LocalServerPort
    private int port;

    private String page(String path) throws Exception {
        try (HttpClient client = HttpClient.newHttpClient()) {
            var response = client.send(HttpRequest.newBuilder(
                    URI.create("http://localhost:" + port + path)).build(),
                    HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            return response.body();
        }
    }

    @Test
    void homeLinksToStudents() throws Exception {
        String html = page("/");
        assertTrue(html.contains("2024 Summer Swimming Lessons"));
        assertTrue(html.contains("href=\"/register\""));
        assertTrue(html.contains("href=\"#\">Coaches"));
    }

    @Test
    void registrationSavesStudentAndShowsConfirmation() throws Exception {
        assertTrue(page("/register").contains("method=\"post\""));
        try (HttpClient client = HttpClient.newHttpClient()) {
            var response = client.send(HttpRequest.newBuilder(
                    URI.create("http://localhost:" + port + "/register"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("name=Steve&session=Session+4&birthYear=2020"
                            + "&level=Minnows&firstPreferredTime=9am&secondPreferredTime=10am"))
                    .build(), HttpResponse.BodyHandlers.ofString());
            assertEquals(302, response.statusCode());
            assertEquals("/register/complete", URI.create(response.headers().firstValue("Location").orElseThrow()).getPath());
        }
        assertTrue(page("/register/complete").contains("Thank you for registering"));
        assertTrue(page("/students?session=Session%204").contains("<td>Steve</td>"));
    }

    @Test
    void studentsDefaultToSessionOne() throws Exception {
        String html = page("/students");
        assertTrue(html.contains("<td>Josh</td>"));
        assertTrue(html.contains("<td>Eva</td>"));
        assertTrue(html.contains("<td>Lucy</td>"));
        assertFalse(html.contains("<td>Matt</td>"));
        assertFalse(html.contains(">Home</a>"));
        assertTrue(html.contains("value=\"Session 1\" selected"));
        assertFalse(html.contains("value=\"Session 2\" selected"));
    }

    @Test
    void sessionSelectionFiltersStudentsAndStaysSelected() throws Exception {
        String html = page("/students?session=Session%202");
        assertTrue(html.contains("<td>Matt</td>"));
        assertFalse(html.contains("<td>Josh</td>"));
        assertTrue(html.contains("value=\"Session 2\" selected"));
        assertFalse(html.contains("value=\"Session 1\" selected"));
        assertTrue(page("/students?session=Session%203").contains("No students registered"));
        assertTrue(page("/students?session=invalid").contains("<td>Josh</td>"));
    }
}
