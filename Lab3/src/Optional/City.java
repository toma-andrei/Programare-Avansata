package Optional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class City {
    /***
     * @author: Toma Andrei-Paul
     */

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

    /***
     *
     * @return a list of visitable and not payable places.
     */
    public List<Visitable> getFreeVisitable() {

        //ArrayList ce accepta obiecte ce implementeaza interfata Visitable
        List<Visitable> tempPlacesArray = new ArrayList<Visitable>();

        //verifica daca locatia este visitabila si nu necesita plata, folosing
        //keyword-ul instanceof care verifica daca un obiect este instanta a unei clase/interfete.
        for (Object obj : placesToVisit) {
            if (obj instanceof Visitable && !(obj instanceof Payable)) {
                tempPlacesArray.add((Visitable) obj);
            }
        }

        //daca am nevoie de o clasa pe care o folosesc intr-un singur loc, pot scrie ca mai jos iar compilatorul
        //va crea o clasa ce va implementa interfata Comparator si ca suprascrie metoda compare.
        tempPlacesArray.sort(new Comparator<Visitable>() {
            public int compare(Visitable o1, Visitable o2) {
                return o1.getOpeningHour().compareTo(o2.getOpeningHour());
            }
        });

        return tempPlacesArray;
    }


}
