package Optional;

import java.util.*;

public class Solution {
    /***
     *
     * @param toBeSolved - instanta a clasei Problem ce contine liste cu instante pentru studenti si scoli
     * @return - un map ce va avea campurile "numeStudent = scoala preferata"
     */
    public Map<Student, School> Solve(Problem toBeSolved) {

        List<Student> studentsList = toBeSolved.getStudents();
        List<School> schoolsList = toBeSolved.getSchools();
        /**
         * Algoritmul sorteaza studentii descrescator dupa scor iar pentru fiecare student se verifica daca scoala
         *      preferata nu are capacitatea satisfacuta. In caz afirmativ se trece la urmatoarea preferinta a studentului.
         *      Cand se gaseste o scoala care nu are capacitatea completa studentul este repartizat la acea scoala.
         */
        studentsList.sort((Student s1, Student s2) -> String.valueOf(s2.getScore()).compareTo(String.valueOf(s1.getScore())));


        //afiseaza o lista a studentilor ordonati dupa scor.

        //studentsList.forEach(s -> System.out.println(s.getName() + " " + s.getScore()));

        // * schoolCapacities este folosit pentru a verifica daca capacitatea scolii a fost sau nu satisfacuta.
        List<Integer> schoolCapacities = new ArrayList<Integer>();
        Map<Student, School> matching = new TreeMap<>();

        schoolsList.forEach(s -> schoolCapacities.add(s.getCapacity()));

        for (Student s : studentsList) {
            for (int i = 0; i < s.getStudentPreferences().size(); i++) {

                /*
                  studHasSchool bifeaza daca un student a fost inscris la o scoala, pentru a sti cand sa se faca break la cautarea scolii.
                 */
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
                if (studHasSchool) {
                    break;
                }
            }
        }

        return matching;

    }

}
