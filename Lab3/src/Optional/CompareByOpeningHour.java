package Optional;

import java.util.Comparator;

public class CompareByOpeningHour implements Comparator<Visitable> {

    @Override
    public int compare(Visitable o1, Visitable o2) {
        return o1.getOpeningHour().compareTo(o2.getOpeningHour());
    }
}
