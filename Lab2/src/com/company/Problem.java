package com.company;

public class Problem extends Object {
    /**
     * author: Toma Andrei-Paul
     */

    //metoda afiseaza informatii despre parametrii primiti.
    public void printProblem(Source sourceArray[], Destination destinationArray[], int costMatrix[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(sourceArray[i].getName() + " (" + sourceArray[i].getSourceType() + ")" +
                        " can supply " + sourceArray[i].getCapacity() + " units to " +
                        destinationArray[j].getName() + " (" + destinationArray[j].getDestinationType() + ")" +
                        " with cost " + costMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("DEMANDS: ");

        for (int i = 0; i < n; i++) {
            System.out.println(destinationArray[i].getName() + " -> " + destinationArray[i].getDemand());

        }

    }

    public String toString() {
        return "Problem class!";
    }
}
