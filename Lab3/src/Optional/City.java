package Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class City {
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

    public List<Object> getPlacesToVisit() {
        return placesToVisit;
    }

    public void getFreeVisitable() {
        List<Visitable> tempPlacesArray = new ArrayList<Visitable>();
        for (Object obj : placesToVisit) {
            if (obj instanceof Visitable && !(obj instanceof Payable)) {
                tempPlacesArray.add((Visitable) obj);
            }
        }

        tempPlacesArray.sort(new CompareByOpeningHour());

        for(Visitable place: tempPlacesArray){
            System.out.println(place + place.getOpeningHour());
        }
    }


}
