package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        Hotel v1 = new Hotel("Traian", "Nice old looking building", 5, 2);

        Museum v2 = new Museum("Muzeul literaturii", "08:00", "22:00", 15);
        Museum v3 = new Museum("Bojdeuca lui Ion Creanga", "08:00", "22:00", 10);

        Church v4 = new Church("Sfintii 3 ierarhi", "06:30", "21:30");
        Church v5 = new Church("Mitropolia", "05:30", "22:00");

        Restaurant v6 = new Restaurant("Mamma Mia", "07:00", "23:30", 0);

        City Iasi = new City("Iasi", v1, v2, v3, v4, v5, v6);

        v1.setTimeDistance(v2, "20:00",v3, "25:00",v4, "10:00",v5, "35:00", v6, "30:00");
        v2.setTimeDistance(v2, "25:00",v3, "10:00",v4, "35:00", v5, "30:00", v6, "25:00");
        v3.setTimeDistance(v1, "20:00",v2, "25:00",v4, "10:00",v5, "35:00", v6, "30:00");
        v4.setTimeDistance(v1, "20:00",v2, "25:00",v3, "10:00",v5, "35:00", v6, "30:00");
        v5.setTimeDistance(v1, "20:00",v2, "25:00",v3, "10:00",v4, "35:00", v6, "30:00");
        v6.setTimeDistance(v1, "20:00",v2, "25:00",v3, "10:00",v4, "35:00", v5, "30:00");

        System.out.println(Iasi.getPlacesToVisit());
        System.out.println();

        System.out.println("Distance (minutes) from " + v1 + " to: " + "\n" + v1.getTimeDistance());
        System.out.println();
        System.out.println("Distance (minutes) from " + v4 + " to: " + "\n" + v4.getTimeDistance());

    }
}