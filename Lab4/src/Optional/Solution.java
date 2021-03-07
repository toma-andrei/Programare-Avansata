package Optional;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public void Solve(List<Student> studentsList, List<School> schoolsList) {
        studentsList.sort((Student s1, Student s2) -> String.valueOf(s2.getScore()).compareTo(String.valueOf(s1.getScore())));

        studentsList.forEach(s -> System.out.println(s.getName() + " " + s.getScore()));

        List<Integer> schoolCapacities = new ArrayList<Integer>();

        schoolsList.forEach(s -> schoolCapacities.add(s.getCapacity()));

        //studentsList.forEach();


    }

}
