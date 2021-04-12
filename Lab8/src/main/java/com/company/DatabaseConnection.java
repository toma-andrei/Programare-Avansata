package com.company;

import java.sql.*;

/**
 * O Clasa Singleton e o clasa ce poate avea o singura instanta.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection conn = null;
    private String mysqlUrl = "jdbc:mysql://localhost:3306/movie";
    private String username = "movie";
    private String password = "MOVIE";
    private String driver = "com.mysql.cj.jdbc.Driver";

    private DatabaseConnection() {
        try {
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

    public Connection getConnection(){
        return conn;
    }
}
