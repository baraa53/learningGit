package com.example.mystart;


public class products {
    String name,image;
    double price;

    public products(String name, double price) {
        this.name = name;
        this.image = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "products{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}

