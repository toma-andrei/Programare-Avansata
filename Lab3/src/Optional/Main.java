package Optional;

import java.util.List;

public class Main {
    /***
     * @author: Toma Andrei-Paul
     */
    public static void main(String[] args) {

        Hotel hotelTraian = new Hotel("Traian", "Nice old looking building", 5, 2);

        Museum muzeulLiteraturii = new Museum("Muzeul literaturii", "08:00", "22:00", 15);
        Museum bojdeucaLuiCreanga = new Museum("Bojdeuca lui Ion Creanga", "08:00", "22:00", 10);

        Church sfintiiTreiIerarhi = new Church("Sfintii 3 ierarhi", "06:30", "21:30");
        Church mitropolia = new Church("Mitropolia", "05:30", "22:00");

        Restaurant restaurantMammaMia = new Restaurant("Mamma Mia", "07:00", "23:30", 0);

        Church bisericaSfantulAndrei = new Church("Sfantul Andrei", "06:40", "22:00");


        City Iasi = new City("Iasi", bisericaSfantulAndrei, hotelTraian, muzeulLiteraturii, bojdeucaLuiCreanga,
                sfintiiTreiIerarhi, mitropolia, restaurantMammaMia);

        hotelTraian.setTimeDistance(muzeulLiteraturii, "20:00", bojdeucaLuiCreanga, "45:00", sfintiiTreiIerarhi, "25:00",
                mitropolia, "25:00", restaurantMammaMia, "20:00", bisericaSfantulAndrei, "30:00");

        muzeulLiteraturii.setTimeDistance(hotelTraian, "20:00", bojdeucaLuiCreanga, "30:00", sfintiiTreiIerarhi, "35:00",
                mitropolia, "35:00", restaurantMammaMia, "25:00", bisericaSfantulAndrei, "25:00");

        bojdeucaLuiCreanga.setTimeDistance(hotelTraian, "45:00", muzeulLiteraturii, "30:00", sfintiiTreiIerarhi, "34:00",
                mitropolia, "35:00", restaurantMammaMia, "30:00", bisericaSfantulAndrei, "28:00");

        sfintiiTreiIerarhi.setTimeDistance(hotelTraian, "25:00", muzeulLiteraturii, "35:00", bojdeucaLuiCreanga, "34:00",
                mitropolia, "05:00", restaurantMammaMia, "15:00", bisericaSfantulAndrei, "33:00");

        mitropolia.setTimeDistance(hotelTraian, "25:00", muzeulLiteraturii, "35:00", bojdeucaLuiCreanga, "35:00", sfintiiTreiIerarhi,
                "05:00", restaurantMammaMia, "10:00", bisericaSfantulAndrei, "15:00");

        restaurantMammaMia.setTimeDistance(hotelTraian, "20:00", muzeulLiteraturii, "25:00", bojdeucaLuiCreanga, "30:00",
                sfintiiTreiIerarhi, "15:00", mitropolia, "10:00", bisericaSfantulAndrei, "15:00");

        bisericaSfantulAndrei.setTimeDistance(hotelTraian, "30:00", muzeulLiteraturii, "25:00", bojdeucaLuiCreanga, "28:00",
                sfintiiTreiIerarhi, "33:00", mitropolia, "15:00", restaurantMammaMia, "15:00");

        System.out.println();

        System.out.println("Distance (minutes) from " + sfintiiTreiIerarhi + " to: " + "\n" + sfintiiTreiIerarhi.getTimeDistance());
        System.out.println();

        //lista de instante Visitable in care vor fi puse obiecte visitable dar nu payable.
        List<Visitable> freeVisitablePlaces = Iasi.getFreeVisitable();

        System.out.println("Locuri ce pot fi vizitate fara a plati ordonate dupa ora de deschidere: ");

        for (Visitable place : freeVisitablePlaces) {
            System.out.println(place + " " + place.getOpeningHour());
        }

        System.out.println();

        System.out.println(restaurantMammaMia + "is opened for " + Visitable.getVisitingDuration(restaurantMammaMia) + " a day");

        System.out.println();
        TravelPlan myPlan = new TravelPlan(Iasi, muzeulLiteraturii, sfintiiTreiIerarhi, bojdeucaLuiCreanga, restaurantMammaMia, hotelTraian);
    }
}