package com.ecommerce.service;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import com.ecommerce.model.User;

public class UserService extends User{
    public UserService(String username, String password, String email, String role){
        super(0, username, email, password, role, 0);
    }

    public boolean registerUser(Connection conn, String username, String password, String email, String role) throws SQLException{
        String sql = "INSERT INTO users(username, password, email, role_id) VALUES(?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, role);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new SQLException("Error registering user", e);
        }
    }

    public String authUser(Connection conn) throws SQLException{
        String sql = "SELECT password, role FROM users WHERE username = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, getUsername());

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String hashedPassword = rs.getString("password");
                    String role = rs.getString("role");
                    
                    if(BCrypt.checkpw(hashedPassword, getPassword())){
                        System.out.println("User authenticated, Role: " + role);
                    } else {
                        System.out.println("Invalid password");
                    }

                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error authenticating user", e);
        }

        return null;
    }

    public boolean saveUser(Connection conn) throws SQLException{
        String sql = "INSERT INTO users(username, password, email, role_id) VALUES(?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, getUsername());
            ps.setString(2, getPassword());
            ps.setString(3, getEmail());
            ps.setInt(4, role_id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
