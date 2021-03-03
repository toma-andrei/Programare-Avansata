package Optional;

public class Main {

    public static void main(String[] args) {

        Hotel v1 = new Hotel("Traian", "Nice old looking building", 5, 2);

        Museum v2 = new Museum("Muzeul literaturii", "08:00", "22:00", 15);
        Museum v3 = new Museum("Bojdeuca lui Ion Creanga", "08:00", "22:00", 10);

        Church v4 = new Church("Sfintii 3 ierarhi", "06:30", "21:30");
        Church v5 = new Church("Mitropolia", "05:30", "22:00");

        Restaurant v6 = new Restaurant("Mamma Mia", "07:00", "23:30", 0);

        Church v7 = new Church("Sfantul Andrei", "06:40", "22:00");


        City Iasi = new City("Iasi",  v7, v1, v2, v3, v4, v5, v6);

        v1.setTimeDistance(v2, "20:00",v3, "45:00",v4, "25:00",v5, "25:00", v6, "20:00", v7, "30:00");
        v2.setTimeDistance(v1, "20:00",v3, "30:00",v4, "35:00", v5, "35:00", v6, "25:00", v7, "25:00");
        v3.setTimeDistance(v1, "45:00",v2, "30:00",v4, "34:00",v5, "35:00", v6, "30:00", v7, "28:00");
        v4.setTimeDistance(v1, "25:00",v2, "35:00",v3, "34:00",v5, "05:00", v6, "15:00", v7, "33:00");
        v5.setTimeDistance(v1, "25:00",v2, "35:00",v3, "35:00",v4, "05:00", v6, "10:00", v7, "15:00");
        v6.setTimeDistance(v1, "20:00",v2, "25:00",v3, "30:00",v4, "15:00", v5, "10:00", v7, "15:00");
        v7.setTimeDistance(v1, "30:00",v2, "25:00",v3, "28:00",v4, "33:00", v5, "15:00", v6, "15:00");

//       System.out.println(Iasi.getPlacesToVisit());
//       System.out.println();
//
//      System.out.println("Distance (minutes) from " + v4 + " to: " + "\n" + v4.getTimeDistance());
        System.out.println();

        Iasi.getFreeVisitable();

        System.out.println();

        System.out.println("Hours Between: " + Visitable.getVisitingDuration(v6));

        System.out.println();
        TravelPlan myPlan = new TravelPlan(Iasi, v2, v4, v3, v6, v1);
    }
}