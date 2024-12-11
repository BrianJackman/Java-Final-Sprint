package com.ecommerce.dao;

import com.ecommerce.model.User;
import com.ecommerce.model.Buyer;
import com.ecommerce.model.Seller;
import com.ecommerce.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for user-related database operations.
 */
public class UserDAO {
    private Connection connection;

    /**
     * Constructs a new UserDAO.
     *
     * @param connection the database connection
     */
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new user to the database.
     *
     * @param user the user to add
     * @throws SQLException if a database access error occurs
     */
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, email, role, role_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getRole_id());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username
     * @return the user, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    String userUsername = rs.getString("username");
                    String userPassword = rs.getString("password");
                    String userEmail = rs.getString("email");
                    String userRole = rs.getString("role");
                    int userRoleId = rs.getInt("role_id");

                    switch (userRole.toLowerCase()) {
                        case "buyer":
                            return new Buyer(userId, userUsername, userPassword, userEmail, userRole, userRoleId);
                        case "seller":
                            return new Seller(userId, userUsername, userPassword, userEmail, userRole, userRoleId);
                        case "admin":
                            return new Admin(userId, userUsername, userPassword, userEmail, userRole, userRoleId);
                        default:
                            throw new SQLException("Unknown user role: " + userRole);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     * @throws SQLException if a database access error occurs
     */
    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt("id");
                String userUsername = rs.getString("username");
                String userPassword = rs.getString("password");
                String userEmail = rs.getString("email");
                String userRole = rs.getString("role");
                int userRoleId = rs.getInt("role_id");

                switch (userRole.toLowerCase()) {
                    case "buyer":
                        users.add(new Buyer(userId, userUsername, userPassword, userEmail, userRole, userRoleId));
                        break;
                    case "seller":
                        users.add(new Seller(userId, userUsername, userPassword, userEmail, userRole, userRoleId));
                        break;
                    case "admin":
                        users.add(new Admin(userId, userUsername, userPassword, userEmail, userRole, userRoleId));
                        break;
                    default:
                        throw new SQLException("Unknown user role: " + userRole);
                }
            }
        }
        return users;
    }

    /**
     * Deletes a user by username.
     *
     * @param username the username
     * @return true if the user was deleted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    public boolean deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}