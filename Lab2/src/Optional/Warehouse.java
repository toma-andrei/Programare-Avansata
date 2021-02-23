package Optional;

public class Warehouse extends Source{
    /**
     * author: Toma Andrei-Paul
     */

    public Warehouse(String name, int capacity) {
        /*apeleaza constructorul pentru super clasa (clasa Source).
            toate datele neprivate ale clasei Source vor fi continute de clasa Warehouse

                */
        super(name, "Warehouse", capacity);
    }
}
