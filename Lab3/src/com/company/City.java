package com.company;

import java.util.*;

public class City{
    private String name;

    //placesToVisit este o lista de elemente cu tipul Object. Contine locuri (obiecte) ce pot fi vizitate in oras.
    private List<Object> placesToVisit = new ArrayList<Object>();
    /***
     *
     * @param name city name
     * @param places multiple places you can go in the city
     */

    City(String name, Object... places) {
        this.name = name;

        int len = places.length;

        for (int i = 0; i < len; i++) {
            placesToVisit.add(places[i]);
        }
    }

    public List<Object> getPlacesToVisit(){
        return placesToVisit;
    }


}
