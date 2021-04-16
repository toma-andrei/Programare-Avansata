package DaoClasses;

import OOModels.Actor;
import Optional.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Actor> getAll() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Actor> actors = new ArrayList<>();

        String sql = "Select * from actors;";

        PreparedStatement stmt;

        ResultSet rows;

        try {
            stmt = conn.prepareStatement(sql);
            rows = stmt.executeQuery();

            while (rows.next()) {
                actors.add(new Actor(rows.getString("idMovie"), rows.getString("full_name")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return actors;
    }
}
