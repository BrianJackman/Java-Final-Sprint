package com.ecommerce.model;

public class Buyer extends User{
    public Buyer(int user_id, String username, String email, String password){
        super(user_id, username, email, password, "BUYER", 2);
    }
    public Buyer(String username, String email, String password){
        super(0, username, email, password, "BUYER", 2);
    }
}
