package university.services;

import university.entities.Course;
import university.entities.Teacher;

import java.util.Arrays;

public class CourseService {

    private Course[] courses;
    private int count;
    private int nextId;

    public CourseService() {
        courses = new Course[100];
        count = 0;
        nextId = 1;
    }

    public Course addCourse(String name, int credits, Teacher teacher) {
        Course course = new Course(nextId++, name, credits, teacher);
        courses[count++] = course;
        return course;
    }

    public void showAll() {
        if (count == 0) {
            System.out.println("Список курсів порожній.");
            return;
        }
        Arrays.stream(courses, 0, count).forEach(System.out::println);
    }

    public Course findById(int id) {
        return Arrays.stream(courses, 0, count)
                     .filter(course -> course.getId() == id)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Курс з ID " + id + " не знайдено"));
    }

    public boolean delete(int id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId() == id) {
                courses[i] = courses[count - 1];
                courses[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    public boolean update(int id, String name, int credits, Teacher teacher) {
        Course course = findById(id);
        course.setName(name);
        course.setCredits(credits);
        course.setTeacher(teacher);
        return true;
    }

    public void showByTeacher(int teacherId) {
        long found = Arrays.stream(courses, 0, count)
                           .filter(course -> course.getTeacher() != null && course.getTeacher().getId() == teacherId)
                           .peek(System.out::println)
                           .count();
        if (found == 0) System.out.println("Курсів для цього викладача не знайдено.");
    }

    public int getCount() {
        return count;
    }

    public Course[] getCourses() {
        return courses;
    }
}
