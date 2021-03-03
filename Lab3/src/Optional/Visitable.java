package Optional;

import java.time.Duration;
import java.time.LocalTime;

/***
 * interfata Visitable. Obliga clasele ce o implementeaza sa suprascrie TOATE metodele din ea (cu exceptia cand clasa e abstracta).
 */

public interface Visitable {
    public boolean isOpened();

    public String getOpeningHour();

    public String getClosingHour();

    public void setOpeningHour(String hour);

    public void setClosingHour(String hour);

    default void setDefaultTime() {
        this.setOpeningHour("09:30");
        this.setClosingHour("20:00");
    }

    static Duration getVisitingDuration(Visitable place) {
        Duration diffBetweenHours = Duration.between(LocalTime.parse(place.getOpeningHour()), LocalTime.parse(place.getClosingHour()));
        return diffBetweenHours;
    }
}
