import java.util.Comparator;

public class StudentComparators {

    public static Comparator<Student> byName = new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s1.getName().compareToIgnoreCase(s2.getName());
        }
    };

    public static Comparator<Student> byMarks = new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s2.getMarks() - s1.getMarks(); // descending
        }
    };
}
