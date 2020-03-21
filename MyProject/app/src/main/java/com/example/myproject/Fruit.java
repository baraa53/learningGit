package com.example.myproject;

public class Fruit {
    private String name,ImageID;
    private double price;

    public Fruit(String name, String imageID, double price) {
        this.name = name;
        ImageID = imageID;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", ImageID='" + ImageID + '\'' +
                ", price=" + price +
                '}';
    }
}
