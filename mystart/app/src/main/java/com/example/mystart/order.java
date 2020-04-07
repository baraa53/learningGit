package com.example.mystart;

import java.util.ArrayList;

public class order {
    private customer customer;
    ArrayList<cartItem> items;

    public order() {
    }

    public order(com.example.mystart.customer customer, ArrayList<cartItem> items) {
        this.customer = customer;
        this.items = items;
    }

    public com.example.mystart.customer getCustomer() {
        return customer;
    }

    public void setCustomer(com.example.mystart.customer customer) {
        this.customer = customer;
    }

    public ArrayList<cartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<cartItem> items) {
        this.items = items;
    }
}
