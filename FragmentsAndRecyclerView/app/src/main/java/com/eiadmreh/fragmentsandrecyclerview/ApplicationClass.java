package com.eiadmreh.fragmentsandrecyclerview;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Person>people;
    @Override
    public void onCreate() {
        super.onCreate();
        people=new ArrayList<Person>();
        people.add(new Person("Adil Emam","0526487952"));
        people.add(new Person("Ahmad Zake","0526487666"));
        people.add(new Person("Yonis Shalaby","0526487333"));
        people.add(new Person("Saeed Salih","0526487111"));
        people.add(new Person("Adel Emam","0526487952"));
        people.add(new Person("Ahmad Zake","0526487666"));
        people.add(new Person("Yonis Shalaby","0526487333"));
        people.add(new Person("Saeed Salih","0526487111"));
    }
}
