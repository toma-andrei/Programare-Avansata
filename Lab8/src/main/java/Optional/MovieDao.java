package Optional;

import java.util.List;

//  https://www.baeldung.com/java-dao-pattern
public interface MovieDao {
    public void createMovie(String id, String title, String releaseDate, float duration, float score, List<Integer> genres);
    public void createGenre(int id, String name);
    public void findMovieById(int id);
    public void findGenreById(int id);
    public void findMovieByName(String name);
    public void findGenreByName(String name);
}
