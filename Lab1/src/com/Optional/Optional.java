package com.Optional;
import java.util.ArrayList;
import java.util.Arrays;/*pentru Arrays.fill()*/
import java.util.Scanner;/*pentru a citi date de la tastatura*/
import java.util.concurrent.TimeUnit;

class Optional {
/**Author: Toma Andrei-Paul*/
/**Gaseste componentele conexe ale unui random graph dat prin matrice de adiacenta. Matricea e generata cu metoda Math.random().
 * Ideea de rezolvare: Face BSF pornind dintr-un nod al grafului. Sunt retinute nodurile care fac parte din acea componenta conexa.
 Dupa fiecare iterare a algoritmului de BFS este updatat un array ce retine nodurile vizitate de fiecare iteratie BFS.
 Cat timp acest array contine noduri nevizitate, se vor afisa nodurile ce fac parte din ultima componenta conexa vizitata cu BFS
 si se itereaza din nou algoritmul de BFS pentru unul din nodurile nevizitate. Acesti pasi se repeta pana cand toate nodurile sunt vizitate.
 */

/**Bibliografie:
 * https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/
 * https://www.w3schools.com/java/java_arraylist.asp
 * https://stackoverflow.com/questions/27455742/array-index-out-of-bounds-exception-0
 * https://www.techiedelight.com/measure-elapsed-time-execution-time-java/ */

    public void connectedComponents(int adjencyMatrix[][], int n, int startNode) { //

        int verified[] = new int[n]; // noduri vizitate pe fiecare componenta conexa in parte
        int globalVerified[] = new int[n]; // reuniunea nodurilor fiecarei componente conexe

        Arrays.fill(verified, 0); // metoda fill din clasa Arrays "umple" vectorul verified cu 0-uri
        Arrays.fill(globalVerified, 0);


        ArrayList <Integer> queue = new ArrayList<Integer>(); // un array de intregi "mai simplu" de manipulat (metode ce usureaza munca)
        queue.add(startNode);

        verified[startNode] = 1;

        boolean allComponentsAreFound = false;
        short compNumber = 1; // numarul de componente conexe

        /*repeta BFS-ul interior pana cand nu mai exista componente conexe care nu au fost vizitate*/
        while(!allComponentsAreFound) {
            while (!queue.isEmpty()) { /* while care face un BFS */
                int visitedNow = queue.get(0);
                queue.remove(0);

                for (int i = 0; i < n; i++) {
                    if (adjencyMatrix[visitedNow][i] == 1 && (verified[i] == 0)) {
                        queue.add(i);
                        verified[i] = 1; // marcheaza ca nodul i a fost vizitat.
                    }
                }
            }

             /*viabila care imi spune daca mai exista sau nu componente conexe*/
            boolean stillExistsComponents = false;

            /*adauga multimea de noduri a componentei conexe gasite la acest pas la multimea tuturor nodurilor*/
            for(int i = 0; i < n; i++){
                if(verified[i] == 1){
                    globalVerified[i] = 1;
                }
            }

            /*for(int i = 0; i < n; i++){
                System.out.print(globalVerified[i] + " ");
            }
            System.out.println();*/

            /*verifica daca exista un nod nevizitat in multimea totala de noduri. In caz afirmativ modifica variabila de existenta
            a comp. conexe nevizitate. Tot in acest for este adaugat in coada cu care se face BFS-ul primul nod nevizitat*/
            for(int i = 0; i < n; i++){
                if(globalVerified[i] == 0) {
                    stillExistsComponents = true;
                    queue.add(i);
                    break;
                }
            }
            /* Daca graful este conex(au fost vizitate toate nodurile si numarul de comp. conexe este 1), afiseaza mesajul.*/
            if(!stillExistsComponents && (compNumber == 1)){
                System.out.println("Graph is connected!");
                break;
            }
            else if (stillExistsComponents) {

                if (compNumber == 1)
                    System.out.println("Connected components:");

                /*afisez fiecare nod ce face parte din componenta conexa curenta.*/
                System.out.print(compNumber + ": ");
                compNumber++;
                for (int i = 0; i < n; i++) {
                    if (verified[i] == 1) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println();

                for (int i = 0; i < n; i++)
                    verified[i] = 0;

                verified[queue.get(0)] = 1;
            }
            else{
                /*intra pe aceasta ramura doar cand ultima componenta conexa a fost gasita(daca exista mai mult de o componenta conexa)*/
                System.out.print(compNumber + ": ");
                compNumber++;
                for(int i = 0; i < n; i++) {
                    if(verified[i] == 1) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println();
                break;
            }
        }
    }

    public static void main(String[] argv){

        Scanner scan = new Scanner(System.in);
        /*Citeste inputul de la tastatura pana intalneste caracterul /n.
         Caracterul \n ramane in scanner si pot aparea probleme la citirea urmatorului input, de aceea
         apare inca o citere pentru Stringuri din scanner.*/
        int n = scan.nextInt();


        // Valideaza valoarea lui n;
        if(n <= 0){
            System.err.println("n is too small!");
        }

        //declararea matricei de adiacenta
        int [][]adjencyMatrix = new int [n][n];
        long startTime = System.nanoTime();
        //umplerea matricei cu metoda Math.random();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int value;
                if(Math.random() < 0.5){
                    value = 1;
                }
                else{
                    value = 0;
                }

                if(j > i){
                    adjencyMatrix[i][j] = value;
                    adjencyMatrix[j][i] = value;
                }
            }
        }

        /*afiseaza matricea doar daca valoarea lui n este relativ mica.*/
        if(n <= 500) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(adjencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        /*instanta a clasei Optional (/ obiect)*/
        Optional graph = new Optional();
        /*apeleaza metoda care gaseste componentele conexe. Primeste ca argumente matricea de adiacenta, numarul de noduri si nodul de start pentru BFS*/
        graph.connectedComponents(adjencyMatrix, n, 0);
        long endTime = System.nanoTime();

        System.out.println("Time elapsed during program execution: " + (endTime - startTime) + " nanoseconds!");
    }
}
