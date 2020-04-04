package com.example.mystart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class quantity extends AppCompatActivity {
        int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        Intent intent=new Intent();
       // intent.getExtras("name",x);
    }
}
