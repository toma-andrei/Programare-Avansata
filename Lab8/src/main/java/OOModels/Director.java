package OOModels;

public class Director {

    private String movieId;
    private String name;

    public Director(String movieId, String name){
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
