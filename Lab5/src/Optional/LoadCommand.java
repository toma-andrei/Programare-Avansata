package Optional;

public class LoadCommand implements CatalogOperation {
    private String fileName;
    Catalog catalog;

    public LoadCommand(Catalog catalog, String fileName) {
        this.fileName = fileName;
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        catalog.load(fileName);
    }
}
