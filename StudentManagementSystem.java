import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    // List to store students
    private static ArrayList<Student> studentList = new ArrayList<Student>();

    // Scanner for user input
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            displayMenu();
            int choice = readIntegerInput();

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
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ================= MENU =================

    private static void displayMenu() {
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

    // ================= ADD STUDENT =================

    private static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = readIntegerInput();

        // Prevent duplicate IDs
        if (findStudentById(id) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = readLineInput();

        System.out.print("Enter Student Age: ");
        int age = readIntegerInput();

        if (age <= 0) {
            System.out.println("Age must be greater than zero.");
            return;
        }

        System.out.print("Enter Student Marks (0–100): ");
        int marks = readIntegerInput();

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        studentList.add(new Student(id, name, age, marks));
        System.out.println("Student added successfully.");
    }

    // ================= UPDATE STUDENT =================

    private static void updateStudent() {

        if (studentList.isEmpty()) {
            System.out.println("No students available to update.");
            return;
        }

        System.out.print("Enter Student ID to update: ");
        int id = readIntegerInput();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        try {
            System.out.println("Enter new details:");

            System.out.print("New Name: ");
            String newName = readLineInput();

            System.out.print("New Age: ");
            int newAge = readIntegerInput();

            if (newAge <= 0) {
                System.out.println("Invalid age. Update cancelled.");
                return;
            }

            System.out.print("New Marks (0–100): ");
            int newMarks = readIntegerInput();

            if (newMarks < 0 || newMarks > 100) {
                System.out.println("Invalid marks. Update cancelled.");
                return;
            }

            // Apply updates
            student.setName(newName);
            student.setAge(newAge);
            student.setMarks(newMarks);

            System.out.println("Student updated successfully.");

        } catch (Exception e) {
            System.out.println("Error occurred while updating student.");
        }
    }

    // ================= DELETE STUDENT =================

    private static void deleteStudent() {

        if (studentList.isEmpty()) {
            System.out.println("No students available to delete.");
            return;
        }

        System.out.print("Enter Student ID to delete: ");
        int id = readIntegerInput();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        studentList.remove(student);
        System.out.println("Student deleted successfully.");
    }

    // ================= SEARCH STUDENT =================

    private static void searchStudent() {

        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Search By:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.print("Choose option: ");

        int option = readIntegerInput();

        if (option == 1) {
            System.out.print("Enter Student ID: ");
            int id = readIntegerInput();

            Student student = findStudentById(id);

            if (student == null)
                System.out.println("Student not found.");
            else
                System.out.println(student);
        }

        else if (option == 2) {
            System.out.print("Enter Student Name: ");
            String name = readLineInput();

            boolean found = false;

            for (Student s : studentList) {
                if (s.getName().equalsIgnoreCase(name)) {
                    System.out.println(s);
                    found = true;
                }
            }

            if (!found)
                System.out.println("Student not found.");
        }

        else {
            System.out.println("Invalid search option.");
        }
    }

    // ================= SORT STUDENTS =================

    private static void sortStudents() {

        if (studentList.isEmpty()) {
            System.out.println("No students available to sort.");
            return;
        }

        System.out.println("Sort By:");
        System.out.println("1. Name");
        System.out.println("2. Marks");
        System.out.print("Choose option: ");

        int option = readIntegerInput();

        if (option == 1) {
            studentList.sort(StudentComparators.byName);
            System.out.println("Students sorted by Name.");
        }

        else if (option == 2) {
            studentList.sort(StudentComparators.byMarks);
            System.out.println("Students sorted by Marks.");
        }

        else {
            System.out.println("Invalid sorting option.");
        }
    }

    // ================= DISPLAY =================

    private static void displayStudents() {

        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\n===== Student List =====");

        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    // ================= INPUT HANDLING =================

    // Reads integer safely
    private static int readIntegerInput() {
        while (true) {
            try {
                int value = input.nextInt();
                input.nextLine(); // clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Enter a valid number: ");
                input.next();
            }
        }
    }

    // Reads non-empty string
    private static String readLineInput() {
        String value = input.nextLine().trim();

        if (value.isEmpty()) {
            System.out.print("Input cannot be empty. Enter again: ");
            return readLineInput();
        }

        return value;
    }

    // ================= UTILITY METHOD =================

    // Finds student by ID
    private static Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
                }
