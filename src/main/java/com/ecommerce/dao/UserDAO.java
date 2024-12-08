package com.ecommerce.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.User;

public class UserDAO {
    public static Connection connectToDB() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    public String getPassword(String username) throws SQLException {
        String sql = "SELECT password, role_id FROM users WHERE username = ?";

        try(Connection conn = connectToDB();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getString("password");
            } else {
                return null;
            }
            } catch (SQLException e) {
                throw new SQLException("Error getting password", e);
                
            }
    }

    public void getUsers() throws SQLException {
        String sql = "SELECAT * FROM users";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int userID = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");

                // Print out the user information
                System.out.println("User ID: " + userID + " Username: " + username + " Email: " + email + " Password: " + password);
            }
        } catch (SQLException e) {
            System.out.println("Error getting users");
            throw e;
        }

    }

    public int login(String username, String password)  throws SQLException{
        String sql = "SELECT password, role_id FROM users WHERE username = ?";

        try (Connection conn = connectToDB();
             PreparedStatement selectStmt = conn.prepareStatement(sql)) {

            // Retrieve the current hashed password and role_id
            selectStmt.setString(1, username);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                String currentHashedPassword = rs.getString("password");
                int roleId = rs.getInt("role_id");

                // Compare the provided password with the hashed password
                if (!BCrypt.checkpw(password, currentHashedPassword)) {
                    return 0; // Login failed, return 0 (or other failure indicator)
                }

                // Login successful
                return roleId;

            } else {
                return 0; // Username not found, return 0
            }
        } catch (SQLException e) {
            System.out.println("Error while logging in");
            return 0; // Return 0 if an exception occurs
        }
    }

    public void addUser(User user) throws SQLException{
        String sql = "INSERT INTO users (username, email, password, role_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = connectToDB();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole_id());

            // Execute the query
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Error adding user.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding user");
            throw e;
        }
    }

    public void deleteUser(String username) throws SQLException{
        String sql = "DELETE FROM users WHERE username = ?";

        try(Connection conn = connectToDB();
        PreparedStatement ps = conn.prepareStatement(sql)){

            // Set the username parameter
            ps.setString(1, username);

            // Execute the query
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Error deleting user.");
            }
        
        } catch (SQLException e) {
            System.out.println("Error deleting user");
            throw e;
        }
    }

    public void updatePassword(String username, String oldPassword, String newPassword) throws SQLException{
        String sql = "SELECT password FROM users WHERE username = ?";
        String updateSql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection connection = connectToDB();
             PreparedStatement selectStmt = connection.prepareStatement(sql);
             PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

                // Retrieve the current hashed password
                selectStmt.setString(1, username);
                ResultSet rs = selectStmt.executeQuery();

                if(rs.next()){
                    String currentHashedPassword = rs.getString("password");

                    // Compare the provided old password with the hashed password
                    if(!BCrypt.checkpw(oldPassword, currentHashedPassword)){
                        System.out.println("Old password is incorrect.");
                        return;
                    }
                }

                // Hash the new password
                String newhashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

                // Update the password
                updateStmt.setString(1, newhashedPassword);
                updateStmt.setString(2, username);

                // Execute the update
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Password updated successfully.");
                } else {
                    System.out.println("Error updating password.");
                }
            } catch (SQLException e) {
                System.out.println("Error updating password");
                throw e;
            }
        
    }   
    
    public void changeRole(String username, int newRoleId) throws SQLException{
        if(newRoleId < 1 || newRoleId > 3){
            System.out.println("Invalid role ID");
            return;
        }

        String sql = "UPDATE users SET role_id = ? WHERE username = ?";

        try(Connection conn = connectToDB();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, newRoleId);
            ps.setString(2, username);

            // Execute the query
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Role updated successfully.");
            } else {
                System.out.println("Error updating role.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating role");
            throw e;
        }
    }

    public void updateEmail(String username, String newEmail) throws SQLException{
        String sql = "UPDATE users SET email = ? WHERE username = ?";

        try(Connection conn = connectToDB();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, newEmail);
            ps.setString(2, username);

            // Execute the query
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Email updated successfully.");
            } else {
                System.out.println("Error updating email.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating email");
            throw e;
        }
    }

    public int getRoleIdByUsername(String username) throws SQLException{
        String sql = "SELECT role_id FROM users WHERE username = ?";

        try(Connection conn = connectToDB();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("role_id");
            } else {
                System.out.println("User not found");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error getting role ID");
            throw e;
        }
    }
}
            
