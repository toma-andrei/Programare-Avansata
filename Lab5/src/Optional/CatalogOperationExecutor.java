package Optional;

/**
 * Command Pattern: Trateaza comenzile ca si obiecte. Intareste Incapsularea
 * https://www.tutorialspoint.com/design_pattern/command_pattern.htm
 */


public class CatalogOperationExecutor {
    /***
     * @param catalogOperation - o instanta a unei comenzi ce urmeaza a fi executata
     */
    public void executeOperation(CatalogOperation catalogOperation) throws GeneralException {
        catalogOperation.execute();
    }
}
