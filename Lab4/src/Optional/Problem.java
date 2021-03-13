package Optional;

import java.util.List;

public class Problem {
    private List<School> schools;
    private List<Student> students;

    public Problem(List<Student> student, List<School> school) {
        this.schools = school;
        this.students = student;
    }

    public List<School> getSchools() {
        return schools;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void showProblem() {

        System.out.println("STUDENTS and PREFERENCES: ");

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getName() + "   " + students.get(i).getStudentPreferences());
        }

        System.out.println("\nSCHOOLS and PREFERENCES: ");
        for (int i = 0; i < schools.size(); i++)
            System.out.println(schools.get(i).getName() + "   " + schools.get(i).getSchoolPreferences());
    }
}
