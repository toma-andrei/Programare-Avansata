package formatter;

import entities.Artist;
import entities.Genre;
import entities.Song;

import java.util.List;
import java.util.ResourceBundle;

public class SongFormatter {

    public StringBuilder format(List<Song> songList, ResourceBundle messages) {
        StringBuilder output = new StringBuilder("");

        for (Song song : songList) {
            output.append(song.getId()).append(".");
            output.append(messages.getString("songName")).append(": ").append(song.getName()).append("/LNSEP/");
            int spaces = 1;
            output.append(messages.getString("artists")).append(": ");
            for (Artist artist : song.getArtistList()) {
                if (spaces < song.getArtistList().size())
                    output.append(artist.getName()).append("/LNSEP/").append("         ");
                else
                    output.append(artist.getName()).append("/LNSEP/");
                spaces++;
            }
            output.append(messages.getString("description")).append(": ").append(song.getDescription()).append("/LNSEP/");
            output.append(messages.getString("votes")).append(": ").append(song.getVotes()).append("/LNSEP/");
            output.append(messages.getString("genres")).append(": ");

            spaces = 1;
            for (Genre gen : song.getGenreList()) {
                if (spaces < song.getGenreList().size())
                    output.append(gen.getName()).append("/LNSEP/").append("        ");
                else
                    output.append(gen.getName()).append("/LNSEP/");
                spaces++;
            }


            output.append(messages.getString("addedBy")).append(": ").append(song.getAddedBy()).append("/LNSEP/");
            output.append(messages.getString("link")).append(": ").append(song.getLink()).append("/LNSEP/");
            output.append("/LNSEP/");

        }

        return output;
    }
}
