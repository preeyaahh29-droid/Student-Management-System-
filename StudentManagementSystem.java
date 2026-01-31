import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    sc.nextLine(); // clear buffer
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    Student student = new Student(id, name, age);
                    service.addStudent(student);

                } else if (choice == 2) {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    service.deleteStudent(id);

                } else if (choice == 3) {
                    System.out.print("Enter ID to search: ");
                    int id = sc.nextInt();
                    service.searchStudent(id);

                } else if (choice == 4) {
                    System.out.println("Exiting program");
                    break;
                } else {
                    System.out.println("Invalid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter valid numeric input");
                sc.nextLine(); // clear wrong input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}
