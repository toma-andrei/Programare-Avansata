package com.company;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Restaurant implements Visitable, Classifiable {
    private String name;
    int rank;
    private LocalTime openingHours;
    private LocalTime closingHours;
    private Map<Object, String> timeDistance = new HashMap<Object, String>();

    /***
     *
     * @param name
     * @param openAt
     * @param closeAt
     * @param rank int meaning rank of person allowed to visit restaurant
     */
    public Restaurant(String name, String openAt, String closeAt, int rank) {
        this.name = name;
        openingHours = LocalTime.parse(openAt);
        closingHours = LocalTime.parse(closeAt);
        this.rank = rank;
    }

    @Override
    public boolean isClassifiable() {
        return rank > 0 ? true : false;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public boolean isOpened() {
        return true;
    }

    @Override
    public String getOpeningHour() {
        return openingHours.toString();
    }

    @Override
    public String toString() {
        return name + "(Restaurant)";
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
