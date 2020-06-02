package com.example.mystart;

import java.util.ArrayList;

public class customer {
    String name,phone,email;
    ArrayList<cartItem> cart;
    public customer() {
    }

    //activity that contains the details of the customer

    public customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        cart=new ArrayList<cartItem>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<cartItem> getCart() {
        return cart;
    }

    public void setCart(ArrayList<cartItem> cart) {
        this.cart = cart;
    }

    public void setName(String name) {
        this.name = name;
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
}
