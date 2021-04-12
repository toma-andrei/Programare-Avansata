package com.company;


import java.sql.*;
//Data access object implementation

public class MovieDaoImplement implements MovieDao {

    private Connection conn = null;

    /***
     *
     * @param id - id of the movie
     * @param title - movie title
     * @param releaseDate - movie release date
     * @param duration - duration (in minutes) of the movie
     * @param score - imdb movie score
     * @param genres -  Array of ints. Each int is an id for a movie genre.
     */
    @Override
    public void createMovie(int id, String title, String releaseDate, float duration, float score, int... genres) {
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
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, releaseDate);
            stmt.setFloat(4, duration);
            stmt.setFloat(5, score);

            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // pentru fiecare film insereaza in tabela de asocieri genul corespunzator;

        sql = "insert into moviegenre values (?, ?)";

        for (int i : genres) {
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setInt(2, i);
                stmt.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createGenre(int id, String name) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql = "Insert into genres(id, name) values (?, ?)";
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);

            stmt.execute();

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void findMovieById(int id) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sqlForMovie = "Select * from movies where id=?";
        String sqlForGenre = "Select * from moviegenre inner join genres on moviegenre.idGen = genres.id where idMovie=?;";

        PreparedStatement stmtForMovie;
        PreparedStatement stmtForGenre;
        ResultSet rows;
        try {
            stmtForMovie = conn.prepareStatement(sqlForMovie);
            stmtForMovie.setInt(1, id);
            rows = stmtForMovie.executeQuery();
            System.out.println("MOVIE WITH ID: " + id);
            while (rows.next()) {
                System.out.println("id:           " + rows.getInt("id"));
                System.out.println("title:        " + rows.getString("title"));
                System.out.println("release_date: " + rows.getString("release_date"));
                System.out.println("duration:     " + rows.getFloat("duration"));
                System.out.println("score:        " + rows.getFloat("score"));
            }

            stmtForGenre = conn.prepareStatement(sqlForGenre);
            stmtForGenre.setInt(1, id);
            rows = stmtForGenre.executeQuery();
            System.out.print("Genre:        ");
            while (rows.next()) {
                System.out.print(rows.getString("name") + "  ");
            }
            System.out.println();
            System.out.println();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findGenreById(int id) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select * from genres where id=?";

        PreparedStatement stmt;
        ResultSet rows;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rows = stmt.executeQuery();
            System.out.println("GENRE WITH ID: " + id);
            while (rows.next()) {
                System.out.println(rows.getInt("id") + "  -  " + rows.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void findMovieByName(String name) {
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
                System.out.println("id:           " + rows.getInt("id"));
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

    @Override
    public void findGenreByName(String name) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql = "Select * from genres where name=?";
        PreparedStatement stmt;
        ResultSet rows;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            rows = stmt.executeQuery();
            System.out.println("GENRE WITH NAME: " + name);
            while (rows.next()) {
                System.out.println("id:   " + rows.getInt("id"));
                System.out.println("name: " + rows.getString("name"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}