package com.company;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {


        /***
         * creeaza un Stream cu elementele specificate in metoda of() iar acesta este "pus" intr-o lista.
         */
        List<Student> students = Stream.of(new Student("Toma Andrei", "Liceul Lascar Rosetti", "Colegiul Mihai Eminescu", "Liceul Dimitrie Leonida"),
                new Student("Matei Marian", "Liceul Lascar Rosetti", "Colegiul Mihai Eminescu", "Liceul Dimitrie Leonida"),
                new Student("Ionut Tache", "Liceul Lascar Rosetti", "Colegiul Mihai Eminescu"),
                new Student("Costel Dragan", "Liceul Lascar Rosetti", "Liceul Dimitrie Leonida"))
                .collect(Collectors.toList());

        List<School> schools = Stream.of(new School("Liceul Lascar Rosetti", 1, "Costel Dragan", "Toma Andrei", "Matei Marian", "Ionut Tache"),
                new School("Colegiul Mihai Eminescu", 2, "Toma Andrei", "Ionut Tache", "Matei Marian"),
                new School("Liceul Dimitrie Leonida", 2, "Toma Andrei", "Matei Marian", "Costel Dragan"))
                .collect(Collectors.toList());

        System.out.println(students + "\n");
        System.out.println(schools + "\n");

        List<Student> linkedListStudents = new LinkedList<>();

        linkedListStudents.addAll(students);

        /***
         *foloseste o expresie lambda pentru a sorta studentii dupa nume.
         */
        linkedListStudents.sort((Student stud1, Student stud2) -> stud1.getName().compareTo(stud2.getName()));
        //Collections.sort(linkedListStudents, Comparator.comparing(Student::getName));

        System.out.println(linkedListStudents);

        /***
         * clasa School trebuie sa implementeze interfata Comparable si sa suprascrie metoda compareTo() (elementele trebuie
         *              sa fie comparabile pentru a putea fi asezate in ordine "crescatoare").
         */

        Set<School> schoolSet = new TreeSet<School>();

        schoolSet.addAll(schools);

        System.out.println(schoolSet);
        System.out.println();

        Map<Student, List<String>> studentPreferencesHashMap = new HashMap<>();

        for (Student student1 : students) {
            studentPreferencesHashMap.put(student1, student1.getStudentPreferences());
        }

        System.out.println(studentPreferencesHashMap);
        System.out.println();

        Map<Student, List<String>> studentPreferencesTreeMap = new TreeMap<>();

        for (Student student : students) {
            studentPreferencesTreeMap.put(student, student.getStudentPreferences());
        }

        System.out.println(studentPreferencesTreeMap);


    }
}
