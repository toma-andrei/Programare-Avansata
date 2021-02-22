package com.company;

public class Source extends Object {
    /**
     * author: Toma Andrei-Paul
     */

//variabilele sunt private, accesarea lor facandu-se prin settere si gettere (incapsulare)
    private final String name;
    private final SourceType type;
    private int capacity;

    //Constructor parametrizat. Este apelat cand e creata o noua instanta a clasei.
    public Source(String name, SourceType type, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    //settere si gettere pentru variabilele private.

    int getCapacity() {
        return capacity;
    }

    public String getName() {
        return this.name;
    }

    public String getSourceType() {
        //returneaza concatenarea intre sirul vid si tipul sursei.
        // Nu am gasit alta metoda de a face string dintr-o constanta a unui enum.
        return "" + type;
    }

    void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }


    //metoda "toString()" suprascrie metoda toString() din clasa Object (au acelasi nume si aceiasi parametri
    //                                                              dar functionalitate diferita)
    public String toString() {
        return name + " (" + type + ") " + " supplies " + capacity;
    }

}
