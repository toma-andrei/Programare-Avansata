package Optional;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Church extends Location implements Visitable {

    private LocalTime openingHours;
    private LocalTime closingHours;

    /***
     *
     * @param name  String meaning church name
     * @param openingHours String meaning opening hours for Church
     * @param closingHours String meaning closing hours for Church
     */

    public Church(String name, String openingHours, String closingHours) {
        this.name = name;
        this.openingHours = LocalTime.parse(openingHours);
        this.closingHours = LocalTime.parse(closingHours);
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
        return name + "(Church) ";
    }

}
