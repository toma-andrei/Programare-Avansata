package Optional;

import DaoClasses.MovieDao;
import OOModels.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * dao tutorial : https://www.baeldung.com/java-dao-pattern
 */

public class Main {

    public static void main(String[] args) {
//
//        CSVImporter importer = new CSVImporter();
//        importer.importData();

        MovieDao movie = new MovieDao();
        List<Movie> movieList;
        movieList = movie.getAll();
        movieList.forEach(m -> m.getActors().forEach(actor -> System.out.println(actor.getName())));
    }
}
