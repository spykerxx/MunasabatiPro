package com.example.Munasabati;

public class User {
    private String username;
    private String phone;
    private String email;
    private String pass;
    private String type;

    public User(String username, String phone, String email, String pass, String type) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
        this.type=type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
