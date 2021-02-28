package com.company;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Church implements Visitable {
    private String name;
    private LocalTime openingHours;
    private LocalTime closingHours;
    private Map<Object, String> timeDistance = new HashMap<Object, String>();

    /***
     *
     * @param name  String meaning church name
     * @param openingHours String meaning opening hours for Church
     * @param closingHours String meaning closing hours for Church
     */

    public Church(String name, String openingHours, String closingHours) {
        this.name = name;
        this.openingHours = LocalTime.parse(openingHours);
        this.closingHours = LocalTime.parse(closingHours);
    }

    @Override
    public boolean isOpened() {
        return true;
    }

    @Override
    public String getOpeningHour() {
        return openingHours.toString();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name + "(Church)";
    }

    /***
     *
     * @param placesAndDistances - array de obiecte unde a[i] = loc de vizitat si a[i+1] = distanta de la instanta curenta la obiectul de vizitat.
     */

    public void setTimeDistance(Object... placesAndDistances) {
        for (int i = 0; i < placesAndDistances.length; i += 2) {
            timeDistance.put(placesAndDistances[i], placesAndDistances[i + 1].toString());
        }
    }

    public Map<Object, String> getTimeDistance() {
        return timeDistance;
    }
}
