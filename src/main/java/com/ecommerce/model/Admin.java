package com.ecommerce.model;

/**
 * Represents an admin in the system.
 */
public class Admin extends User {

    /**
     * Constructs a new Admin.
     *
     * @param user_id the user ID
     * @param username the username
     * @param password the password
     * @param email the email address
     * @param role the role of the user
     * @param role_id the role ID
     */
    public Admin(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }

    /**
     * Provides a string representation of the admin.
     *
     * @return a string representation of the admin
     */
    @Override
    public String toString() {
        return "Admin{" +
                "user_id=" + getUser_id() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                ", role_id=" + getRole_id() +
                '}';
    }
}