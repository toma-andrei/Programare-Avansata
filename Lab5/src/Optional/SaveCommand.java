package Optional;

public class SaveCommand implements CatalogOperation {

    private Catalog catalog;
    private String name;

    public SaveCommand(Catalog catalog, String name) {
        this.catalog = catalog;
        this.name = name;
    }

    @Override
    public void execute() {
        catalog.save(name);
    }
}
