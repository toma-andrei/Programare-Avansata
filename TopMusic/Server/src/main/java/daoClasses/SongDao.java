package daoClasses;

import daoClasses.databaseConnection.DatabaseConnection;
import entities.Genre;
import entities.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDao {
    Connection conn = null;

    public synchronized boolean create(Song song) {

        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO songs(name, description, artists, link) VALUES(?,?,?,?)";

        String maxId = "SELECT max(id) id FROM songs";

        ResultSet rows;

        PreparedStatement stmt = null;

        int songId = -1;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, song.getName());
            stmt.setString(2, song.getDescription());
            stmt.setString(3, song.getArtists());
            stmt.setString(4, song.getLink());

            stmt.execute();

            stmt = conn.prepareStatement(maxId);

            rows = stmt.executeQuery();

            if (rows.next()) {
                songId = rows.getInt("id");
            } else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        sql = "INSERT INTO genres(id_song, gen_name) VALUES(?,?)";

        try {
            for (Genre gen : song.getGenreList()) {
                stmt = conn.prepareStatement(sql);

                stmt.setInt(1, songId);
                stmt.setString(2, gen.getName());

                stmt.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public synchronized List<Song> getByVotes() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sqlForSongs = "SELECT * FROM songs s ORDER BY votes";
        String sqlForGenres = "SELECT name from genre where id_song=:id_song";

        List<Song> songList = new ArrayList<>();

        PreparedStatement stmtForSongs;
        PreparedStatement stmtForGenres;
        ResultSet songSet;
        ResultSet genreSet;

        try {
            stmtForSongs = conn.prepareStatement(sqlForSongs);

            songSet = stmtForSongs.executeQuery();

            while (songSet.next()) {
                Song song = new Song();
                song.setId(songSet.getInt("id"));
                song.setName(songSet.getString("name"));
                song.setDescription(songSet.getString("description"));
                song.setArtists(songSet.getString("artists"));
                song.setVotes(songSet.getInt("votes"));
                song.setLink(songSet.getString("link"));

                stmtForGenres = conn.prepareStatement(sqlForGenres);

                stmtForGenres.setInt(1, song.getId());

                genreSet = stmtForGenres.executeQuery();

                while (genreSet.next()) {
                    Genre gen = new Genre();
                    gen.setName(genreSet.getString("name"));
                    song.addGen();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
