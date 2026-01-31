public class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        if (id <= 0 || age <= 0) {
            throw new IllegalArgumentException("ID and age must be positive");
        }
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
