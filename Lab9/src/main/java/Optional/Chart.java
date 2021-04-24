package Optional;

import entities.Movie;

import java.util.*;

public class Chart {

    private String name;
    private List<Movie> movieList = new ArrayList<>();
    private Date createdAt;

    public Chart(String name, List<Movie> movieList){
        this.name = name;
        this.movieList = movieList;
        this.createdAt = new Date();

        Comparator<Movie> compareByScore = (Movie m1, Movie m2) -> m1.getScore().compareTo(m2.getScore());

        Collections.sort(this.movieList, compareByScore.reversed());
    }

    public List<Movie> getMovieList(){
        return movieList;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return createdAt;
    }
}
