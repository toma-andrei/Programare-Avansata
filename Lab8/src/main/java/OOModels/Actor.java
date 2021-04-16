package OOModels;

public class Actor {
    private String movieId;
    private String name;

    public Actor(String movieId, String name){
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }
}
