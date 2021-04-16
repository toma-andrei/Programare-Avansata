package DaoClasses;

import Optional.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreDao {

    private Connection conn;

    // in map tin minte genurile care au fost deja adaugate in tabela genres.
    Map<String, Integer> genreMap = new HashMap<>();

    int genreId = 1;

    public List<Integer> add(String[] genres) {

        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i = 0; i < genres.length; i++) {
            genres[i] = genres[i].trim();
        }

        List<Integer> genresIds = new ArrayList<>();

        String sql = "Insert into genres(id, name) values (?, ?)";

        PreparedStatement stmt;

        for (String i : genres) {
            if (genreMap.containsKey(i)) {
                genresIds.add(genreMap.get(i));
            } else {
                genreMap.put(i, genreId);
                genresIds.add(genreId);
                try {
                    stmt = conn.prepareStatement(sql);

                    stmt.setInt(1, genreId);
                    stmt.setString(2, i);

                    genreId++;

                    stmt.execute();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genresIds;
    }

    public void findById(int id) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select * from genres where id=?";

        PreparedStatement stmt;
        ResultSet rows;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rows = stmt.executeQuery();
            System.out.println("GENRE WITH ID: " + id);
            while (rows.next()) {
                System.out.println(rows.getInt("id") + "  -  " + rows.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
