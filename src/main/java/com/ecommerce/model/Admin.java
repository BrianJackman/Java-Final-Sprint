package com.ecommerce.model;

public class Admin extends User {
    public Admin(int user_id, String username, String password, String email, String role, int role_id) {
        super(user_id, username, password, email, role, role_id);
    }
}