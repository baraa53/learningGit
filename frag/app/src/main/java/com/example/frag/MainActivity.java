package com.example.frag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements listFrag.ItemSelected{
    TextView tvDescription;
    String []description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDescription=findViewById(R.id.textView);
        description=getResources().getStringArray(R.array.descriptions);

        tvDescription.setText(description[0]);

        if(findViewById(R.id.layout_portrait)!=null){
            FragmentManager manager=this.getSupportFragmentManager();
            manager.beginTransaction().hide(manager.findFragmentById(R.id.lvdetail)).show(manager.findFragmentById(R.id.lvlist)).commit();
        }

        if(findViewById(R.id.layout_land)!=null){
            FragmentManager manager=this.getSupportFragmentManager();
            manager.beginTransaction().show(manager.findFragmentById(R.id.lvdetail)).show(manager.findFragmentById(R.id.lvlist)).commit();
        }
    }

    @Override
    public void onItemSelected(int index) {
        tvDescription.setText(description[index]);
        if(findViewById(R.id.layout_portrait)!=null){
            FragmentManager manager=this.getSupportFragmentManager();
            manager.beginTransaction().hide(manager.findFragmentById(R.id.lvlist)).show(manager.findFragmentById(R.id.lvdetail)).addToBackStack(null).commit();
        }
    }
}
