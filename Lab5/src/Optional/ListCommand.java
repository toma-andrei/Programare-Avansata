package Optional;

public class ListCommand implements CatalogOperation {

    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        catalog.list();
    }
}
