package com.example.mystart;

import java.util.ArrayList;

public class order {
    private customer customer;

    private double totalPrice;

    //activity that contains the customer's order and the total price of his order
    public order() {
    }

    public order(customer customer) {
        this.customer = customer;
        totalPrice=0;
        for(int i=0;i<customer.getCart().size();i++){
            totalPrice+=customer.getCart().get(i).getPrice();
        }
    }

    public customer getCustomer() {
        return customer;
    }

    public void setCustomer(customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "place order by " +
                customer.getName()+"\n"+
                customer.getCart()+"\n"+
                "Total price: "+totalPrice+"shekels"
                ;
    }
}
