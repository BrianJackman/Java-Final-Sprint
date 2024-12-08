package com.ecommerce.model;

public class Buyer extends User {
    public Buyer(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }
}