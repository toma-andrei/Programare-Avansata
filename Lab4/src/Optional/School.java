package Optional;

import java.util.ArrayList;
import java.util.List;

public class School implements Comparable<School> {
    private String name;
    private int capacity;
    private List<String> schoolPreferences = new ArrayList<String>();

    public School(String name, int capacity, String... pref) {
        this.name = name;

        this.capacity = capacity;

        for (int i = 0; i < pref.length; i++) {
            schoolPreferences.add(pref[i]);
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getSchoolPreferences() {
        return schoolPreferences;
    }

    public void addPreference(String preference) {
        schoolPreferences.add(preference);
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(School otherSchool) {
        return this.name.compareTo(otherSchool.getName());
    }
}
