package com.company;

public class Main extends Object {
    /**
     * author: Toma Andrei-Paul
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
        Source s1 = new Source("Coca-Cola Iasi", SourceType.FACTORY, 10);
        Source s2 = new Source("Coca-cola Cluj", SourceType.WAREHOUSE, 35);
        Source s3 = new Source("Coca-Cola Brasov", SourceType.WAREHOUSE, 25);

        //creeaza 3 instante ale destiantiei, fiecare cu numele, tipul si cererea ei.
        Destination d1 = new Destination("Carrefour Felicia", DestinationType.SHOP, 20);
        Destination d2 = new Destination("Lidl Tatarasi", DestinationType.SHOP, 25);
        Destination d3 = new Destination("Mircea PF", DestinationType.INDIVIDUAL, 20);

        //intanta a clasei "Problem"
        Problem solver = new Problem();

        //Apeleaza metoda "printProblem" ce va afisa informatii despre sursa, destinatie si costuri.
        solver.printProblem(new Source[]{s1, s2, s3}, new Destination[]{d1, d2, d3}, costs, 3);


        System.out.println();
        System.out.println(s1.toString());
        System.out.println(d1.toString());
    }
}
