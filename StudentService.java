import java.util.ArrayList;

public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                throw new IllegalArgumentException("Student with this ID already exists");
            }
        }
        students.add(student);
        System.out.println("Student added successfully");
    }

    public void deleteStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                System.out.println("Student deleted successfully");
                return;
            }
        }
        throw new IllegalArgumentException("Student not found");
    }

    public void searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " + s.getName());
                System.out.println("Age: " + s.getAge());
                return;
            }
        }
        throw new IllegalArgumentException("Student not found");
    }
}
public void searchStudentByName(String name) {
    boolean found = false;

    for (Student student : students) {
        if (student.getName().equalsIgnoreCase(name)) {
            System.out.println(
                "ID: " + student.getId() +
                ", Name: " + student.getName() +
                ", Age: " + student.getAge()
            );
            found = true;
        }
    }

    if (!found) {
        System.out.println("Student not found with name: " + name);
    }
}
