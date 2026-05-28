package university.services;

import university.entities.Teacher;
import university.enums.TeacherPosition;

import java.util.Arrays;

public class TeacherService {

    private Teacher[] teachers;
    private int count;
    private int nextId;

    public TeacherService() {
        teachers = new Teacher[100];
        count = 0;
        nextId = 1;
    }

    public Teacher addTeacher(String name, String email, TeacherPosition position, double salary) {
        Teacher teacher = new Teacher(nextId++, name, email, position, salary);
        teachers[count++] = teacher;
        return teacher;
    }

    public void showAll() {
        if (count == 0) {
            System.out.println("Список викладачів порожній.");
            return;
        }
        Arrays.stream(teachers, 0, count)
              .forEach(System.out::println);
    }

    public Teacher findById(int teacherId) {
        return Arrays.stream(teachers, 0, count)
                     .filter(teacher -> teacher.getId() == teacherId)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Викладача з ID " + teacherId + " не знайдено"));
    }

    public boolean delete(int teacherId) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId() == teacherId) {
                teachers[i] = teachers[count - 1];
                teachers[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    public boolean update(int teacherId, String name, String email, TeacherPosition position, double salary) {
        Teacher teacher = findById(teacherId);
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPosition(position);
        teacher.setSalary(salary);
        return true;
    }

    public int getCount() {
        return count;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }
}
