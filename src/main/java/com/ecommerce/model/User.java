package com.ecommerce.model;
import org.mindrot.jbcrypt.BCrypt;

public abstract class User{
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String role;
    public int role_id;


    public User(int user_id, String username, String password, String email, String role, int role_id){
        this.user_id = user_id;
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.email = email;
        this.role = role;
        this.role_id = role_id;
    }
    
    // getters and setters
    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public int getRole_id(){
        return role_id;
    }

    public void setRole_id(int role_id){
        this.role_id = role_id;
    }
}