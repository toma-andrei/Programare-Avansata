package DaoClasses;

import Optional.DatabaseConnection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DirectorDao {

    public void add(String idMovie, String[] names) {
        Connection conn = null;
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
}
