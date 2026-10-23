package cs3220.samplemidterm;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private final int id;
    private final String question;
    private final boolean singleChoice;
    private final List<String> answers = new ArrayList<>();

    public Poll(int id, String question, boolean singleChoice) {
        this.id = id;
        this.question = question;
        this.singleChoice = singleChoice;
    }

    public int getId() { return id; }
    public String getQuestion() { return question; }
    public boolean isSingleChoice() { return singleChoice; }
    public synchronized List<String> getAnswers() { return List.copyOf(answers); }
    public synchronized void addAnswer(String answer) { answers.add(answer); }
}
