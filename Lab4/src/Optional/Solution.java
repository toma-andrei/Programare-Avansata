package Optional;

import java.util.*;

public class Solution {
    /***
     *
     * @param studentsList
     * @param schoolsList
     */
    public Map<Student, School> Solve(List<Student> studentsList, List<School> schoolsList) {
        /**
         * Algoritmul sorteaza studentii descrescator dupa scor iar pentru fiecare student se verifica daca scoala
         *      preferata nu are capacitatea satisfacuta. In caz afirmativ se trece la urmatoarea preferinta a studentului.
         *      Cand se gaseste o scoala care nu are capacitatea completa studentul este repartizat la acea scoala.
         */
        studentsList.sort((Student s1, Student s2) -> String.valueOf(s2.getScore()).compareTo(String.valueOf(s1.getScore())));

        studentsList.forEach(s -> System.out.println(s.getName() + " " + s.getScore()));


        List<Integer> schoolCapacities = new ArrayList<Integer>();
        Map<Student, School> matching = new TreeMap<>();

        schoolsList.forEach(s -> schoolCapacities.add(s.getCapacity()));

        for (Student s : studentsList) {
            for (int i = 0; i < s.getStudentPreferences().size(); i++) {

                boolean studHasSchool = false;

                for (int j = 0; j < schoolsList.size(); j++) {
                    if (schoolsList.get(j).getName().equals(s.getStudentPreferences().get(i))) {

                        if (schoolCapacities.get(j) > 0) {

                            matching.put(s, schoolsList.get(j));
                            schoolCapacities.set(j, (int) ((schoolCapacities.get(j)) - 1));
                            studHasSchool = true;
                        } else {
                            break;
                        }
                    } else {
                        continue;
                    }

                }
                if (studHasSchool == true) {
                    break;
                }
            }
        }

        return matching;

    }

}
