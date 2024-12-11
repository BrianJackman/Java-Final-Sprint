package com.ecommerce.model;

/**
 * Represents a generic user in the system.
 */
public abstract class User {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String role;
    private int role_id;

    /**
     * Constructs a new User.
     *
     * @param user_id the user ID
     * @param username the username
     * @param password the password
     * @param email the email address
     * @param role the role of the user
     * @param role_id the role ID
     */
    public User(int user_id, String username, String password, String email, String role, int role_id) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.role_id = role_id;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets the user ID.
     *
     * @param user_id the user ID
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role of the user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the role ID.
     *
     * @return the role ID
     */
    public int getRole_id() {
        return role_id;
    }

    /**
     * Sets the role ID.
     *
     * @param role_id the role ID
     */
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    /**
     * Provides a string representation of the user.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}