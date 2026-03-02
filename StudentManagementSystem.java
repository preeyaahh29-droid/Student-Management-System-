import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentService service = new StudentService();

    public static void main(String[] args) {

        while (true) {
            showMenu();
            int choice = getValidInteger();

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
                    searchById();
                    break;

                case 5:
                    searchByName();
                    break;

                case 6:
                    service.displayStudents();
                    break;

                case 7:
                    sortMenu();
                    break;

                case 8:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Search by ID");
        System.out.println("5. Search by Name");
        System.out.println("6. Display Students");
        System.out.println("7. Sort Students");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addStudent() {

        System.out.print("Enter ID: ");
        int id = getValidInteger();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Age: ");
        int age = getValidInteger();

        System.out.print("Enter Marks: ");
        int marks = getValidInteger();

        service.addStudent(new Student(id, name, age, marks));
    }

    private static void updateStudent() {

        System.out.print("Enter ID to update: ");
        int id = getValidInteger();

        System.out.print("Enter new name: ");
        String name = scanner.next();

        System.out.print("Enter new age: ");
        int age = getValidInteger();

        System.out.print("Enter new marks: ");
        int marks = getValidInteger();

        service.updateStudent(id, name, age, marks);
    }

    private static void deleteStudent() {

        System.out.print("Enter ID to delete: ");
        int id = getValidInteger();

        service.deleteStudent(id);
    }

    private static void searchById() {

        System.out.print("Enter ID: ");
        int id = getValidInteger();

        service.searchStudentById(id);
    }

    private static void searchByName() {

        System.out.print("Enter Name: ");
        String name = scanner.next();

        service.searchStudentByName(name);
    }

    private static void sortMenu() {

        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Marks");
        System.out.print("Choose option: ");

        int option = getValidInteger();

        if (option == 1) {
            service.sortByName();
        } else if (option == 2) {
            service.sortByMarks();
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static int getValidInteger() {

        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Enter a valid number: ");
                scanner.next();
            }
        }
    }
}
