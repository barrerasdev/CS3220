package cs3220.lab8;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StudentStore {
    private final List<Student> students = new ArrayList<>();

    public StudentStore() {
        students.add(new Student(1, "Josh", "Session 1", 2019, "Minnows", "10am", "9am"));
        students.add(new Student(2, "Eva", "Session 1", 2020, "Minnows", "10am", "1pm"));
        students.add(new Student(3, "Lucy", "Session 1", 2022, "Starfish", "1pm", "2pm"));
        students.add(new Student(4, "Matt", "Session 2", 2018, "Dolphins", "9am", "2pm"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public synchronized void addStudent(Student student) {
        student.setId(students.stream().mapToInt(Student::getId).max().orElse(0) + 1);
        students.add(student);
    }
}
