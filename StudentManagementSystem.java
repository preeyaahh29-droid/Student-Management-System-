import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    private static ArrayList<Student> students = new ArrayList<Student>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            showMenu();
            int choice = readInteger();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    deleteStudent();
                    break;

                case 4:
                    searchStudent();
                    break;

                case 5:
                    sortStudents();
                    break;

                case 6:
                    displayStudents();
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ================= MENU =================

    private static void showMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Student");
        System.out.println("5. Sort Students");
        System.out.println("6. Display Students");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // ================= ADD =================

    private static void addStudent() {

        System.out.print("Enter ID: ");
        int id = readInteger();

        if (findStudentById(id) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = readLine();

        System.out.print("Enter Age: ");
        int age = readInteger();

        if (age <= 0) {
            System.out.println("Invalid age.");
            return;
        }

        System.out.print("Enter Marks: ");
        int marks = readInteger();

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        students.add(new Student(id, name, age, marks));
        System.out.println("Student added successfully.");
    }

    // ================= UPDATE =================

    private static void updateStudent() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter ID to update: ");
        int id = readInteger();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String name = readLine();
        student.setName(name);

        System.out.print("Enter New Age: ");
        int age = readInteger();
        student.setAge(age);

        System.out.print("Enter New Marks: ");
        int marks = readInteger();
        student.setMarks(marks);

        System.out.println("Student updated successfully.");
    }

    // ================= DELETE =================

    private static void deleteStudent() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter ID to delete: ");
        int id = readInteger();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully.");
    }

    // ================= SEARCH =================

    private static void searchStudent() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Search By:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.print("Choose option: ");

        int option = readInteger();

        if (option == 1) {
            System.out.print("Enter ID: ");
            int id = readInteger();

            Student student = findStudentById(id);

            if (student == null)
                System.out.println("Student not found.");
            else
                System.out.println(student);
        }

        else if (option == 2) {
            System.out.print("Enter Name: ");
            String name = readLine();

            boolean found = false;

            for (Student s : students) {
                if (s.getName().equalsIgnoreCase(name)) {
                    System.out.println(s);
                    found = true;
                }
            }

            if (!found)
                System.out.println("Student not found.");
        }

        else {
            System.out.println("Invalid option.");
        }
    }

    // ================= SORT =================

    private static void sortStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Sort By:");
        System.out.println("1. Name");
        System.out.println("2. Marks");
        System.out.print("Choose option: ");

        int option = readInteger();

        if (option == 1) {
            students.sort(StudentComparators.byName);
            System.out.println("Sorted by Name.");
        }

        else if (option == 2) {
            students.sort(StudentComparators.byMarks);
            System.out.println("Sorted by Marks.");
        }

        else {
            System.out.println("Invalid option.");
        }
    }

    // ================= DISPLAY =================

    private static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\n===== Student List =====");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ================= INPUT METHODS =================

    private static int readInteger() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Enter a valid number: ");
                scanner.next();
            }
        }
    }

    private static String readLine() {
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.print("Input cannot be empty. Enter again: ");
            return readLine();
        }
        return input;
    }

    // ================= UTILITY =================

    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
            }
