import java.util.Comparator;

public class StudentComparators {

    public static Comparator<Student> byName =
            (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName());

    public static Comparator<Student> byMarks =
            (s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks());
}
