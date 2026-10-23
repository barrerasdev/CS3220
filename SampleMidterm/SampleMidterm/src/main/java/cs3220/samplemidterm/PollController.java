package cs3220.samplemidterm;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PollController {
    private final ConcurrentSkipListMap<Integer, Poll> polls = new ConcurrentSkipListMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public PollController() {
        Poll languages = createPoll("What programming languages do you know?", false);
        languages.addAnswer("Java");
        languages.addAnswer("Python");
        languages.addAnswer("C/C++");
        Poll basketball = createPoll("Who is the greatest basketball player of all time?", true);
        basketball.addAnswer("Michael Jordan");
        basketball.addAnswer("LeBron James");
        basketball.addAnswer("Kobe Bryant");
    }

    @GetMapping({"/", "/polls"})
    public String list(Model model) {
        model.addAttribute("polls", List.copyOf(polls.values()));
        return "polls";
    }

    @GetMapping("/polls/{id}")
    public String display(@PathVariable int id, Model model) {
        model.addAttribute("poll", findPoll(id));
        return "poll";
    }

    @GetMapping("/polls/create")
    public String createForm() { return "create-poll"; }

    @PostMapping("/polls/create")
    public String create(@RequestParam String question,
                         @RequestParam(defaultValue = "false") boolean singleChoice,
                         Model model) {
        if (question.isBlank()) {
            model.addAttribute("error", "Please enter a question.");
            return "create-poll";
        }
        Poll poll = createPoll(question.trim(), singleChoice);
        return "redirect:/polls/" + poll.getId() + "/answers";
    }

    @GetMapping("/polls/{id}/answers")
    public String answers(@PathVariable int id, Model model) {
        model.addAttribute("poll", findPoll(id));
        return "add-answers";
    }

    @PostMapping("/polls/{id}/answers")
    public String addAnswer(@PathVariable int id, @RequestParam String answer, Model model) {
        Poll poll = findPoll(id);
        if (answer.isBlank()) {
            model.addAttribute("poll", poll);
            model.addAttribute("error", "Please enter an answer.");
            return "add-answers";
        }
        poll.addAnswer(answer.trim());
        return "redirect:/polls/" + id + "/answers";
    }

    private Poll createPoll(String question, boolean singleChoice) {
        Poll poll = new Poll(nextId.getAndIncrement(), question, singleChoice);
        polls.put(poll.getId(), poll);
        return poll;
    }

    private Poll findPoll(int id) {
        Poll poll = polls.get(id);
        if (poll == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found");
        return poll;
    }
}
