package com.company;

public class Source extends Object {
    /**
     * author: Toma Andrei-Paul
     */

//variabilele sunt private, accesarea lor facandu-se prin settere si gettere (incapsulare)
//constantele se scriu cu litere mari

    private final String NAME;
    private final SourceType TYPE;
    private int capacity;

    //Constructor parametrizat. Este apelat cand e creata o noua instanta a clasei.
    public Source(String name, SourceType type, int capacity) {
        this.NAME = name;
        this.capacity = capacity;
        this.TYPE = type;
    }

    //settere si gettere pentru variabilele private (verbe scris in camelCase).

    int getCapacity() {
        return capacity;
    }

    public String getName() {
        return this.NAME;
    }

    public String getSourceType() {
        //returneaza concatenarea intre sirul vid si tipul sursei.
        // Nu am gasit alta metoda de a face string dintr-o constanta a unui enum.
        return "" + TYPE;
    }

    void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }


    //metoda "toString()" suprascrie metoda toString() din clasa Object (au acelasi nume si aceiasi parametri
    //                                                              dar functionalitate diferita)
    public String toString() {
        return NAME + " (" + TYPE + ") " + " supplies " + capacity;
    }

}
