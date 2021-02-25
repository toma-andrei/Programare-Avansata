package Optional;

public class Factory extends Source {
    /**
     * author: Toma Andrei-Paul
     */

    public String type = "Factory";

    /***
     *
     * @param name numele pentru sursa de tipul Factory
     * @param capacity valoarea maxima a marfii ce poate fi trimisa
     */
    public Factory(String name, int capacity){
        //apeleaza constructorul pentru super clasa (clasa Source).
        //toate datele neprivate ale clasei Source vor fi continute de clasa Factory.
        super(name, capacity);
    }

    public String getSourceType(){
        return type;
    }

}
