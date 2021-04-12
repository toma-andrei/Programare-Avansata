package com.company;

import java.sql.*;

/**
 * dao tutorial : https://www.baeldung.com/java-dao-pattern
 */

public class Main {

    public static void main(String[] args) throws SQLException {
        MovieDaoImplement daoObject = new MovieDaoImplement();
        daoObject.createGenre(1, "Comedy");
        daoObject.createGenre(2, "Irrelevant");
        daoObject.createGenre(3, "Mystery");
        daoObject.createGenre(4, "Psychological");
        daoObject.createGenre(5, "Action");
        daoObject.createGenre(6, "Drama");
        daoObject.createGenre(7, "Thriller");
        daoObject.createGenre(8, "Scary");
        daoObject.createGenre(9, "Raunchy");
        daoObject.createMovie(1, "Hot Fuzz", "2007-04-20", 121, 7.8f, 1, 2);
        daoObject.createMovie(2, "Split", "2017-01-20", 117, 7.3f, 3, 7);
        daoObject.createMovie(3, "Deadpool", "2016-02-12", 108, 8.0f, 1, 2);
        daoObject.createMovie(4, "Hachiko: A Dog's Story", "2009-03-12", 93, 8.1f, 6);
        daoObject.createMovie(5, "Miracle in Cell No. 7", "2018-10-11", 132, 8.2f, 6);
        daoObject.createMovie(6, "American Pie", "1999-07-09", 95, 7.0f, 1, 2);
        daoObject.createMovie(7, "American Pie", "2001-08-10", 108, 6.4f, 1, 2, 9);

        daoObject.findMovieById(2);

        daoObject.findGenreById(1);

        daoObject.findMovieByName("American Pie");

        daoObject.findGenreByName("Comedy");
    }
}
