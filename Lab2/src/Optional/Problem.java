package Optional;

public class Problem extends Solve {
    /**
     * author: Toma Andrei-Paul
     */

    /***
     *
     * @param sourceArray array de surse (warehouses / factories)
     * @param destinationArray array de destinatii
     * @param costMatrix matrice ce reprezinta costurile de transfer a marfii. costMatrix[K][K] reprezinta costul de la sursaK la destinatiaK
     */
    public void printProblem(Source sourceArray[], Destination destinationArray[], int costMatrix[][]) {

        for (int i = 0; i < sourceArray.length; i++) {
            for (int j = 0; j < destinationArray.length; j++) {

                System.out.println(sourceArray[i].getName() + " (" + sourceArray[i].getSourceType() + ")" +
                        " can supply " + sourceArray[i].getCapacity() + " units to " +
                        destinationArray[j].getName() +
                        " with cost " + costMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("DEMANDS: ");

        for (int i = 0; i < destinationArray.length; i++) {
            System.out.println(destinationArray[i].getName() + " -> " + destinationArray[i].getDemand());

        }
        System.out.println();
    }
}


