package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
