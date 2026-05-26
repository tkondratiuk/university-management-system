package university.entities;


public class Person {

    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Невірний формат email");
        }
        this.id = id;
        this.name = name.trim();
        this.email = email.trim();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Невірний формат email");
        }
        this.email = email.trim();
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Ім'я: " + name + " | Email: " + email;
    }
}
