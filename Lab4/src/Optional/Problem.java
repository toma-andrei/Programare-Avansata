package Optional;

import java.util.List;

public class Problem {

    public void showProblem(List<Student> students, List<School> schools) {

        System.out.println("STUDENTS and PREFERENCES: ");

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getName() + "   " + students.get(i).getStudentPreferences());
        }

        System.out.println("\nSCHOOLS and PREFERENCES: ");
        for (int i = 0; i < schools.size(); i++)
            System.out.println(schools.get(i).getName() + "   " + schools.get(i).getSchoolPreferences());
    }
}
