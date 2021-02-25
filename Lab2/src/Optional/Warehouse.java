package Optional;

public class Warehouse extends Source{
    /**
     * author: Toma Andrei-Paul
     */
    public String type = "Warehouse";

    public Warehouse(String name, int capacity) {
        /*apeleaza constructorul pentru super clasa (clasa Source).
            toate datele neprivate ale clasei Source vor fi continute de clasa Warehouse

                */
        super(name,  capacity);
    }

    public String getSourceType(){
        return type;
    }
}
