package daoClasses;

import daoClasses.databaseConnection.DatabaseConnection;
import entities.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDao {

    public List<Genre> findById(Integer id, Connection conn) {

        String sqlForGenres = "SELECT name from genre where id_song=:id_song";

        PreparedStatement stmtForGenres;
        ResultSet genreSet;
        List<Genre> genreList = new ArrayList<>();

        try {
            stmtForGenres = conn.prepareStatement(sqlForGenres);

            stmtForGenres.setInt(1, id);

            genreSet = stmtForGenres.executeQuery();

            while (genreSet.next()) {
                Genre gen = new Genre();
                gen.setName(genreSet.getString("name"));
                genreList.add(gen);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return genreList;
    }
}
