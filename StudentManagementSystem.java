import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    private static final String FILE_NAME = "students.txt";
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadStudentsFromFile();

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
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    searchStudentByName(name);
                    break;
                case 5:
                    saveStudentsToFile();
                    System.out.println("Data saved. Exiting program...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Add Student");
        System.out.println("2. Search Student by ID");
        System.out.println("3. Delete Student by ID");
        System.out.println("4. Search Student by Name");
        System.out.println("5. Sort Students by Name");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
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
        saveStudentsToFile();

        System.out.println("Student added successfully.");
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
                saveStudentsToFile();
                System.out.println("Student deleted successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Handles invalid numeric input safely
    private static int getValidIntegerInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Enter a valid number: ");
                scanner.next();
            }
        }
    }

    // Checks if a student with given ID already exists
    private static boolean isStudentExists(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Saves all student data to file
    private static void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(
                        student.getId() + "," +
                        student.getName() + "," +
                        student.getAge()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    // Loads student data from file at program start
    private static void loadStudentsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
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
