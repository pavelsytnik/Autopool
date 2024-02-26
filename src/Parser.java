import java.io.*;
import java.util.*;

public class Parser {
    public List<Student> readStudentData() {
        String fileName = "res/students.txt";
        String value = null;
        List<Student> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while ((value = reader.readLine()) != null) {
                String[] words = value.split(" ");
                if (words.length != 4)
                    throw new IllegalStateException();

                var student = new Student(words[0], words[1], words[2], Integer.parseInt(words[3]));
                list.add(student);
            }
        } catch (IllegalStateException e) {
            System.err.println("Некорректно введённая строка");
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return list;
    }
}
