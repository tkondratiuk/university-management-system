package university;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import university.entities.Course;
import university.entities.Enrollment;
import university.entities.Student;
import university.entities.Teacher;
import university.enums.Grade;
import university.enums.StudentStatus;
import university.enums.TeacherPosition;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.services.StudentService;
import university.services.TeacherService;
import university.util.GPAUtils;

public class Main {

    static StudentService studentService = new StudentService();
    static TeacherService teacherService = new TeacherService();
    static CourseService courseService = new CourseService();
    static EnrollmentService enrollmentService = new EnrollmentService();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadTestData();
        System.out.println("***Система управління університетом***");

        while (true) {
            System.out.println("\n***МЕНЮ***");
            System.out.println("1. Студенти");
            System.out.println("2. Викладачі");
            System.out.println("3. Курси");
            System.out.println("4. Зарахування");
            System.out.println("5. Звіти");
            System.out.println("0. Вихід");
            System.out.print("> ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> studentsMenu();
                    case 2 -> teachersMenu();
                    case 3 -> coursesMenu();
                    case 4 -> enrollmentsMenu();
                    case 5 -> reportsMenu();
                    case 0 -> {
                        System.out.println("До побачення!");
                        return;
                    }
                    default -> System.out.println("Невірний вибір.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Помилка: введіть число");
            }
        }
    }

    // СТУДЕНТИ
    static void studentsMenu() {
        System.out.println("***Студенти***");
        System.out.println("1. Додати");
        System.out.println("2. Показати всіх");
        System.out.println("3. Видалити");
        System.out.println("4. Оновити");
        System.out.println("5. Змінити статус");
        System.out.println("6. Фільтр за статусом");
        System.out.println("7. Сортування за іменем");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Ім'я: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Рік навчання (1-6): ");
                    int year = Integer.parseInt(scanner.nextLine());
                    Student student = studentService.addStudent(name, email, year);
                    System.out.println("Додано: " + student);
                }
                case 2 -> studentService.showAll();
                case 3 -> {
                    System.out.print("ID: ");
                    int studentId = Integer.parseInt(scanner.nextLine());
                    if (studentService.delete(studentId)) {
                        System.out.println("Видалено.");
                    } else {
                        System.out.println("Не знайдено.");
                    }
                }
                case 4 -> {
                    System.out.print("ID: ");
                    Student student = studentService.findById(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Нове ім'я: ");
                    String name = scanner.nextLine();
                    System.out.print("Новий email: ");
                    String email = scanner.nextLine();
                    System.out.print("Новий рік: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    studentService.update(student.getId(), name, email, year);
                    System.out.println("Оновлено.");
                }
                case 5 -> {
                    System.out.print("ID: ");
                    Student student = studentService.findById(Integer.parseInt(scanner.nextLine()));
                    System.out.println("ACTIVE / ON_LEAVE / EXPELLED / GRADUATED");
                    System.out.print("Новий статус: ");
                    studentService.changeStatus(student.getId(), StudentStatus.valueOf(scanner.nextLine().toUpperCase()));
                    System.out.println("Статус змінено.");
                }
                case 6 -> {
                    System.out.println("ACTIVE / ON_LEAVE / EXPELLED / GRADUATED");
                    System.out.print("Статус: ");
                    studentService.showByStatus(StudentStatus.valueOf(scanner.nextLine().toUpperCase()));
                }
                case 7 -> studentService.showSortedByName();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Помилка: " + ex.getMessage());
        }
    }

    // ВИКЛАДАЧІ
    static void teachersMenu() {
        System.out.println("***Викладачі***");
        System.out.println("1. Додати");
        System.out.println("2. Показати всіх");
        System.out.println("3. Видалити");
        System.out.println("4. Оновити");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Ім'я: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Посада (1-ASSISTANT  2-LECTURER  3-PROFESSOR): ");
                    int positionChoice = Integer.parseInt(scanner.nextLine());
                    TeacherPosition position = (positionChoice == 1) ? TeacherPosition.ASSISTANT
                            : (positionChoice == 2) ? TeacherPosition.LECTURER : TeacherPosition.PROFESSOR;
                    System.out.print("Зарплата: ");
                    double salary = Double.parseDouble(scanner.nextLine());
                    Teacher teacher = teacherService.addTeacher(name, email, position, salary);
                    System.out.println("Додано: " + teacher);
                }
                case 2 -> teacherService.showAll();
                case 3 -> {
                    System.out.print("ID: ");
                    if (teacherService.delete(Integer.parseInt(scanner.nextLine()))) {
                        System.out.println("Видалено.");
                    } else {
                        System.out.println("Не знайдено.");
                    }
                }
                case 4 -> {
                    System.out.print("ID: ");
                    Teacher teacher = teacherService.findById(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Нове ім'я: ");
                    String name = scanner.nextLine();
                    System.out.print("Новий email: ");
                    String email = scanner.nextLine();
                    System.out.println("Посада (1-ASSISTANT  2-LECTURER  3-PROFESSOR): ");
                    int positionChoice = Integer.parseInt(scanner.nextLine());
                    TeacherPosition position = (positionChoice == 1) ? TeacherPosition.ASSISTANT
                            : (positionChoice == 2) ? TeacherPosition.LECTURER : TeacherPosition.PROFESSOR;
                    System.out.print("Нова зарплата: ");
                    double salary = Double.parseDouble(scanner.nextLine());
                    teacherService.update(teacher.getId(), name, email, position, salary);
                    System.out.println("Оновлено.");
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Помилка: " + ex.getMessage());
        }
    }

    // КУРСИ
    static void coursesMenu() {
        System.out.println("***Курси***");
        System.out.println("1. Додати");
        System.out.println("2. Показати всі");
        System.out.println("3. Видалити");
        System.out.println("4. Оновити");
        System.out.println("5. Фільтр за викладачем");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Назва: ");
                    String name = scanner.nextLine();
                    System.out.print("Кредити: ");
                    int credits = Integer.parseInt(scanner.nextLine());
                    teacherService.showAll();
                    System.out.print("ID викладача: ");
                    int teacherId = Integer.parseInt(scanner.nextLine());
                    Teacher teacher = (teacherId == 0) ? null : teacherService.findById(teacherId);
                    Course course = courseService.addCourse(name, credits, teacher);
                    System.out.println("Додано: " + course);
                }
                case 2 -> courseService.showAll();
                case 3 -> {
                    System.out.print("ID: ");
                    if (courseService.delete(Integer.parseInt(scanner.nextLine()))) {
                        System.out.println("Видалено.");
                    } else {
                        System.out.println("Не знайдено.");
                    }
                }
                case 4 -> {
                    System.out.print("ID: ");
                    Course course = courseService.findById(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Нова назва: ");
                    String name = scanner.nextLine();
                    System.out.print("Нова кількість кредитів: ");
                    int credits = Integer.parseInt(scanner.nextLine());
                    teacherService.showAll();
                    System.out.print("ID нового викладача: ");
                    int teacherId = Integer.parseInt(scanner.nextLine());
                    Teacher teacher = (teacherId == 0) ? course.getTeacher() : teacherService.findById(teacherId);
                    courseService.update(course.getId(), name, credits, teacher);
                    System.out.println("Оновлено.");
                }
                case 5 -> {
                    teacherService.showAll();
                    System.out.print("ID викладача: ");
                    courseService.showByTeacher(Integer.parseInt(scanner.nextLine()));
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Помилка: " + ex.getMessage());
        }
    }

    // ЗАРАХУВАННЯ
    static void enrollmentsMenu() {
        System.out.println("***Зарахування***");
        System.out.println("1. Записати студента на курс");
        System.out.println("2. Показати всі");
        System.out.println("3. Виставити оцінку");
        System.out.println("4. Позначити оплату");
        System.out.println("5. Транскрипт студента");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    studentService.showAll();
                    System.out.print("ID студента: ");
                    Student student = studentService.findById(Integer.parseInt(scanner.nextLine()));
                    courseService.showAll();
                    System.out.print("ID курсу: ");
                    Course course = courseService.findById(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Семестр: ");
                    String semester = scanner.nextLine();
                    enrollmentService.add(student, course, semester);
                    System.out.println("Записано.");
                }
                case 2 -> enrollmentService.showAll();
                case 3 -> {
                    enrollmentService.showAll();
                    System.out.print("ID зарахування: ");
                    int enrollmentId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Оцінка (A / B / C / D / E / NA): ");
                    Grade grade = Grade.valueOf(scanner.nextLine().toUpperCase());
                    enrollmentService.setGrade(enrollmentId, grade);
                    System.out.println("Оцінку виставлено.");
                }
                case 4 -> {
                    enrollmentService.showAll();
                    System.out.print("ID зарахування: ");
                    enrollmentService.markPaid(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Оплачено.");
                }
                case 5 -> {
                    studentService.showAll();
                    System.out.print("ID студента: ");
                    Student student = studentService.findById(Integer.parseInt(scanner.nextLine()));
                    enrollmentService.showTranscript(student);
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Помилка: " + ex.getMessage());
        }
    }


    // ЗВІТИ
    static void reportsMenu() {
        System.out.println("***Звіти***");
        System.out.println("1. Пошук студента за іменем або email");
        System.out.println("2. Неоплачені зарахування");
        System.out.println("3. Середній GPA по курсу");
        System.out.println("4. Топ-N студентів за GPA");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Частина імені або email: ");
                    String query = scanner.nextLine().toLowerCase();
                    long found = Arrays.stream(studentService.getStudents(), 0, studentService.getCount())
                                       .filter(student -> student.getName().toLowerCase().contains(query)
                                               || student.getEmail().toLowerCase().contains(query))
                                       .peek(System.out::println)
                                       .count();
                    if (found == 0) {
                        System.out.println("Нікого не знайдено");
                    }
                }
                case 2 -> enrollmentService.showUnpaid();
                case 3 -> {
                    courseService.showAll();
                    System.out.print("ID курсу: ");
                    Course course = courseService.findById(Integer.parseInt(scanner.nextLine()));
                    double averageGpa = enrollmentService.averageGPAByCourse(course);
                    System.out.printf("Середній GPA по курсу '%s': %.2f%n", course.getName(), averageGpa);
                }
                case 4 -> {
                    System.out.print("Кількість : ");
                    int topCount = Integer.parseInt(scanner.nextLine());
                    Student[] sortedStudents = GPAUtils.sortByGpaDesc(
                            studentService.getStudents(), studentService.getCount(),
                            enrollmentService.getEnrollments(), enrollmentService.getCount());
                    int limit = Math.min(topCount, studentService.getCount());
                    Enrollment[] enrollments = enrollmentService.getEnrollments();
                    int enrollCount = enrollmentService.getCount();
                    System.out.println("--- Топ-" + limit + " ---");
                    IntStream.range(0, limit).forEach(index ->
                            System.out.printf("%d. %s — GPA: %.2f%n",
                                    index + 1, sortedStudents[index].getName(),
                                    GPAUtils.calculateGPA(sortedStudents[index], enrollments, enrollCount)));
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Помилка: " + ex.getMessage());
        }
    }

    // ТЕСТОВІ ДАНІ
    static void loadTestData() {
        Teacher teacher1 = teacherService.addTeacher("Іваненко О.П.", "ivan@univ.ua", TeacherPosition.PROFESSOR, 25000);
        Teacher teacher2 = teacherService.addTeacher("Петренко І.В.", "petr@univ.ua", TeacherPosition.LECTURER, 18000);
        Teacher teacher3 = teacherService.addTeacher("Ковальчук А.М.", "koval@univ.ua", TeacherPosition.ASSISTANT, 12000);

        Student student1 = studentService.addStudent("Мороз Олексій", "moroz@gmail.com", 1);
        Student student2 = studentService.addStudent("Шевченко Марія", "shevch@gmail.com", 2);
        Student student3 = studentService.addStudent("Коваль Дмитро", "koval@gmail.com", 1);
        Student student4 = studentService.addStudent("Ткач Ольга", "tkach@gmail.com", 3);
        Student student5 = studentService.addStudent("Бондар Микола", "bondar@gmail.com", 2);

        Course course1 = courseService.addCourse("Математика", 4, teacher1);
        Course course2 = courseService.addCourse("Програмування", 5, teacher2);
        Course course3 = courseService.addCourse("Фізика", 3, teacher3);

        enrollmentService.add(student1, course1, "2024-1");
        enrollmentService.setGrade(1, Grade.A);
        enrollmentService.markPaid(1);
        enrollmentService.add(student1, course2, "2024-1");
        enrollmentService.setGrade(2, Grade.B);
        enrollmentService.markPaid(2);
        enrollmentService.add(student2, course1, "2024-1");
        enrollmentService.setGrade(3, Grade.B);
        enrollmentService.add(student2, course2, "2024-1");
        enrollmentService.setGrade(4, Grade.A);
        enrollmentService.markPaid(4);
        enrollmentService.add(student3, course2, "2024-1");
        enrollmentService.setGrade(5, Grade.C);
        enrollmentService.add(student4, course1, "2024-1");
        enrollmentService.setGrade(6, Grade.A);
        enrollmentService.markPaid(6);
        enrollmentService.add(student5, course3, "2024-1");
        enrollmentService.setGrade(7, Grade.D);
    }
}
