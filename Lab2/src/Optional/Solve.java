package Optional;

public class Solve extends Problem {
    public void solve(Source sourceArray[], Destination destinationArray[], int costMatrix[][], int n) {

        int[] supplies = new int[n];
        int[] demands = new int[n];

        for (int i = 0; i < n; i++) {
            supplies[i] = sourceArray[i].getCapacity();
            demands[i] = destinationArray[i].getDemand();
        }

        int totalCost = 0, consumedSupply = 0, satisfiedDemand = 0;

        while (true) {
            if (demands[satisfiedDemand] > supplies[consumedSupply]) {

                System.out.println(sourceArray[consumedSupply] + " -> " + destinationArray[satisfiedDemand] +
                        ": " + supplies[consumedSupply] + "units with cost " +
                        (costMatrix[consumedSupply][satisfiedDemand] * supplies[consumedSupply]));

                totalCost += (costMatrix[consumedSupply][satisfiedDemand] * supplies[consumedSupply]);

                demands[satisfiedDemand] -= supplies[consumedSupply];

                consumedSupply++;

            } else if (demands[satisfiedDemand] < supplies[consumedSupply]) {

            }
            else{

            }

        }
    }
}
