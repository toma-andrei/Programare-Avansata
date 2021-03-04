package Optional;

import java.util.HashMap;
import java.util.Map;

/***
 * clasa abstracta Location contine atribute si metode comune pentru fiecare locatie pentru a nu mai
 * fi rescrise in fiecare clasa
 */
public abstract class Location {
    /***
     * @author: Toma Andrei-Paul
     */

    protected String name;

    //Map (un obiect ce contine elemente de forma cheie=valoare) ce contine distanta dintr o locatie si celelalte locatii.
    protected Map<Object, String> timeDistance = new HashMap<Object, String>();

    public String getName() {
        return name;
    }

    /***
     *
     * @param placesAndDistances - parametru variadic ce contine o lista de obiecte,
    unde place[i] este o locatie iar place[i+1] este distanta (in timp)
    de la obiectul care apeleaza metoda la celelalte obiecte.
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
