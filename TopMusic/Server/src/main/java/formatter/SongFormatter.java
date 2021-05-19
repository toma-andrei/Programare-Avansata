package formatter;

import entities.Artist;
import entities.Genre;
import entities.Song;

import java.util.List;

public class SongFormatter {

    public StringBuilder format(List<Song> songList) {
        StringBuilder output = new StringBuilder("");

        for (Song song : songList) {
            output.append(song.getId()).append(".");
            output.append("Song name: ").append(song.getName()).append("/LNSEP/");
            output.append("Description: ").append(song.getDescription()).append("/LNSEP/");
            output.append("Votes: ").append(song.getVotes()).append("/LNSEP/");
            output.append("Genres: ");

            int spaces = 0;
            for (Genre gen : song.getGenreList()) {
                if (spaces < song.getGenreList().size())
                    output.append(gen.getName()).append("/LNSEP/").append("        ");
                else
                    output.append(gen.getName()).append("/LNSEP/");
                spaces++;
            }

            spaces = 0;
            output.append("Artists: ");
            for (Artist artist : song.getArtistList()) {
                if (spaces < song.getArtistList().size())
                    output.append(artist.getName()).append("/LNSEP/").append("         ");
                else
                    output.append(artist.getName()).append("/LNSEP/");
                spaces++;
            }

            output.append("Added by: ").append(song.getAddedBy()).append("/LNSEP/");
            output.append("Link: ").append(song.getLink()).append("/LNSEP/");
        }

        return output;
    }
}
