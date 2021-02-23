package Optional;

public class Factory extends Source {
    /**
     * author: Toma Andrei-Paul
     */
    public Factory(String name, int capacity){
        //apeleaza constructorul pentru super clasa (clasa Source).
        //toate datele neprivate ale clasei Source vor fi continute de clasa Factory.
        super(name, "Factory", capacity);
    }
}
