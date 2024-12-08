package com.ecommerce.model;

public class Buyer extends User {
    public Buyer(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }

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