import java.util.Comparator;

public class StudentComparators {

    // Sort by Name (Alphabetical)
    public static Comparator<Student> byName = (s1, s2) ->
            s1.getName().compareToIgnoreCase(s2.getName());

    // Sort by Marks (Highest first)
    public static Comparator<Student> byMarks = (s1, s2) ->
            Integer.compare(s2.getMarks(), s1.getMarks());
}
