package cs3220.model;

public class Student {

    private String name;
    private int session;
    private int birthYear;
    private String level;
    private String choice1;
    private String choice2;

    public Student(String name, int session, int birthYear,
                   String level, String choice1, String choice2) {
        this.name = name;
        this.session = session;
        this.birthYear = birthYear;
        this.level = level;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public String getName() {
        return name;
    }

    public int getSession() {
        return session;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getLevel() {
        return level;
    }

    public String getchoice1() {
        return choice1;
    }

    public String getchoice2() {
        return choice2;
    }
}