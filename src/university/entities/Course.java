package university.entities;

public class Course {

    private int id;
    private String name;
    private int credits;
    private Teacher teacher;

    public Course(int id, String name, int credits, Teacher teacher) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва курсу не може бути порожньою");
        }
        if (credits < 1) {
            throw new IllegalArgumentException("Кількість кредитів має бути більше 0");
        }
        this.id = id;
        this.name = name.trim();
        this.credits = credits;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва курсу не може бути порожньою");
        }
        this.name = name.trim();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits < 1) {
            throw new IllegalArgumentException("Кількість кредитів має бути більше 0");
        }
        this.credits = credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        String teacherName = (teacher != null) ? teacher.getName() : "Немає викладача";
        return "ID: " + id + " | Курс: " + name + " | Кредити: " + credits + " | Викладач: " + teacherName;
    }
}
