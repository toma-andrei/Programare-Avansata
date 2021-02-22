package Optional;

public class Factory extends Source {

    public Factory(String name, int capacity){
        //apeleaza constructorul pentru super clasa (clasa Source).
        //toate datele neprivate ale clasei Source vor fi continute de clasa Factory.
        super(name, "Factory", capacity);
    }
}
