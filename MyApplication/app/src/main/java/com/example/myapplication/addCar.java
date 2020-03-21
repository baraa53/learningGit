package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addCar extends AppCompatActivity {
    private EditText name,year,info;
    private ImageButton addCar;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("cars");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        //my code
        name=(EditText)findViewById(R.id.name);
        year=(EditText)findViewById(R.id.year);
        info=(EditText)findViewById(R.id.info);
        addCar=(ImageButton)findViewById(R.id.imageButton);

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()) {
                    name.setError("please enter a name");
                    name.requestFocus();//btro7 3name 3shan n3be
                    return;
                }
                if(year.getText().toString().isEmpty()) {
                    year.setError("please enter a name");
                    year.requestFocus();//btro7 3year 3shan n3be
                    return;
                }
                if(info.getText().toString().isEmpty()) {
                    info.setError("please enter a name");
                    info.requestFocus();//btro7 3info 3shan n3be
                    return;
                }
                Car car=new Car(name.getText().toString(),info.getText().toString(),Integer.parseInt(year.getText().toString()));
                System.out.println(car.toString());
                myRef.push().setValue(car);
            }
        });


    }

   /* private void addcar(Car c){


        myRef.push().setValue(c).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(addCar.this," succeeded",Toast.LENGTH_SHORT);
            }
        });
        myRef.push().setValue(c).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addCar.this," failed",Toast.LENGTH_SHORT);
            }
        });
    }*/
}
