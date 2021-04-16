package DaoClasses;

import Optional.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActorDao {

    Connection conn;
    public void add(String movieId, String[] actors){
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for(int i = 0; i < actors.length; i++){
            actors[i] = actors[i].trim();
        }

        String sql = "Insert into actors (idMovie, full_name) values(?, ?)";
        PreparedStatement stmt;

        try {
            for(String i : actors) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, movieId);
                stmt.setString(2, i);

                stmt.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
