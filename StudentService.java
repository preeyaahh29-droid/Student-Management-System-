import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void deleteStudent(int id) {
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully.");
    }

    public void updateStudent(int id, String name, int age, int marks) {
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.setName(name);
        student.setAge(age);
        student.setMarks(marks);

        System.out.println("Student updated successfully.");
    }

    public void searchStudentById(int id) {
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(student);
        }
    }

    public void searchStudentByName(String name) {
        boolean found = false;

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                System.out.println(s);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void sortByName() {
        students.sort(StudentComparators.byName);
        System.out.println("Students sorted by name.");
    }

    public void sortByMarks() {
        students.sort(StudentComparators.byMarks);
        System.out.println("Students sorted by marks.");
    }

    private Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
