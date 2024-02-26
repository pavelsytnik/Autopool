package autopool;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var autopool = new Autopool();
        Student student = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("Добро пожаловать в бассейн!");

        boolean accepted = false;
        do {
            System.out.print("Введите в формате [Имя Фамилия 0-ФКЛТ-123]: ");
            String[] strs = sc.nextLine().trim().split(" ");
            if (strs.length != 3) {
                System.out.println("Некорректный ввод");
                continue;
            }

            String firstName = strs[0];
            String lastName = strs[1];
            String group = strs[2];

            student = autopool.requestStudent(firstName, lastName, group);
            if (student != null)
                accepted = true;
            else
                System.out.println("Нет соответствия в базе данных");

        } while (!accepted);

        System.out.println("Студент " + student + " (" + student.getHours() + " занятий)");

        System.out.println("\nНачало занятия");
        Thread.sleep(5000);
        System.out.println("Конец занятия");

        student.addHour();
        autopool.updateJournal();
    }
}
