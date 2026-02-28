import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            displayMainMenu();
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
                    searchStudentMenu();
                    break;

                case 5:
                    sortStudentsMenu();
                    break;

                case 6:
                    printStudentList();
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Please select from menu.");
            }
        }
    }

    private static void displayMainMenu() {

        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Student");
        System.out.println("5. Sort Students");
        System.out.println("6. Display All Students");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {

        System.out.print("Enter Student ID: ");
        int studentId = readInteger();

        if (!isValidId(studentId)) {
            System.out.println("Invalid ID. ID must be positive.");
            return;
        }

        if (findStudentById(studentId) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String studentName = inputScanner.next();

        System.out.print("Enter Age: ");
        int studentAge = readInteger();

        if (!isValidAge(studentAge)) {
            System.out.println("Invalid age. Age must be between 1 and 120.");
            return;
        }

        System.out.print("Enter Marks: ");
        int studentMarks = readInteger();

        if (!isValidMarks(studentMarks)) {
            System.out.println("Invalid marks. Enter between 0 and 100.");
            return;
        }

        studentList.add(new Student(studentId, studentName, studentAge, studentMarks));

        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {

        System.out.print("Enter Student ID to update: ");
        int studentId = readInteger();

        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String newName = inputScanner.next();

        System.out.print("Enter New Age: ");
        int newAge = readInteger();

        if (!isValidAge(newAge)) {
            System.out.println("Invalid age.");
            return;
        }

        System.out.print("Enter New Marks: ");
        int newMarks = readInteger();

        if (!isValidMarks(newMarks)) {
            System.out.println("Invalid marks.");
            return;
        }

        student.setName(newName);
        student.setAge(newAge);
        student.setMarks(newMarks);

        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {

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

    private static void searchStudentMenu() {

        System.out.println("\nSearch Student By:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.print("Enter choice: ");

        int option = readInteger();

        if (option == 1) {
            searchStudentById();
        } else if (option == 2) {
            searchStudentByName();
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void searchStudentById() {

        System.out.print("Enter Student ID: ");
        int studentId = readInteger();

        Student student = findStudentById(studentId);

        if (student == null)
            System.out.println("Student not found.");
        else
            System.out.println(student);
    }

    private static void searchStudentByName() {

        System.out.print("Enter Student Name: ");
        String name = inputScanner.next();

        boolean found = false;

        for (Student student : studentList) {

            if (student.getName().equalsIgnoreCase(name)) {

                System.out.println(student);
                found = true;
            }
        }

        if (!found)
            System.out.println("Student not found.");
    }

    private static void sortStudentsMenu() {

        if (studentList.isEmpty()) {
            System.out.println("No students available to sort.");
            return;
        }

        System.out.println("\nSort Students By:");
        System.out.println("1. Name");
        System.out.println("2. Marks");
        System.out.print("Choose option: ");

        int option = readInteger();

        switch (option) {

            case 1:
                studentList.sort(StudentComparators.byName);
                System.out.println("Students sorted by Name.");
                break;

            case 2:
                studentList.sort(StudentComparators.byMarks);
                System.out.println("Students sorted by Marks (Highest first).");
                break;

            default:
                System.out.println("Invalid sorting option.");
        }
    }

    private static void printStudentList() {

        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n===== Student List =====");

        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private static Student findStudentById(int id) {

        for (Student student : studentList) {

            if (student.getId() == id)
                return student;
        }

        return null;
    }

    private static int readInteger() {

        while (true) {

            try {
                return inputScanner.nextInt();
            } catch (InputMismatchException e) {

                System.out.print("Invalid input. Enter a number: ");
                inputScanner.next();
            }
        }
    }

    private static boolean isValidId(int id) {
        return id > 0;
    }

    private static boolean isValidAge(int age) {
        return age > 0 && age <= 120;
    }

    private static boolean isValidMarks(int marks) {
        return marks >= 0 && marks <= 100;
    }
}
