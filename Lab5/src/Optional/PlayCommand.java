package Optional;

import java.awt.font.TextMeasurer;

public class PlayCommand implements CatalogOperation {
    private Catalog catalog;
    private String toBePlayed;

    public PlayCommand(Catalog catalog, String elemName) {
        this.catalog = catalog;
        toBePlayed = elemName;
    }

    @Override
    public void execute() {

        catalog.play(toBePlayed);
    }
}
