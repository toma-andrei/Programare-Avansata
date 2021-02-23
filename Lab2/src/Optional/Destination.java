package Optional;

public class Destination extends Object {

    //similar ca la clasa Source.

    private final String name;
    private int demand;

    public Destination(String name,  int demand) {
        this.name = name;
        this.demand = demand;
    }

    public String getName() {
        return name;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int newDemand) {
        this.demand = newDemand;
    }

    public String toString() {
        return name + " demands " + demand;
    }

    @Override
    public boolean equals(Object newDest) {
    //explicatii similare ca la metoda equals() suprascrisa in clasa Source.
        if(this == newDest){
            return true;
        }

        if(newDest == null || newDest.getClass() != this.getClass())
            return false;

        Destination verify = (Destination) newDest;


        if(verify.getName() == name && verify.getDemand() == demand)
            return true;
        else return false;
    }
}
