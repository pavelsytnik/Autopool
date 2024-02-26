package autopool;

public class Student {

    public final String firstName;
    public final String lastName;
    public final String group;
    private int hours;

    public Student(String firstName, String lastName, String group, int hours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.hours = hours;
    }

    public void addHour() {
        hours++;
    }

    public int getHours() {
        return hours;
    }

    public boolean correspond(String firstName, String lastName, String group) {
        return this.firstName.equals(firstName)
                && this.lastName.equals(lastName)
                && this.group.equals(group);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + group;
    }
}
