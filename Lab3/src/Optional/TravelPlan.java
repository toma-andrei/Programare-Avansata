package Optional;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class TravelPlan {

    private City city;

    private List <Object> preferences = new ArrayList<Object>();

    public TravelPlan(City city, Object ... preference){
        this.city = city;
        for(int i = 0; i < preference.length; i++)
            preferences.add(preference[i]);

        city.getPlacesToVisit();
    }

}
