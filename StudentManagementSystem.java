import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getValidIntegerInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    searchStudentById();
                    break;
                case 3:
                    deleteStudentById();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. Search Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = getValidIntegerInput();

            // Prevent duplicate student IDs
            if (isStudentExists(id)) {
                System.out.println("Student with this ID already exists.");
                return;
            }

            System.out.print("Enter Name: ");
            String name = scanner.next();

            System.out.print("Enter Age: ");
            int age = getValidIntegerInput();

            students.add(new Student(id, name, age));
            System.out.println("Student added successfully.");

        } catch (Exception e) {
            System.out.println("Failed to add student. Invalid input.");
        }
    }

    private static void searchStudentById() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter ID to search: ");
        int id = getValidIntegerInput();

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Age: " + student.getAge());
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void deleteStudentById() {
        if (students.isEmpty()) {
            System.out.println("No students to delete.");
            return;
        }

        System.out.print("Enter ID to delete: ");
        int id = getValidIntegerInput();

        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                System.out.println("Student deleted successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Validates integer input and prevents program crash
    private static int getValidIntegerInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Enter a valid number: ");
                scanner.next(); // clear invalid input
            }
        }
    }

    // Checks whether a student with given ID already exists
    private static boolean isStudentExists(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }
            }
