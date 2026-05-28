package university.util;

import university.entities.Enrollment;
import university.entities.Student;
import university.enums.Grade;

import java.util.Arrays;
import java.util.Comparator;

public class GPAUtils {

    public static double calculateGPA(Student student, Enrollment[] enrollments, int count) {
        return Arrays.stream(enrollments, 0, count)
                     .filter(e -> e.getStudent().getId() == student.getId() && e.getGrade() != Grade.NA)
                     .mapToDouble(e -> e.getGrade().getPoints())
                     .average()
                     .orElse(0.0);
    }

    public static Student[] sortByGpaDesc(Student[] students, int count,
                                          Enrollment[] enrollments, int enrollCount) {
        return Arrays.stream(students, 0, count)
                     .sorted(Comparator.comparingDouble(
                             (Student s) -> calculateGPA(s, enrollments, enrollCount)).reversed())
                     .toArray(Student[]::new);
    }
}
