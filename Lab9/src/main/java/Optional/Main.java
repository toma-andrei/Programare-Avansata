package Optional;

import AbstractFactoryPattern.AbstractFactory;
import AbstractFactoryPattern.Dao;
import AbstractFactoryPattern.FactoryProducer;
import entities.Genre;
import entities.Movie;
import repositories.AbstractRepository;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * dao tutorial : https://www.baeldung.com/java-dao-pattern
 */

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        AbstractRepository<Movie> repositoryMovie = new AbstractRepository<>(new Movie());
        Date releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse("2007-04-20");
        Movie movie;
        try {
            movie = new Movie(1, "Hot Fuzz", releaseDate, 121, 7.8f);
            repositoryMovie.create(movie);

            releaseDate = new SimpleDateFormat("yyy-MM-dd").parse("2017-01-20");
            movie = new Movie(2, "Split", releaseDate, 117, 7.3f);
            repositoryMovie.create(movie);

            releaseDate = new SimpleDateFormat("yyy-MM-dd").parse("2016-02-12");
            movie = new Movie(3, "Deadpool", releaseDate, 108, 8.0f);
            repositoryMovie.create(movie);

            releaseDate = new SimpleDateFormat("yyy-MM-dd").parse("2009-03-12");
            movie = new Movie(4, "Hachiko: A Dog's Story", releaseDate, 93, 8.1f);
            repositoryMovie.create(movie);

            releaseDate = new SimpleDateFormat("yyy-MM-dd").parse("1999-07-09");
            movie = new Movie(5, "American Pie", releaseDate, 95, 7.0f);
            repositoryMovie.create(movie);

            releaseDate = new SimpleDateFormat("yyy-MM-dd").parse("2001-08-10");
            movie = new Movie(6, "American Pie", releaseDate, 108, 6.4f);
            repositoryMovie.create(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        movie = repositoryMovie.findById(2);
        System.out.println("MOVIE WITH ID = 2: ");
        System.out.println("id:    " + movie.getId());
        System.out.println("title: " + movie.getTitle());
        System.out.println();
        List<Movie> movieList = repositoryMovie.findAll();

        AbstractRepository<Genre> repositoryGenre = new AbstractRepository<>(new Genre());
        Genre genre = new Genre(1, "Comedy");
        repositoryGenre.create(genre);
        genre = new Genre(2, "Action");
        repositoryGenre.create(genre);
        genre = new Genre(3, "Drama");
        repositoryGenre.create(genre);
        genre = new Genre(4, "Romance");
        repositoryGenre.create(genre);

//        List<Genre> genreList = repositoryGenre.findByName("Comedy");
//        System.out.println("GENRE WITH NAME \"COMEDY\":");
//
//        genreList.forEach(gen -> System.out.println(gen.getId()));

        File file = new File("config.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String implementation = br.readLine();

        implementation = implementation.trim();

        AbstractFactory abstractFact = FactoryProducer.getFactory(implementation);

        Dao<Movie> film = abstractFact.getDaoObject("Movie");

        releaseDate = new SimpleDateFormat("yyy-MM-dd").parse("1972-03-24");
        java.sql.Date sqlReleaseDate = new java.sql.Date(releaseDate.getTime());

        film.add(new Movie(7, "The Godfather", sqlReleaseDate, 175, 9.2f));
        movieList = film.getAll();

        Chart movieChart = new Chart("Sorted By Score", movieList);

        movieChart.getMovieList().forEach(m -> System.out.println(m.getTitle() + "   Score: " + m.getScore()));

    }
}
