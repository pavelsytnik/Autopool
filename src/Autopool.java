import java.util.List;
import java.io.*;

public class Autopool {
    private final List<Student> students;
    public Autopool() {
        students = new Parser().readStudentData();
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student requestStudent(String firstName, String lastName, String group) {
        for (var student : students) {
            if (student.correspond(firstName, lastName, group))
                return student;
        }
        return null;
    }

    public void updateJournal() {

        try (var fileWriter = new FileWriter("students.txt")) {
            for (var student : students) {
                fileWriter.write(student.toString() + " " + student.getHours() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Файл отсутствует или иная ошибка ввода-вывода");
        }
    }
}
