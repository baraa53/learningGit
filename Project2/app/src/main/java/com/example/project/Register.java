package com.example.project;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.User;
import com.example.project.Weeelcome;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference PlayerInfo;
    Button RegisterButton,LoginButton;
    EditText Username,Password,Email,Phonenumb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = FirebaseDatabase.getInstance();
        PlayerInfo= database.getReference("PlayersInfo");
        RegisterButton = findViewById(R.id.RegisterButtttton);
        LoginButton =  findViewById(R.id.LoginButton);
        Username= findViewById(R.id.username);
        Password= findViewById(R.id.password);
        Email =findViewById(R.id.Email);
        Phonenumb = findViewById(R.id.Phonenum);

      RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User player = new User(Username.getText().toString(),Password.getText().toString(),Phonenumb.getText().toString(),Email.getText().toString());
                PlayerInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(player.getUsername()).exists()){
                            Toast.makeText(Register.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(player.getEmail().equals(""))
                                Toast.makeText(Register.this, "Email is empty!", Toast.LENGTH_SHORT).show();
                            if(!player.getEmail().equals("")){
                                Toast.makeText(Register.this, "Registration complete!", Toast.LENGTH_SHORT).show();
                                PlayerInfo.child(player.getUsername()).setValue(player);
                                Intent s = new Intent(getApplicationContext(), Weeelcome.class);
                                startActivity(s);
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Z = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Z);
            }
        });
    }
}

