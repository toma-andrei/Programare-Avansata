package Optional;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Hotel extends Location implements Classifiable {
    /***
     * @author: Toma Andrei-Paul
     */

    private String description;
    private int stars;
    int rank;

    /***
     *
     * @param name
     * @param description String - hotel description
     * @param stars int - number of stars for the hotel
     * @param rank int meaning rank of person allowed to stay in the hotel
     */
    Hotel(String name, String description, int stars, int rank) {
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

    @Override
    public String toString() {
        return name + "(Hotel)";
    }

    public String getDescription() {
        return description;
    }

    public int getStars() {
        return stars;
    }


}
