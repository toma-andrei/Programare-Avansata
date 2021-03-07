package Optional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    private List<String> studentPreferences = new ArrayList<>();
    private float score;

    public Student(String name, float score, String... pref) {
        this.name = name;
        this.score = score;
        Collections.addAll(studentPreferences, pref);
    }

    public String getName() {
        return name;
    }

    public List<String> getStudentPreferences() {
        return studentPreferences;
    }

    public void addPreference(String preference) {
        studentPreferences.add(preference);
    }

    public float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Student stud) {
        return this.name.compareTo(stud.getName());
    }
}
