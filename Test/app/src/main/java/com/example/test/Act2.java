package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Act2 extends AppCompatActivity {
    TextView txt;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);
        txt=(TextView)findViewById(R.id.textView2);
        Bundle extras=getIntent().getExtras();
        s=extras.getString("key");
        txt.setTextSize(36);
        txt.setText(s);
    }
}
