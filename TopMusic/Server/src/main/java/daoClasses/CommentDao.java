package daoClasses;

import daoClasses.databaseConnection.DatabaseConnection;
import entities.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDao {
    Connection conn;

    public boolean create(Comment comment) {

        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO comments(id_song, username, comment) VALUES(?,?,?)";

        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, comment.getSongId());
            stmt.setString(2, comment.getUsername());
            stmt.setString(3, comment.getComment());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
