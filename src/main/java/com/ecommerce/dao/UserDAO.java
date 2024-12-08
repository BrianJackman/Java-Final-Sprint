package com.ecommerce.dao;

import com.ecommerce.model.User;
import com.ecommerce.model.Buyer;
import com.ecommerce.model.Seller;
import com.ecommerce.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

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

    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
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

    public boolean saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password, email, role_id) VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole_id());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}