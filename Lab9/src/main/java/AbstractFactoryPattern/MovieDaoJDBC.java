package AbstractFactoryPattern;

import Optional.DatabaseConnection;
import entities.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDaoJDBC implements Dao<Movie> {
    Connection conn;

    @Override
    public void add(Movie movie) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int id = movie.getId();
        String title = movie.getTitle();
        Date releaseDate = movie.getReleaseDate();
        float duration = movie.getDuration();
        float score = movie.getScore();

        String sql = "Insert into movies(id, title, release_date, duration, score) values (?, ?, ?, ?, ?)";

        // Interogare ce va fi executata pe baza de date.
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);

            //insereaza valorile pe pozitiile specificate in primul argument
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setDate(3, (java.sql.Date) releaseDate);
            stmt.setFloat(4, duration);
            stmt.setFloat(5, score);

            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Movie> getAll() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<Movie> movieList = new ArrayList<>();

        String sqlForMovie = "Select * from movies;";

        PreparedStatement stmtForMovie;

        ResultSet rows;

        try {
            stmtForMovie = conn.prepareStatement(sqlForMovie);

            rows = stmtForMovie.executeQuery();

            //parcurge fiecare linie din tabela movies. Pentru fiecare film ii cauta genurile, actorii si regizorii.
            while (rows.next()) {
                int id = rows.getInt("id");
                String title = rows.getString("title");
                Date releaseDate = rows.getDate("release_date");
                int duration = rows.getInt("duration");
                float score = rows.getFloat("score");

                movieList.add(new Movie(id, title, releaseDate, duration, score));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movieList;
    }
}