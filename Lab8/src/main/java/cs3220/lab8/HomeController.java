package cs3220.lab8;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Controller
public class HomeController {
    private final StudentStore studentStore;

    public HomeController(StudentStore studentStore) {
        this.studentStore = studentStore;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/students")
    public String register(@RequestParam(defaultValue = "Session 1") String session, Model model) {
        if (!java.util.List.of("Session 1", "Session 2", "Session 3", "Session 4").contains(session)) {
            session = "Session 1";
        }
        String selectedSession = session;
        model.addAttribute("students", studentStore.getStudents().stream()
                .filter(student -> student.getSession().equals(selectedSession))
                .toList());
        model.addAttribute("session", session);
        return "register";
    }

    @GetMapping("/register")
    public String registration() {
        return "registration";
    }

    @PostMapping("/register")
    public String saveStudent(@RequestParam String name, @RequestParam String session,
                              @RequestParam int birthYear, @RequestParam String level,
                              @RequestParam String firstPreferredTime,
                              @RequestParam String secondPreferredTime) {
        var times = java.util.List.of("9am", "10am", "1pm", "2pm");
        if (name.isBlank() || !java.util.List.of("Session 1", "Session 2", "Session 3", "Session 4").contains(session)
                || birthYear < 1900 || birthYear > java.time.Year.now().getValue()
                || !java.util.List.of("Starfish", "Minnows", "Guppies", "Dolphins").contains(level)
                || !times.contains(firstPreferredTime) || !times.contains(secondPreferredTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid student registration");
        }
        studentStore.addStudent(new Student(0, name.trim(), session, birthYear, level,
                firstPreferredTime, secondPreferredTime));
        return "redirect:/register/complete";
    }

    @GetMapping("/register/complete")
    public String registrationComplete() {
        return "registrationComplete";
    }
}
