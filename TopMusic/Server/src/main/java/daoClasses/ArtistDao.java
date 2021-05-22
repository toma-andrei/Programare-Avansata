package daoClasses;

import entities.Artist;
import entities.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDao {

    public List<Artist> findById(Integer id, Connection conn) {

        String sqlForArtists = "Select name from artists where id_song=?";

        PreparedStatement stmtForArtists;
        ResultSet artistSet;
        List<Artist> artistList = new ArrayList<>();

        try {
            stmtForArtists = conn.prepareStatement(sqlForArtists);

            stmtForArtists.setInt(1, id);

            artistSet = stmtForArtists.executeQuery();

            while (artistSet.next()) {
                Artist artist = new Artist();
                artist.setName(artistSet.getString("name"));
                artistList.add(artist);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return artistList;
    }
}

