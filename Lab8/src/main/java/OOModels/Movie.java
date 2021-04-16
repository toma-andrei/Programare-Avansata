package OOModels;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String id;
    private String title;
    private String releaseDate;
    private int duration;
    private float score;
    List<Genre> genres;
    List<Actor> actors;
    List<Director> directors;

    public Movie(String id, String title, String releaseDate, int duration, float score, List<Genre> genres,List<Actor> actors, List<Director> directors) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.genres = genres;
        this.actors = actors;
        this.directors = directors;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public float getScore() {
        return score;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
