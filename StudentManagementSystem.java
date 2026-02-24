import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    // Stores all student records
    private static ArrayList<Student> studentList = new ArrayList<Student>();

    // Scanner for reading user input
    private static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            printMainMenu();
            int menuChoice = readInteger();

            switch (menuChoice) {
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
                    inputScanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }

    // ================= MENU =================

    private static void printMainMenu() {
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
        int studentId = readInteger();

        if (studentId <= 0) {
            System.out.println("ID must be a positive number.");
            return;
        }

        if (findStudentById(studentId) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String studentName = readNonEmptyString();

        System.out.print("Enter Student Age: ");
        int studentAge = readInteger();

        if (studentAge <= 0) {
            System.out.println("Age must be greater than zero.");
            return;
        }

        System.out.print("Enter Student Marks (0 - 100): ");
        int studentMarks = readInteger();

        if (studentMarks < 0 || studentMarks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        studentList.add(new Student(studentId, studentName, studentAge, studentMarks));
        System.out.println("Student added successfully.");
    }

    // ================= UPDATE STUDENT =================

    private static void updateStudent() {

        if (studentList.isEmpty()) {
            System.out.println("No students available to update.");
            return;
        }

        System.out.print("Enter Student ID to update: ");
        int studentId = readInteger();

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String newName = readNonEmptyString();

        System.out.print("Enter New Age: ");
        int newAge = readInteger();

        if (newAge <= 0) {
            System.out.println("Invalid age. Update cancelled.");
            return;
        }

        System.out.print("Enter New Marks (0 - 100): ");
        int newMarks = readInteger();

        if (newMarks < 0 || newMarks > 100) {
            System.out.println("Invalid marks. Update cancelled.");
            return;
        }

        student.setName(newName);
        student.setAge(newAge);
        student.setMarks(newMarks);

        System.out.println("Student updated successfully.");
    }

    // ================= DELETE STUDENT =================

    private static void deleteStudent() {

        if (studentList.isEmpty()) {
            System.out.println("No students available to delete.");
            return;
        }

        System.out.print("Enter Student ID to delete: ");
        int studentId = readInteger();

        Student student = findStudentById(studentId);

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

        int searchChoice = readInteger();

        if (searchChoice == 1) {
            System.out.print("Enter Student ID: ");
            int studentId = readInteger();

            Student student = findStudentById(studentId);

            if (student == null)
                System.out.println("Student not found.");
            else
                System.out.println(student);
        }

        else if (searchChoice == 2) {
            System.out.print("Enter Student Name: ");
            String name = readNonEmptyString();

            boolean isFound = false;

            for (Student s : studentList) {
                if (s.getName().equalsIgnoreCase(name)) {
                    System.out.println(s);
                    isFound = true;
                }
            }

            if (!isFound)
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

        int sortChoice = readInteger();

        if (sortChoice == 1) {
            studentList.sort(StudentComparators.byName);
            System.out.println("Students sorted by Name.");
        }

        else if (sortChoice == 2) {
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

    // ================= SAFE INPUT METHODS =================

    // Reads integer safely (prevents crash)
    private static int readInteger() {
        while (true) {
            try {
                int value = inputScanner.nextInt();
                inputScanner.nextLine(); // clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Enter a valid number: ");
                inputScanner.next();
            }
        }
    }

    // Reads non-empty string safely
    private static String readNonEmptyString() {
        String value = inputScanner.nextLine().trim();

        if (value.isEmpty()) {
            System.out.print("Input cannot be empty. Enter again: ");
            return readNonEmptyString();
        }

        return value;
    }

    // ================= UTILITY =================

    private static Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }
                }
