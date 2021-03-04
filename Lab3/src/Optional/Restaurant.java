package Optional;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Restaurant extends Location implements Visitable, Classifiable {
    /***
     * @author: Toma Andrei-Paul
     */

    int rank;
    private LocalTime openingHours;
    private LocalTime closingHours;

    /***
     *
     * @param name
     * @param openAt
     * @param closeAt
     * @param rank int meaning rank of person allowed to visit restaurant
     */
    public Restaurant(String name, String openAt, String closeAt, int rank) {
        this.name = name;
        openingHours = LocalTime.parse(openAt);
        closingHours = LocalTime.parse(closeAt);
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
    public boolean isOpened() {
        return true;
    }

    @Override
    public String getOpeningHour() {
        return openingHours.toString();
    }

    @Override
    public String getClosingHour() {
        return this.closingHours.toString();
    }

    @Override
    public void setOpeningHour(String hour) {
        this.openingHours = LocalTime.parse(hour);
    }

    @Override
    public void setClosingHour(String hour) {
        this.closingHours = LocalTime.parse(hour);
    }

    @Override
    public String toString() {
        return name + "(Restaurant)";
    }

}
