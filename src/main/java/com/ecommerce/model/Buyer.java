package com.ecommerce.model;

/**
 * Represents a buyer in the system.
 */
public class Buyer extends User {

    /**
     * Constructs a new Buyer.
     *
     * @param user_id the user ID
     * @param username the username
     * @param password the password
     * @param email the email address
     * @param role the role of the user
     * @param role_id the role ID
     */
    public Buyer(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }

    /**
     * Provides a string representation of the buyer.
     *
     * @return a string representation of the buyer
     */
    @Override
    public String toString() {
        return "Buyer{" +
                "user_id=" + getUser_id() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                ", role_id=" + getRole_id() +
                '}';
    }
}