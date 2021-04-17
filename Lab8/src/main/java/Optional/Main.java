package Optional;

import DaoClasses.DirectorDao;
import DaoClasses.GenreDao;
import DaoClasses.MovieDao;
import OOModels.Director;
import OOModels.Genre;
import OOModels.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * dao tutorial : https://www.baeldung.com/java-dao-pattern
 */

public class Main {

    public static void main(String[] args) {

//        CSVImporter importer = new CSVImporter();
//        importer.importData();

        MovieDao movie = new MovieDao();
        List<Movie> movieList;
        movieList = movie.getAll();
        Movie.print(movieList);

        GenreDao genres = new GenreDao();
        List<Genre> genreList;
        genreList = genres.getAll();

        System.out.println("LIST OF ALL GENRES: ");
        genreList.forEach(g -> System.out.println(g.getName()));
        System.out.println();

        DirectorDao directors = new DirectorDao();
        List<Director> directorList;
        directorList = directors.getAll();

        System.out.println("LIST OF ALL DIRECTORS: ");
        directorList.forEach(d -> System.out.println(d.getName()));
        System.out.println();

    }
}
