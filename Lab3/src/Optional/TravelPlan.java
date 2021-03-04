package Optional;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class TravelPlan {
    /***
     * @author: Toma Andrei-Paul
     */

    private City city;

    private List <Location> preferences = new ArrayList<Location>();

    public TravelPlan(City city, Object ... preference){
        this.city = city;
        for(int i = 0; i < preference.length; i++)
            preferences.add((Location) preference[i]);
    }

}
