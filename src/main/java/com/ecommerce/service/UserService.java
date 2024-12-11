package com.ecommerce.service;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles user-related operations such as registration, authentication, and user management.
 */
public class UserService {
    private UserDAO userDAO;

    /**
     * Constructs a new UserService.
     *
     * @param connection the database connection
     */
    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    /**
     * Registers a new user.
     *
     * @param user the user to register
     * @throws SQLException if a database access error occurs
     */
    public void registerUser(User user) throws SQLException {
        // Hash the password before saving the user
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.addUser(user);
    }

    /**
     * Authenticates a user.
     *
     * @param username the username
     * @param password the password
     * @return true if authentication is successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    public boolean authenticateUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username
     * @return the user, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     * @throws SQLException if a database access error occurs
     */
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    /**
     * Deletes a user by username.
     *
     * @param username the username
     * @return true if the user was deleted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    public boolean deleteUser(String username) throws SQLException {
        return userDAO.deleteUser(username);
    }
}