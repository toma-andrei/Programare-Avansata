package Optional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.javafaker.Faker;

public class Main {

    public static void main(String[] args) {

        List<Student> students = Stream.of(new Student("Toma Andrei", 10, "Liceul Lascar Rosetti", "Colegiul Mihai Eminescu", "Liceul Dimitrie Leonida"),
                new Student("Matei Marian", 10, "Liceul Lascar Rosetti", "Colegiul Mihai Eminescu", "Liceul Dimitrie Leonida"),
                new Student("Ionut Tache", 10, "Liceul Lascar Rosetti", "Colegiul Mihai Eminescu"),
                new Student("Costel Dragan", 10, "Liceul Lascar Rosetti", "Liceul Dimitrie Leonida"))
                .collect(Collectors.toList());

        List<School> schools = Stream.of(new School("Liceul Lascar Rosetti", 1, "Costel Dragan", "Toma Andrei", "Matei Marian", "Ionut Tache"),
                new School("Colegiul Mihai Eminescu", 2, "Toma Andrei", "Ionut Tache", "Matei Marian"),
                new School("Liceul Dimitrie Leonida", 2, "Toma Andrei", "Matei Marian", "Costel Dragan"))
                .collect(Collectors.toList());

        /**
         * filtreaza studentii care au ca preferinte cele doua licee si transforma streamul in lista.
         */
        List<Student> filteredStudents = students.stream()
                .filter(s -> s.getStudentPreferences()
                        .containsAll(Arrays.asList("Liceul Lascar Rosetti", "Liceul Dimitrie Leonida")))
                .collect(Collectors.toList());


        System.out.println("filtered students are: \n" + filteredStudents + "\n");


        /**
         * filtreaza scolile ce il au pe "Costel Dragan" in capul listei de preferinte
         */
        List<School> filteredSchools = schools.stream()
                .filter(s -> s.getSchoolPreferences().get(0).equals("Costel Dragan"))
                .collect(Collectors.toList());

        System.out.println("filtered schools are: \n" + filteredSchools + "\n");


        List<String> schoolNames = new ArrayList<>();
        List<String> studentNames = new ArrayList<>();

        //numarul de studenti si scoli pentru care se creeaza instante
        int noOfStudents = 20;
        int noOfSchools = 10;

        Faker faker = new Faker();

        for (int i = 0; i < noOfSchools; i++) { // genereaza nume de scoli de mai mult de un cuvant
            String schoolFirstName = faker.artist().name();
            String schoolLastName = faker.name().lastName();

            if (!(schoolNames.contains(schoolFirstName + " " + schoolLastName)))
                schoolNames.add(schoolFirstName + " " + schoolLastName);
        }

        for (int i = 0; i < noOfStudents; i++) { // genereaza nume si prenume de studenti
            String studentFirstName = faker.name().firstName();
            String studentLastName = faker.name().lastName();
            if (!(studentNames.contains(studentFirstName + " " + studentLastName)))
                studentNames.add(studentFirstName + " " + studentLastName);
        }

        List<Student> studentInstances = new ArrayList<>();

        /*
         creeaza instante pentru studenti si le adauga preferinte unice +
                    genereaza un scor random (mai mare egal ca 5) pentru fiecare student.
         */
        for (int i = 0; i < studentNames.size(); i++) {

            float randomScore = (float) ((Math.random() * 5) + 5);

            studentInstances.add(new Student(studentNames.get(i), randomScore));

            int noOfPreferedSchool = (int) (Math.random() * (schoolNames.size() - 1) + 1);

            for (int j = 0; j < noOfPreferedSchool; j++) {
                int randomNumber = (int) (Math.random() * (schoolNames.size()));

                if (!(studentInstances.get(i).getStudentPreferences().contains(schoolNames.get(randomNumber)))) {
                    studentInstances.get(i).addPreference(schoolNames.get(randomNumber));
                }
            }
        }

        List<School> schoolInstances = new ArrayList<>();
        //creeaza instante pentru scoli: adauga preferinte unice (din lista de nume de studenti)
        //              si adauga o capacitate random (asa incat fiecare student sa aiba o scoala)
        for (int i = 0; i < schoolNames.size(); i++) {
            int randomCapacity = (int) (Math.random() * (Math.abs(noOfStudents - noOfSchools)) +
                    Math.min(noOfSchools, noOfStudents) +
                    Math.min(noOfSchools, noOfStudents));

            schoolInstances.add(new School(schoolNames.get(i), randomCapacity));

            int noOfPreferedStudents = (int) (Math.random() * (studentNames.size() - 1) + 1);

            for (int j = 0; j < noOfPreferedStudents; j++) {
                int randomNumber = (int) (Math.random() * (studentNames.size()));

                if (!(schoolInstances.get(i).getSchoolPreferences().contains(studentNames.get(randomNumber)))) {
                    schoolInstances.get(i).addPreference(studentNames.get(randomNumber));
                }
            }
        }

        Problem bigProblemWithStudents = new Problem(studentInstances, schoolInstances);

        //uncomment pentru a vedea lista studentilor si a scolilor
        //bigProblemWithStudents.showProblem();

        Solution toBeSolved = new Solution();

        Map<Student, School> solvedMatching = toBeSolved.Solve(bigProblemWithStudents);

        System.out.println("Lista repartizarii studentilor: \n" + solvedMatching);
    }
}
