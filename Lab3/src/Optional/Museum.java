package Optional;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Museum implements Visitable, Payable {
    private String name;
    private int entryFee;
    //LocalTime - clasa ce reprezinta timpul ca ore:minute:secunde
    private LocalTime openingHours;
    private LocalTime closingHours;
    private Map<Object, String> timeDistance = new HashMap<Object, String>();

    /***
     *
     * @param name
     * @param openAt
     * @param closeAt
     * @param fee int meaning tax that must pe payed for visiting this museum
     */
    public Museum(String name, String openAt, String closeAt, int fee){
        this.name = name;
        //metoda .parse(String) din clasa LocalTime parseaza tipul String la tipul LocalTime.
        this.openingHours = LocalTime.parse(openAt);
        this.closingHours = LocalTime.parse(closeAt);

    }

    @Override
    public boolean isPayable() {
        return this.entryFee > 0 ? true : false;
    }

    @Override
    public int  getEntryFee() {
        return entryFee;
    }

    @Override
    public boolean isOpened() {
        return false;
    }

    @Override
    public String getOpeningHour() {
        return String.valueOf(openingHours);
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
        this.openingHours = LocalTime.parse(hour);
    }

    @Override
    public String toString() {
        return name + "(Museum)";
    }

    /***
     *
     * @param placesAndDistances - array de obiecte unde a[i] = loc de vizitat si a[i+1] = distanta de la instanta curenta la obiectul de vizitat.
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
