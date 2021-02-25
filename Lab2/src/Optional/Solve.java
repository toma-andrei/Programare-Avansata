package Optional;

public class Solve extends Object {

    /***
     *
     * @param sourceArray array de surse (warehouses / factories)
     * @param destinationArray array de destinatii
     * @param costMatrix matrice ce reprezinta costurile de transfer a marfii. costMatrix[K][K] reprezinta costul de la sursaK la destinatiaK
     */
    public void solveProblem(Source[] sourceArray, Destination[] destinationArray, int[][] costMatrix) {

        int nrOfSrcInstances = sourceArray.length;
        int nrOfDestInstances = destinationArray.length;

        //verifica daca este folosita aceeasi sursa de doua ori(foloseste metoda equals())
        for (int i = 0; i < nrOfSrcInstances - 1; i++) {
            for (int j = i + 1; j < nrOfSrcInstances; j++) {
                if (sourceArray[i].equals(sourceArray[j])) {
                    System.out.println("Aceeasi sursa este folosita de doua ori!");
                    return;
                }
            }
        }

        //verifica daca este folosita aceeasi destinatie de doua ori
        //          (foloseste metoda equals() suprascrisa in clasa Destination)
        for (int i = 0; i < nrOfDestInstances - 1; i++) {
            for (int j = i + 1; j < nrOfDestInstances; j++) {
                if (destinationArray[i].equals(destinationArray[j])) {
                    System.out.println("Aceeasi destinatie este folosita de doua ori!");
                    return;
                }
            }
        }

        int[] supplies = new int[nrOfSrcInstances];
        int[] demands = new int[nrOfDestInstances];

        /*adauga valorile pentru supplies si pentru demands in
                    array-uri pentru a accesa datele ulterior cu mai putin cod.*/

        for (int i = 0; i < nrOfSrcInstances; i++) {
            supplies[i] = sourceArray[i].getCapacity();
        }
        for (int i = 0; i < nrOfDestInstances; i++) {
            demands[i] = destinationArray[i].getDemand();
        }

        /*consumedSupply si satisfiedDemand sunt variabile ce retin pozitii in
                array-urile creeate anterior.*/
        int totalCost = 0, consumedSupply = 0, satisfiedDemand = 0;

        /**Algoritmul parcurge simultan array-urile supplies si demands si incearca "sa satisfaca" valorile din demands prin
         *      incrementare pozitiilor vectorilor (consumedSupply si satisfiedDemand) cand o sursa "si-a trimis" marfa
         *      si cand o destinatie "si-a primit-o". Algoritmul se termina cand s-a trimis toata marfa sau cand
         *      toate cererile sunt satisfacute.
         */
        while (consumedSupply < nrOfSrcInstances && satisfiedDemand < nrOfDestInstances) {
            /*daca conditia e satisfacuta, inseamna ca trimit toata marfa din supplies[i] si totusi demads[j]
             * inca trebuie sa primeasca marfa => incrementez doar i-ul (consumedSupply) => costul va fi
             * costs[i][j] * supplies[j]  */
            if (demands[satisfiedDemand] > supplies[consumedSupply]) {

                //afiseaza de la ce sursa la ce destinatie se trimite marfa, in ce cantitate si cu ce cost.

                System.out.println(sourceArray[consumedSupply].getName() + " -> " + destinationArray[satisfiedDemand].getName() +
                        ": " + supplies[consumedSupply] + " units with cost " +
                        (costMatrix[consumedSupply][satisfiedDemand] * supplies[consumedSupply]));

                totalCost += (costMatrix[consumedSupply][satisfiedDemand] * supplies[consumedSupply]);

                demands[satisfiedDemand] -= supplies[consumedSupply];

                consumedSupply++;
                /*daca conditia e satisfacuta, inseamna ca trimit o parte din marfa unei surse supplies[i]  si
                 *       se satisface toata cererea unei destinatii demands[j] => incrementez j-ul si costul
                 *       trimiterii va fi costs[i][j] * demands[j];
                 *       */
            } else if (demands[satisfiedDemand] < supplies[consumedSupply]) {

                System.out.println(sourceArray[consumedSupply].getName() + " -> " + destinationArray[satisfiedDemand].getName() +
                        ": " + demands[satisfiedDemand] + " units with cost " +
                        (costMatrix[consumedSupply][satisfiedDemand] * demands[satisfiedDemand]));
                supplies[consumedSupply] -= demands[satisfiedDemand];

                totalCost += (costMatrix[consumedSupply][satisfiedDemand] * demands[satisfiedDemand]);

                satisfiedDemand++;

                /*daca intra aici, inseamna ca demands[i]==supplies[i]; deci incrementez ambele pozitii si costul va fi
                 * costs[i][j] * supplies[i] (sau * demands[j])*/
            } else {
                System.out.println(sourceArray[consumedSupply].getName() + " -> " + destinationArray[satisfiedDemand].getName() +
                        ": " + supplies[consumedSupply] + " units with cost " +
                        (costMatrix[consumedSupply][satisfiedDemand] * demands[satisfiedDemand]));

                totalCost += costMatrix[consumedSupply][satisfiedDemand] * demands[satisfiedDemand];

                satisfiedDemand++;
                consumedSupply++;
            }
            /*costul total = suma costurilor trimiterilor tuturor unitatilor de marfa catre toate destinatie.*/
        }

        System.out.println("Total cost: " + totalCost);
    }

    public String toString() {
        return "Problem class!";
    }
}
