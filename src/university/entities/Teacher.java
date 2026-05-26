package university.entities;

import university.enums.TeacherPosition;

public class Teacher extends Person {

    private TeacherPosition position;
    private double salary;

    public Teacher(int id, String name, String email, TeacherPosition position, double salary) {
        super(id, name, email);
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не може бути від'ємною");
        }
        this.position = position;
        this.salary = salary;
    }

    public TeacherPosition getPosition() {
        return position;
    }

    public void setPosition(TeacherPosition position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не може бути від'ємною");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " | Посада: " + position + " | Зарплата: " + salary;
    }
}
