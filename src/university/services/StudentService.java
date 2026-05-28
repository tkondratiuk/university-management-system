package university.services;

import university.entities.Student;
import university.enums.StudentStatus;

import java.util.Arrays;
import java.util.Comparator;

public class StudentService {

    private Student[] students;
    private int count;
    private int nextId;

    public StudentService() {
        students = new Student[100];
        count = 0;
        nextId = 1;
    }

    public Student addStudent(String name, String email, int yearOfStudy) {
        Student student = new Student(nextId++, name, email, yearOfStudy);
        students[count++] = student;
        return student;
    }

    public void showAll() {
        if (count == 0) {
            System.out.println("Список студентів порожній.");
            return;
        }
        Arrays.stream(students, 0, count).forEach(System.out::println);
    }

    public Student findById(int id) {
        return Arrays.stream(students, 0, count)
                     .filter(student -> student.getId() == id)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Студента з ID " + id + " не знайдено"));
    }

    public boolean delete(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                students[i] = students[count - 1];
                students[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    public boolean update(int id, String name, String email, int year) {
        Student student = findById(id);
        student.setName(name);
        student.setEmail(email);
        student.setYearOfStudy(year);
        return true;
    }

    public boolean changeStatus(int id, StudentStatus status) {
        Student student = findById(id);
        student.setStatus(status);
        return true;
    }

    public void showByStatus(StudentStatus status) {
        long found = Arrays.stream(students, 0, count)
                           .filter(student -> student.getStatus() == status)
                           .peek(System.out::println)
                           .count();
        if (found == 0) System.out.println("Студентів зі статусом " + status + " не знайдено.");
    }

    public void showSortedByName() {
        if (count == 0) {
            System.out.println("Список порожній.");
            return;
        }
        Arrays.stream(students, 0, count)
              .sorted(Comparator.comparing(Student::getName))
              .forEach(System.out::println);
    }

    public int getCount() {
        return count;
    }

    public Student[] getStudents() {
        return students;
    }
}
