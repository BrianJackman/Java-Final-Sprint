package com.ecommerce;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The main application class that tests the database connection.
 */
public class App {

    /**
     * The main method to start the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}