package university.entities;

import university.enums.StudentStatus;

public class Student extends Person {

    private int yearOfStudy;
    private StudentStatus status;

    public Student(int id, String name, String email, int yearOfStudy) {
        super(id, name, email);
        if (yearOfStudy < 1 || yearOfStudy > 6) {
            throw new IllegalArgumentException("Рік навчання має бути від 1 до 6");
        }
        this.yearOfStudy = yearOfStudy;
        this.status = StudentStatus.ACTIVE;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        if (yearOfStudy < 1 || yearOfStudy > 6) {
            throw new IllegalArgumentException("Рік навчання має бути від 1 до 6");
        }
        this.yearOfStudy = yearOfStudy;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + " | Рік навчання: " + yearOfStudy + " | Статус: " + status;
    }
}
