package com.ecommerce.model;

/**
 * Represents a seller in the system.
 */
public class Seller extends User {

    /**
     * Constructs a new Seller.
     *
     * @param user_id the user ID
     * @param username the username
     * @param password the password
     * @param email the email address
     * @param role the role of the user
     * @param role_id the role ID
     */
    public Seller(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }

    /**
     * Provides a string representation of the seller.
     *
     * @return a string representation of the seller
     */
    @Override
    public String toString() {
        return "Seller{" +
                "user_id=" + getUser_id() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                ", role_id=" + getRole_id() +
                '}';
    }
}