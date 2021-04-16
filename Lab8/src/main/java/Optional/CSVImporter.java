package Optional;

import DaoClasses.ActorDao;
import DaoClasses.DirectorDao;
import DaoClasses.GenreDao;
import DaoClasses.MovieDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVImporter {

    public void importData() {
        String line;

        boolean isOkToIterate = false;

        try {
            MovieDao movieDao = new MovieDao();
            GenreDao genreDao = new GenreDao();
            DirectorDao directorDao = new DirectorDao();
            ActorDao actorDao = new ActorDao();

            BufferedReader reader = new BufferedReader((new FileReader("C:\\Users\\toma1\\OneDrive\\Desktop\\IMDB_movies.csv")));
            while ((line = reader.readLine()) != null) {
                if (!isOkToIterate) {
                    isOkToIterate = true;
                    continue;
                }

                //face split dupa virgule, exceptandu-le pe cele dintre ghilimele.

                String[] splittedLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                String movieId = splittedLine[0];
                String movieTitle = splittedLine[1];
                String releaseDate = splittedLine[4];
                String[] genres = splittedLine[5].replace("\"", "").split(",");

                int duration = Integer.parseInt(splittedLine[6]);
                String[] directors = splittedLine[9].replace("\"", "").split(",");
                String[] actors = splittedLine[12].replace("\"", "").split(",");
                float score = Float.parseFloat(splittedLine[14]);

                List<Integer> genresIds;

                genresIds = genreDao.add(genres);
                movieDao.add(movieId, movieTitle, releaseDate, duration, score, genresIds);
                directorDao.add(movieId, directors);
                actorDao.add(movieId, actors);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
