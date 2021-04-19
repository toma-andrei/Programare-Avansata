package com.company;

import entities.Movie;
import jdk.swing.interop.SwingInterOpUtils;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import repositories.MovieRepo;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * dao tutorial : https://www.baeldung.com/java-dao-pattern
 */

public class Main {

    public static void main(String[] args) throws ParseException {
        MovieRepo repositoryMovie = new MovieRepo();
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

        List<Movie> movieList = repositoryMovie.findByName("American Pie");
        System.out.println("MOVIES WITH NAME \"American Pie\": ");
        movieList.forEach(m -> {
            System.out.println("id:       " + m.getId());
            System.out.println("title:    " + m.getTitle());
            System.out.println("released: " + m.getReleaseDate());
            System.out.println();
        });
    }
}
