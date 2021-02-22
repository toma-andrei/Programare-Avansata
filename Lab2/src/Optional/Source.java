package Optional;

public abstract class Source extends Object {
    /**
     * author: Toma Andrei-Paul
     */

//variabilele sunt private, accesarea lor facandu-se prin settere si gettere (incapsulare)
//constantele se scriu cu litere mari

    private final String NAME;
    private final String TYPE;
    private int capacity;

    //Constructor parametrizat. Este apelat cand e creata o noua instanta a clasei.
    public Source(String name, String type, int capacity) {
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

    @Override
    public boolean equals(Object newObject){
        //verifica daca ambele obiecte fac referire la acelasi obiect. (ex : o.equals(o));
        if(this == newObject){
            return true;
        }
        /*metoda getClass() a clasei Class returneaza o instanta a clasei Class care contine informatii
        *   despre clasa Source (clasa din care a fost apelata metoda getClass()).
        * if-ul urmator verifica daca metoda getClass() returneaza aceeasi instanta atat pentru clasa curenta cand si
        * pentru clasa cu care se face verificarea. */
        if(newObject == null || newObject.getClass() != this.getClass())
            return false;

        /*(type casting) Converteste un tip de date (Object) la un alt tip de date (Source), "mai mare"
        *       (Object extinde clasa Source deci Source = Object + "extraStuff" => Source > Object.).
        * Casting-ul se face pe referinta, ceea ce inseamna ca doar "Extinde"
        *       posibilitatile de a lucra cu acea referinta (se mareste lista metodelor
        *       care pot fi folosite de o anumita instanta.)."*/
        Source verify = (Source) newObject;

        //Verifica daca cele doua instante
        if(verify.getName() == NAME && verify.getCapacity() == capacity)
            return true;
        else return false;
    }

}
