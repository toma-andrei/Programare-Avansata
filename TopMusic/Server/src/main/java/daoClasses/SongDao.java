package daoClasses;

import daoClasses.databaseConnection.DatabaseConnection;
import entities.Artist;
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

        String sql = "INSERT INTO songs(name, description, link) VALUES(?,?,?)";

        String maxId = "SELECT max(id) id FROM songs";

        ResultSet rows;

        PreparedStatement stmt = null;

        int songId;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, song.getName());
            stmt.setString(2, song.getDescription());
            stmt.setString(3, song.getLink());

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

        sql = "INSERT INTO genres(id_song, name) VALUES(?,?)";

        try {
            for (Genre gen : song.getGenreList()) {
                stmt = conn.prepareStatement(sql);

                stmt.setInt(1, songId);
                stmt.setString(2, gen.getName());

                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        sql = "INSERT INTO artists(id_song, name) VALUES(?,?)";

        try {
            for (Artist artist : song.getArtistList()) {
                stmt = conn.prepareStatement(sql);

                stmt.setInt(1, songId);
                stmt.setString(2, artist.getName());

                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public synchronized List<Song> getByVotes() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlForSongs = "SELECT * FROM songs s ORDER BY votes";

        List<Song> songList = new ArrayList<>();

        PreparedStatement stmtForSongs;

        ResultSet songSet;
        ResultSet artistSet;

        GenreDao genreDao = new GenreDao();
        ArtistDao artistDao = new ArtistDao();

        try {
            stmtForSongs = conn.prepareStatement(sqlForSongs);

            songSet = stmtForSongs.executeQuery();

            while (songSet.next()) {
                Song song = new Song();
                song.setId(songSet.getInt("id"));
                song.setName(songSet.getString("name"));
                song.setDescription(songSet.getString("description"));
                song.setVotes(songSet.getInt("votes"));
                song.setAddedBy(songSet.getInt("addedBy"));
                song.setLink(songSet.getString("link"));
                song.setGenreList(genreDao.findById(song.getId(), conn));
                song.setArtistList(artistDao.findById(song.getId(), conn));

                songList.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }
}
