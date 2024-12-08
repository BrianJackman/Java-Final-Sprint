package com.ecommerce.model;

public class Seller extends User{
    public Seller(int user_id, String username, String email, String password){
        super(user_id, username, email, password, "SELLER", 1);
    }
    public Seller(String username, String email, String password){
        super(0, username, email, password, "SELLER", 1);
    }
    
}
