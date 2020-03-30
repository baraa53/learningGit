package com.example.mystart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class choose extends AppCompatActivity {
    DatabaseReference myRef;
    List<String> product=new ArrayList<String>();
    ArrayAdapter<String> productsArrayAdapter;
    String[] productss;
    ListView lstv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        productss=getResources().getStringArray(R.array.products);
        for(int i=0;i<productss.length;i++){
            product.add(productss[i]);
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,product);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }
}