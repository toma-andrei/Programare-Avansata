package OOModels;

import java.util.ArrayList;
import java.util.List;

public class Actor {
    private String movieId;
    private String name;

    public Actor(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public static List<Movie> findByName(List<Movie> movies, String movieName) {

        List<Movie> movieList = new ArrayList<>();

        for (Movie i : movies) {
            if (i.getTitle().equals(movieName)) {
                movieList.add(i);
            }
        }

        return movieList;
    }

}
