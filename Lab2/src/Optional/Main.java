package Optional;

public class Main extends Object {
    /**
     * author: Toma Andrei-Paul
     *
     * bibliography:
     *      https://www.geeksforgeeks.org/equals-hashcode-methods-java/
     *      https://stackoverflow.com/questions/12159601/why-do-we-assign-a-parent-reference-to-the-child-object-in-java
     *      https://stackabuse.com/javas-object-methods-getclass/
     *      https://www.baeldung.com/java-type-casting
     */

    public String toString(){
        return "main method;";
    }

    public static void main(String[] args) {

        int[][] costs = new int[][]{
                {2, 3, 1},
                {5, 4, 8},
                {5, 6, 8}
        };

        //creaza 3 instante ale sursei fiecare cu numele, tipul si capacitatea ei
        Factory s1 = new Factory("Coca-Cola Iasi", 10);
        Warehouse s2 = new Warehouse("Coca-cola Cluj", 35);
        Warehouse s3 = new Warehouse("Coca-Cola Brasov", 25);

        //creeaza 3 instante ale destiantiei, fiecare cu numele, tipul si cererea ei.
        Destination d1 = new Destination("Carrefour Felicia", 20);
        Destination d2 = new Destination("Lidl Tatarasi", 25);
        Destination d3 = new Destination("Mircea PF", 20);

        //intanta a clasei "Problem"
        Solve toBeSolved = new Solve();

        //Apeleaza metoda "printProblem" ce va afisa informatii despre sursa, destinatie si costuri.
        /* instantele provenite din clasele Factory si Warehouse sunt tratate ca si Sources,
               de aceea au putut fi adaugate in acelasi array. (se face casting => toate sursele sunt tratate in acelasi fel)*/
        toBeSolved.printProblem(new Source[]{s1, s2, s3}, new Destination[]{d1, d2, d3}, costs, 3);


        System.out.println();
        System.out.println(s1.toString());
        System.out.println(d1.toString());

        System.out.println("s1 == s2? " + s1.equals(s2));
        System.out.println("s1 == s1? " + s1.equals(s1));
        System.out.println("s1 == d1? " + s1.equals(d1));

        Factory s4;
        s4 = s1;

        toBeSolved.solve(new Source[]{s1, s2, s3}, new Destination[]{d1, d2, d3}, costs, 3);

    }
}
