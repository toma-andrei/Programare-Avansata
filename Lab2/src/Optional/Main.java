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
                {2, 3, 1, 2},
                {5, 4, 8, 2},
                {5, 6, 8, 2}
        };

        Factory s1 = new Factory("Coca-Cola Iasi", 40);
        Warehouse s2 = new Warehouse("Coca-cola Cluj", 35);
        Warehouse s3 = new Warehouse("Coca-Cola Brasov", 35);

        Destination d1 = new Destination("Carrefour Felicia", 20);
        Destination d2 = new Destination("Lidl Tatarasi", 25);
        Destination d3 = new Destination("Mircea PF", 25);
        Destination d4 = new Destination("Kaufland Iasi", 25);

        //intanta a clasei "Problem"
        Problem toBeSolved = new Problem();

        //Apeleaza metoda "printProblem" ce va afisa informatii despre sursa, destinatie si costuri.
        /* instantele provenite din clasele Factory si Warehouse sunt tratate ca si Sources,
               de aceea au putut fi adaugate in acelasi array. (se face cast => toate sursele sunt tratate in acelasi fel)*/
        //toBeSolved.printProblem(new Source[]{s1, s2, s3}, new Destination[]{d1, d2, d3}, costs);


        System.out.println();
        System.out.println(s1.toString());
        System.out.println(d1.toString() + "\n");
        toBeSolved.printProblem(new Source[]{s1, s2, s3}, new Destination[]{d1, d2, d3, d4}, costs);
        toBeSolved.solveProblem(new Source[]{s1, s2, s3}, new Destination[]{d1, d2, d3, d4}, costs);

    }
}
