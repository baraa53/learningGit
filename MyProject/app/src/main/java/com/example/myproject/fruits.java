package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class fruits extends AppCompatActivity {
    ArrayAdapter adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        ListView listView=findViewById(R.id.fruit_list);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        adapt=new ArrayAdapter(fruits.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapt);
        adapt.notifyDataSetChanged();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return onCreateOptionsMenu(menu);
    }
}
