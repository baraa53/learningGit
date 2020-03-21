package com.example.project;

public class User {
    protected String Username,Password,Phonenum,Email;

    public User(String username, String password) {
        Username = username;
        Password = password;
    }

    public User(String username, String password, String phonenum, String email) {
        Username = username;
        Password = password;
        Phonenum = phonenum;
        Email = email;
    }

    public String getPhonenum() {
        return Phonenum;
    }

    public void setPhonenum(String phonenum) {
        Phonenum = phonenum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
