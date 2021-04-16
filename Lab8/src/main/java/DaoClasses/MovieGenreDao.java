package DaoClasses;

import Optional.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MovieGenreDao {

    public void add(String movieId, List<Integer> genres){

        Connection conn = null;

        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql = "insert into moviegenre values (?, ?)";

        PreparedStatement stmt;

        for (int i : genres) {
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, movieId);
                stmt.setInt(2, i);
                stmt.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
