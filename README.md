# University Management System

Консольна програма для управління університетом.

## Структура проекту

```
src/university/
├── Main.java                  — головний клас, меню
├── entities/
│   ├── Person.java            — базовий клас (id, name, email)
│   ├── Student.java           — extends Person (рік, статус)
│   ├── Teacher.java           — extends Person (посада, зарплата)
│   ├── Course.java            — (назва, кредити, викладач)
│   └── Enrollment.java        — implements Payable
├── enums/
│   ├── StudentStatus.java     — ACTIVE, ON_LEAVE, EXPELLED, GRADUATED
│   ├── TeacherPosition.java   — ASSISTANT, LECTURER, PROFESSOR
│   └── Grade.java             — A, B, C, D, E, NA
├── interfaces/
│   └── Payable.java           — isPaid(), markAsPaid()
├── services/
│   ├── StudentService.java
│   ├── TeacherService.java
│   ├── CourseService.java
│   └── EnrollmentService.java
└── util/
    └── GPAUtils.java          — розрахунок GPA, сортування
```

## Функціонал

**Студенти:** додати, показати всіх, видалити, оновити, змінити статус, фільтр за статусом, сортування за іменем

**Викладачі:** додати, показати всіх, видалити, оновити

**Курси:** додати, показати всіх, видалити, оновити, фільтр за викладачем

**Зарахування:** записати на курс, показати, виставити оцінку, позначити оплату, транскрипт студента з GPA

**Звіти:** пошук за іменем/email, неоплачені зарахування, середній GPA по курсу, топ-N студентів за GPA

## Запуск

```bash
javac -d out $(find src -name "*.java")
java -cp out university.Main
```
