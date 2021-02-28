package com.company;

import java.util.HashMap;
import java.util.Map;

public class Hotel implements Classifiable{

    private String name;
    private String description;
    private int stars;
    int rank;

    /***
     * referinta catre un obiect de tipul HashMap (tip_cheie, tip_valoare)
     */

    private Map<Object, String> timeDistance = new HashMap<Object, String>();

    /***
     *
     * @param name
     * @param description String - hotel description
     * @param stars int - number of stars for the hotel
     * @param rank int meaning rank of person allowed to stay in the hotel
     */
    Hotel(String name, String description, int stars, int rank){
        this.name = name;
        this.description = description;
        this.stars = stars;
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

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getStars(){
        return stars;
    }

    @Override
    public String toString() {
        return name + "(Hotel)";
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
