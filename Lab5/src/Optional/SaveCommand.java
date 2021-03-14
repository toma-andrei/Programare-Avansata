package Optional;

public class SaveCommand implements CatalogOperation {

    private Catalog catalog;

    public SaveCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        catalog.save();
    }
}
