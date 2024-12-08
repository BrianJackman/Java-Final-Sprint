package com.ecommerce.model;


public class Admin extends User{
    public Admin(int user_id, String username, String email, String password){
        super(user_id, username, email, password, "ADMIN", 3);
    }
    public Admin( String username, String email, String password) {
        super(0, username, email, password, "ADMIN", 3);
    }
}

