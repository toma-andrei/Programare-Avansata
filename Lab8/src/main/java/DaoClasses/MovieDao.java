package DaoClasses;

import OOModels.Actor;
import OOModels.Director;
import OOModels.Genre;
import OOModels.Movie;
import Optional.DatabaseConnection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    private Connection conn;

    public MovieDao() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void add(String id, String title, String releaseDate, float duration, float score, List<Integer> genres) {
        //se conecteaza la baza de date, folosing clasa DatabaseConnection (singleton)
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // creaza un enunt sql cu ajutorul caruia se va interoga baza de date.
        //semnele de intrebare sunt "placeholdere" pentru valori dintr-o anumita tabela.
        // Nu sunt scrise direct numele de coloane pentru a evita SQL injection.

        String sql = "Insert into movies(id, title, release_date, duration, score) values (?, ?, ?, ?, ?)";

        // Interogare ce va fi executata pe baza de date.
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);

            //insereaza valorile pe pozitiile specificate in primul argument
            stmt.setString(1, id);
            stmt.setString(2, title);
            stmt.setString(3, releaseDate);
            stmt.setFloat(4, duration);
            stmt.setFloat(5, score);

            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // pentru fiecare film insereaza in tabela de asocieri genul corespunzator;

//        sql = "insert into moviegenre values (?, ?)";
//
//        for (int i : genres) {
//            try {
//                stmt = conn.prepareStatement(sql);
//                stmt.setString(1, id);
//                stmt.setInt(2, i);
//                stmt.execute();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//
//        try {
//            conn.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        MovieGenreDao movieGenres = new MovieGenreDao();
        movieGenres.add(id, genres);
    }

    public void findByName(String name) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sqlForMovie = "Select * from movies where title=?";
        String sqlForGenre = "Select * from moviegenre inner join genres on moviegenre.idGen = genres.id where idMovie=?;";

        PreparedStatement stmtForMovie;
        PreparedStatement stmtForGenre;

        ResultSet rows;

        ResultSet rowsForGenre;

        try {
            stmtForMovie = conn.prepareStatement(sqlForMovie);
            stmtForMovie.setString(1, name);
            rows = stmtForMovie.executeQuery();
            System.out.println("MOVIE WITH NAME: " + name);
            while (rows.next()) {
                System.out.println("id:           " + rows.getString("id"));
                System.out.println("title:        " + rows.getString("title"));
                System.out.println("release_date: " + rows.getString("release_date"));
                System.out.println("duration:     " + rows.getFloat("duration"));
                System.out.println("score:        " + rows.getFloat("score"));
                stmtForGenre = conn.prepareStatement(sqlForGenre);
                stmtForGenre.setInt(1, rows.getInt("id"));
                rowsForGenre = stmtForGenre.executeQuery();
                System.out.print("Genre:        ");

                while (rowsForGenre.next()) {
                    System.out.print(rowsForGenre.getString("name") + "  ");
                }
                System.out.println();
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getAll() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<Movie> movieList = new ArrayList<>();

        String sqlForMovie = "Select * from movies;";
        String sqlForGenre = "Select * from moviegenre inner join genres on moviegenre.idGen = genres.id where idMovie=?;";
        String sqlForActor = "Select * from actors where idMovie=?;";
        String sqlForDirector = "Select * from directors where idMovie=?;";

        PreparedStatement stmtForMovie;
        PreparedStatement stmtForGenre;
        PreparedStatement stmtForActor;
        PreparedStatement stmtForDirector;

        ResultSet rows;

        ResultSet rowsForGenre;
        ResultSet genreRows;
        ResultSet actorRows;
        ResultSet directorRows;

        try {
            stmtForMovie = conn.prepareStatement(sqlForMovie);

            rows = stmtForMovie.executeQuery();

            while (rows.next()) {

                String id = rows.getString("id");
                String title = rows.getString("title");
                String releaseDate = rows.getString("release_date");
                int duration = rows.getInt("duration");
                float score = rows.getFloat("score");
                List<Genre> genres = new ArrayList<>();
                List<Actor> actors = new ArrayList<>();
                List<Director> directors = new ArrayList<>();

                stmtForGenre = conn.prepareStatement(sqlForGenre);
                stmtForGenre.setString(1, id);

                genreRows = stmtForGenre.executeQuery();


                while (genreRows.next()) {
                    genres.add(new Genre(genreRows.getInt("id"), genreRows.getString("name")));
                }

                stmtForActor = conn.prepareStatement(sqlForActor);
                stmtForActor.setString(1,id);

                actorRows = stmtForActor.executeQuery();

                while(actorRows.next()){
                    actors.add(new Actor(id, actorRows.getString("full_name")));
                }

                stmtForDirector = conn.prepareStatement(sqlForDirector);
                stmtForDirector.setString(1,id);

                directorRows = stmtForDirector.executeQuery();

                while(directorRows.next()){
                    directors.add(new Director(id, directorRows.getString("full_name")));
                }


                movieList.add(new Movie(id, title, releaseDate, duration, score, genres, actors, directors));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }

}
