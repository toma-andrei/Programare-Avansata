package com.company;

public class Destination extends Object {
    //similar ca la clasa Source.

    private final String name;
    private final DestinationType type;
    private int demand;

    public Destination(String name, DestinationType type, int demand) {
        this.name = name;
        this.demand = demand;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDestinationType() {
        return "" + type;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int newDemand) {
        this.demand = newDemand;
    }

    public DestinationType getType() {
        return this.type;
    }

    public String toString() {
        return name + " (" + type + ") " + "demands " + demand;
    }


}
