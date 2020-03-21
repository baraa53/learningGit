package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button Login,register;
    Intent i ;
    EditText Username,Password;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        databaseReference= database.getReference("Users");
        i= new Intent(getApplicationContext(),Weeelcome.class);
        Login= findViewById(R.id.LoginBtn);
        Username=findViewById(R.id.PlainLog);
        Password= findViewById(R.id.Plainpass);
        register=findViewById(R.id.Register);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(Username.getText().toString(),Password.getText().toString());
                System.out.println("user :"+Username.getText().toString());
                System.out.println("pass :"+Password.getText().toString());
                signInCheck(user.getUsername(),user.getPassword());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent g=  new Intent(getApplicationContext(), Register.class);
                                         startActivity(g);
                                     }
        });
    }
  /*  public void SignIn(final User user){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if(dataSnapshot.child(user.getUsername()).exists()){
                if(!user.getUsername().equals("")){

                    User Log = dataSnapshot.child(user.getUsername()).getValue(User.class);
                    if(Log.getPassword().equals(Password.getText().toString())){

                        startActivity(i);
                    }

                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }*/
    private void signInCheck(final String Username,final String Password) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(Username).exists()) {
                    if (!Username.isEmpty()) {
                        User player = dataSnapshot.child(Username).getValue(User.class);
                        if(player.getPassword().equals(Password)){
                            System.out.println("Hi");
                            startActivity(i);}
                        else{
                            Toast.makeText(MainActivity.this,"WrongPassword",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}
