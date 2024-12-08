package com.ecommerce.model;

public class Seller extends User {
    public Seller(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }
}