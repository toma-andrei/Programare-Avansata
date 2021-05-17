package daoClasses.databaseConnection;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection conn = null;

    private DatabaseConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String mysqlUrl = "jdbc:mysql://localhost:3306/topmusic";
            String username = "topmusic";
            String password = "TOPMUSIC";

            Class.forName(driver);
            this.conn = DriverManager.getConnection(mysqlUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.conn.isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
