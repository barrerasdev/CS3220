package cs3220.lab8;

import java.time.Year;

public class Student {
    private int id;
    private String name;
    private String session;
    private int birthYear;
    private String level;
    private String firstPreferredTime;
    private String secondPreferredTime;

    public Student() {
    }

    public Student(int id, String name, String session, int birthYear, String level,
                   String firstPreferredTime, String secondPreferredTime) {
        this.id = id;
        this.name = name;
        this.session = session;
        this.birthYear = birthYear;
        this.level = level;
        this.firstPreferredTime = firstPreferredTime;
        this.secondPreferredTime = secondPreferredTime;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getFirstPreferredTime() {
        return firstPreferredTime;
    }
    public void setFirstPreferredTime(String firstPreferredTime) {
        this.firstPreferredTime = firstPreferredTime;
    }
    public String getSecondPreferredTime() {
        return secondPreferredTime;
    }
    public void setSecondPreferredTime(String secondPreferredTime) {
        this.secondPreferredTime = secondPreferredTime;
    }
    public int getAge() {
        return Year.now().getValue() - birthYear;
    }
}
