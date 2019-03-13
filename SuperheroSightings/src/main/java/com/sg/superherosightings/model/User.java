package com.sg.superherosightings.model;

import java.util.ArrayList;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class User {

    private int user_id;
    @NotEmpty(message = "You must supply a value for username.")
    @Length(max = 20, message = "Username must be no longer than 20 characters in length")
    private String username;
    @NotEmpty(message = "You must supply a value for password.")
    @Length(max = 20, message = "Password must be no longer than 20 characters in length")
    private String password;
    private ArrayList<String> authorities = new ArrayList<>();

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<String> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(String authority) {
        authorities.add(authority);
    }
}