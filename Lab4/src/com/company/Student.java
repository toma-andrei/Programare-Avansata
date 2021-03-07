package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Student implements Comparable<Student> {
    private String name;
    private List<String> studentPreferences = new ArrayList<>();

    public Student(String name, String... pref) {
        this.name = name;
        for (String pr : pref) studentPreferences.add(pr);
    }

    public String getName() {
        return name;
    }

    public List<String> getStudentPreferences() {
        return studentPreferences;
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
