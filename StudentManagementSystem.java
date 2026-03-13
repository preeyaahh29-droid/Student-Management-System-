/**
 * Main class providing menu-driven UI
 */
public class StudentManagementSystem {

    private static StudentService service = new StudentService();

    public static void main(String[] args) {

        while (true) {

            showMenu();

            int choice = InputHelper.readInt("Enter your choice: ");

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
                    service.displayStudents();
                    break;

                case 6:
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
        System.out.println("4. Search Student by ID");
        System.out.println("5. Display Students");
        System.out.println("6. Exit");
    }

    private static void addStudent() {

        int id = InputHelper.readInt("Enter ID: ");
        String name = InputHelper.readString("Enter Name: ");
        int age = InputHelper.readInt("Enter Age: ");
        int marks = InputHelper.readInt("Enter Marks: ");

        service.addStudent(new Student(id, name, age, marks));
    }

    private static void updateStudent() {

        int id = InputHelper.readInt("Enter ID to update: ");
        String name = InputHelper.readString("Enter new name: ");
        int age = InputHelper.readInt("Enter new age: ");
        int marks = InputHelper.readInt("Enter new marks: ");

        service.updateStudent(id, name, age, marks);
    }

    private static void deleteStudent() {

        int id = InputHelper.readInt("Enter ID to delete: ");

        service.deleteStudent(id);
    }

    private static void searchStudent() {

        int id = InputHelper.readInt("Enter student ID: ");

        service.searchStudentById(id);
    }
}
