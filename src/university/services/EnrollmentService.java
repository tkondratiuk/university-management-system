package university.services;

import university.entities.Course;
import university.entities.Enrollment;
import university.entities.Student;
import university.enums.Grade;
import university.util.GPAUtils;

import java.util.Arrays;

public class EnrollmentService {

    private Enrollment[] enrollments;
    private int count;
    private int nextId;

    public EnrollmentService() {
        enrollments = new Enrollment[200];
        count = 0;
        nextId = 1;
    }

    public Enrollment add(Student student, Course course, String semester) {
        boolean duplicate = Arrays.stream(enrollments, 0, count)
                                  .anyMatch(enrollment -> enrollment.getStudent().getId() == student.getId()
                                          && enrollment.getCourse().getId() == course.getId()
                                          && enrollment.getSemester().equals(semester));
        if (duplicate) {
            System.out.println("Студент вже записаний на цей курс у цьому семестрі.");
            return null;
        }
        Enrollment enrollment = new Enrollment(nextId++, student, course, semester);
        enrollments[count++] = enrollment;
        return enrollment;
    }

    public void showAll() {
        if (count == 0) {
            System.out.println("Список зарахувань порожній.");
            return;
        }
        Arrays.stream(enrollments, 0, count).forEach(System.out::println);
    }

    public Enrollment findById(int id) {
        return Arrays.stream(enrollments, 0, count)
                     .filter(enrollment -> enrollment.getId() == id)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Зарахування з ID " + id + " не знайдено"));
    }

    public void setGrade(int id, Grade grade) {
        findById(id).setGrade(grade);
    }

    public void markPaid(int id) {
        findById(id).markAsPaid();
    }

    public void showTranscript(Student student) {
        System.out.println("Транскрипт: " + student.getName() + " ===");
        long enrollmentCount = Arrays.stream(enrollments, 0, count)
                                     .filter(enrollment -> enrollment.getStudent().getId() == student.getId())
                                     .peek(System.out::println)
                                     .count();
        if (enrollmentCount == 0) {
            System.out.println("Зарахувань немає.");
        } else {
            double gpa = GPAUtils.calculateGPA(student, enrollments, count);
            System.out.printf("GPA: %.2f%n", gpa);
        }
    }

    public void showUnpaid() {
        long unpaidCount = Arrays.stream(enrollments, 0, count)
                                 .filter(enrollment -> !enrollment.isPaid())
                                 .peek(System.out::println)
                                 .count();
        if (unpaidCount == 0) System.out.println("Всі зарахування оплачені.");
    }

    public double averageGPAByCourse(Course course) {
        return Arrays.stream(enrollments, 0, count)
                     .filter(enrollment -> enrollment.getCourse()
                                                     .getId() == course.getId() && enrollment.getGrade() != Grade.NA)
                     .mapToDouble(enrollment -> enrollment.getGrade().getPoints())
                     .average()
                     .orElse(0.0);
    }

    public int getCount() {
        return count;
    }

    public Enrollment[] getEnrollments() {
        return enrollments;
    }
}
