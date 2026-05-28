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

    public Student findById(int studentId) {
        return Arrays.stream(students, 0, count)
                     .filter(student -> student.getId() == studentId)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Студента з ID " + studentId + " не знайдено"));
    }

    public boolean delete(int studentId) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == studentId) {
                students[i] = students[count - 1];
                students[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    public boolean update(int studentId, String name, String email, int year) {
        Student student = findById(studentId);
        student.setName(name);
        student.setEmail(email);
        student.setYearOfStudy(year);
        return true;
    }

    public boolean changeStatus(int studentId, StudentStatus status) {
        Student student = findById(studentId);
        student.setStatus(status);
        return true;
    }

    public void showByStatus(StudentStatus status) {
        long found = Arrays.stream(students, 0, count)
                           .filter(student -> student.getStatus() == status)
                           .peek(System.out::println)
                           .count();
        if (found == 0) {
            System.out.println("Студентів зі статусом " + status + " не знайдено.");
        }
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
