package com.ecommerce.model;

public class Seller extends User {
    public Seller(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }

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