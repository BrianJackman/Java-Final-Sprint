package com.ecommerce.service;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public void registerUser(User user) throws SQLException {
        // Hash the password before saving the user
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        System.out.println("Hashed password during registration: " + hashedPassword); // Debugging statement
        user.setPassword(hashedPassword);
        userDAO.addUser(user);
    }

    public boolean authenticateUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            System.out.println("User found: " + user.getUsername()); // Debugging statement
            System.out.println("Stored hashed password: " + user.getPassword()); // Debugging statement
            if (BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                System.out.println("Password does not match."); // Debugging statement
            }
        } else {
            System.out.println("User not found."); // Debugging statement
        }
        return false;
    }

    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(String username) throws SQLException {
        return userDAO.deleteUser(username);
    }
}