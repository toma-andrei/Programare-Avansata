package daoClasses;

import daoClasses.databaseConnection.DatabaseConnection;
import entities.Comment;
import entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Comment> findBySongId(String id) {
        try {
            conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Comment> commentList = new ArrayList<>();

        String sql = "Select * from comments where id_song = ?";

        String commentWriter = "Select username from users u inner join songs s on s.addedBy = u.id where s.id = ?";

        PreparedStatement stmt;

        ResultSet comments;
        ResultSet usernameSet;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            comments = stmt.executeQuery();

            while (comments.next()) {

                String username;
                Comment comment = new Comment();

                stmt = conn.prepareStatement(commentWriter);
                stmt.setString(1,id);
                usernameSet = stmt.executeQuery();
                usernameSet.next();

                username = usernameSet.getString("username");
                comment.setSongId(comments.getInt("id_song"));

                comment.setUsername(username);
                comment.setComment(comments.getString("comment"));
                commentList.add(comment);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return commentList;
    }
}
