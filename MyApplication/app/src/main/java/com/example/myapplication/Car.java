package com.example.myapplication;

public class Car {
    private String name,info;
    private int year;

    public Car() {
    }

    public Car(String name, String info, int year) {
        this.name = name;
        this.info = info;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", year=" + year +
                '}';
    }
}
