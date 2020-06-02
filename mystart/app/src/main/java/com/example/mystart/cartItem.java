package com.example.mystart;

import android.content.Context;

public class cartItem {
    private double amount,price;
    private int position;
    private String product;
    //activity that contain the amount of the item and its name and calculate the price for this amount
    public cartItem() {
    }

    public cartItem(double amount, Context context, int position) {
        this.amount = amount;
        this.position=position;
        product=context.getResources().getStringArray(R.array.products)[position];
        price =Double.parseDouble(context.getResources().getStringArray(R.array.prices)[position])*amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return
                "amount=" + amount +
                ", price=" + price +
                ", product='" + product+"" +
                        "\n"
                ;
    }
}
