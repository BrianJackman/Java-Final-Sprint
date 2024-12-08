package com.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "postgres";
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "PostgresPass";

    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to database with URL: " + URL); // Debugging statement
        System.out.println("Using username: " + USER); // Debugging statement
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}