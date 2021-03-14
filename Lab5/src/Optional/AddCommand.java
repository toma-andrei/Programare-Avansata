package Optional;

public class AddCommand implements CatalogOperation {

    private Catalog catalog;
    private MyFile file;

    public AddCommand(Catalog catalog, MyFile file) {
        this.catalog = catalog;
        this.file = file;
    }

    @Override
    public void execute() throws GeneralException {
        catalog.add(file);
    }
}
