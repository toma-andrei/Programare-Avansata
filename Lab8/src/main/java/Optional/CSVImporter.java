package Optional;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVImporter {

    public void importData() {
        String line;

        int genreId = 1;

        Map<String, Integer> genreMap = new HashMap<>();

        boolean isOkToIterate = false;

        try {
            MovieDaoImplement movieDao = new MovieDaoImplement();
            BufferedReader reader = new BufferedReader((new FileReader("C:\\Users\\toma1\\OneDrive\\Desktop\\IMDB_movies.csv")));
            while ((line = reader.readLine()) != null) {
                if (!isOkToIterate) {
                    isOkToIterate = true;
                    continue;
                }

                String[] splittedLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                String movieId = splittedLine[0];
                String movieTitle = splittedLine[1];
                String releaseDate = splittedLine[4];
                String[] genres = splittedLine[5].replace("\"", "").split(",");

                for(int i = 0; i < genres.length; i++){
                    genres[i] = genres[i].trim();
                }

                int duration = Integer.parseInt(splittedLine[6]);
                String director = splittedLine[9];
                String[] actors = splittedLine[12].split(",");
                float score = Float.parseFloat(splittedLine[14]);

                List<Integer> genresIds = new ArrayList<>();

                for (String i : genres) {
                    if (genreMap.containsKey(i)) {
                        genresIds.add(genreMap.get(i));
                    } else {
                        genreMap.put(i, genreId);
                        genresIds.add(genreId);
                        movieDao.createGenre(genreId, i);
                        genreId++;
                    }
                }

                movieDao.createMovie(movieId, movieTitle, releaseDate, duration, score, genresIds);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
