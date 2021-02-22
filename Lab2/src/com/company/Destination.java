package com.company;

public class Destination extends Object {
    //similar ca la clasa Source.

    private final String NAME;
    private final DestinationType TYPE;
    private int demand;

    public Destination(String name, DestinationType type, int demand) {
        this.NAME = name;
        this.demand = demand;
        this.TYPE = type;
    }

    public String getName() {
        return NAME;
    }

    public String getDestinationType() {
        return "" + TYPE;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int newDemand) {
        this.demand = newDemand;
    }

    public DestinationType getType() {
        return this.TYPE;
    }

    public String toString() {
        return NAME + " (" + TYPE + ") " + "demands " + demand;
    }


}
