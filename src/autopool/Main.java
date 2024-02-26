package autopool;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var autopool = new Autopool();
        Scanner sc = new Scanner(System.in);

        var lockerRoom = new LockerRoom();

        System.out.println("Добро пожаловать в систему управления бассейном!");
        System.out.println("=======================================");


        System.out.println("\nПожалуйста, введите свои данные для доступа к бассейну:");
        boolean accepted = false;
        Student student = null;
        do {
            System.out.print("Введите в формате [Имя Фамилия 0-ФКЛТ-123]: ");
            String[] strs = sc.nextLine().trim().split(" ");
            if (strs.length != 3) {
                System.out.println("Неправильный формат ввода");
                continue;
            }

            String firstName = strs[0];
            String lastName = strs[1];
            String group = strs[2];

            student = autopool.requestStudent(firstName, lastName, group);
            if (student != null)
                accepted = true;
            else
                System.out.println("Извините, ваша информация не соответствует нашим данным.");

        } while (!accepted);
        System.out.println("===================");
        System.out.println("\nИнформация о студенте:");
        System.out.println("Имя: " + student.firstName + " " + student.lastName);
        System.out.println("Группа: " + student.group);
        System.out.println("Hours attended: " + student.getHours());

        System.out.println("\nДоступные и свободные  шкафчики:");
        System.out.println("===================");
        lockerRoom.log();
        System.out.println("===================");
        if(lockerRoom.requestLocker()==null){
            System.out.println("\nУважаемый студент, массивчик переполнен:");
            return;
        }
        System.out.println("\nВаш ключик под номером " + lockerRoom.requestLocker().getNumber());


        // Simulate pool session
        System.out.println("\nСессия продолжается...");
        Thread.sleep(5000);
        System.out.println("Сессия окончена.");


        student.addHour();
        autopool.updateJournal();
        System.out.println("Журнал обновлен.");
    }
}
