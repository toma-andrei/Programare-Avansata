package DaoClasses;

import OOModels.Director;
import OOModels.Genre;
import Optional.DatabaseConnection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDao {

    Connection conn = null;

    public void add(String idMovie, String[] names) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].trim();
        }

        StringBuilder sql = new StringBuilder("Insert into directors(idMovie, full_name) values(?, ?);");

        PreparedStatement stmt;

        for (String i : names) {
            try {
                stmt = conn.prepareStatement(String.valueOf(sql));
                stmt.setString(1, idMovie);
                stmt.setString(2, i);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Director> getAll() {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Director> directors = new ArrayList<>();

        String sql = "Select * from directors;";

        PreparedStatement stmt;

        ResultSet rows;

        try {
            stmt = conn.prepareStatement(sql);
            rows = stmt.executeQuery();

            while (rows.next()) {
                directors.add(new Director(rows.getString("idMovie"), rows.getString("full_name")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return directors;
    }
}
