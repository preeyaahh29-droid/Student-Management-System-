import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    private static final String FILE_NAME = "students.txt";
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadStudentsFromFile(); // Load saved students at start

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
                    searchStudentByName();
                    break;
                case 5:
                    sortStudentsByName();
                    break;
                case 6:
                    saveStudentsToFile();
                    System.out.println("Data saved. Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. Search Student by ID");
        System.out.println("3. Delete Student by ID");
        System.out.println("4. Search Student by Name");
        System.out.println("5. Sort Students by Name");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void sortStudentsByName() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        // Sort alphabetically (case-insensitive)
        students.sort((s1, s2) ->
                s1.getName().compareToIgnoreCase(s2.getName()));

        System.out.println("Students sorted by name:");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() +
                               ", Name: " + s.getName() +
                               ", Age: " + s.getAge());
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = getValidIntegerInput();

            if (id <= 0)
                throw new InvalidStudentDataException("ID must be positive.");

            if (isStudentExists(id)) {
                System.out.println("Student with this ID already exists.");
                return;
            }

            scanner.nextLine(); // Clear buffer
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty())
                throw new InvalidStudentDataException("Name cannot be empty.");

            System.out.print("Enter Age: ");
            int age = getValidIntegerInput();

            if (age <= 0)
                throw new InvalidStudentDataException("Age must be positive.");

            students.add(new Student(id, name, age));
            saveStudentsToFile();

            System.out.println("Student added successfully.");

        } catch (InvalidStudentDataException e) {
            System.out.println("Error: " + e.getMessage());
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
                System.out.println("Student Found:");
                System.out.println("ID   : " + student.getId());
                System.out.println("Name : " + student.getName());
                System.out.println("Age  : " + student.getAge());
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

        // Index-based loop avoids ConcurrentModificationException
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                saveStudentsToFile();
                System.out.println("Student deleted successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void searchStudentByName() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        scanner.nextLine(); // Clear buffer
        System.out.print("Enter Name to search: ");
        String name = scanner.nextLine();

        boolean found = false;

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                if (!found) {
                    System.out.println("Matching Students:");
                }
                System.out.println("ID: " + student.getId() +
                                   ", Name: " + student.getName() +
                                   ", Age: " + student.getAge());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with that name.");
        }
    }

    // Handles invalid numeric input safely
    private static int getValidIntegerInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next(); // Discard invalid input
            }
        }
    }

    private static boolean isStudentExists(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private static void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.getId() + "," +
                             student.getName() + "," +
                             student.getAge());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    private static void loadStudentsFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length != 3) continue;

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                students.add(new Student(id, name, age));
            }

        } catch (IOException e) {
            System.out.println("Error loading student data.");
        }
    }
        }
