package com.example.mystart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button signinbtn,signupbtn;
    TextView welcome;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinbtn=(Button)findViewById(R.id.signIn);
        signupbtn=(Button)findViewById(R.id.signUp);
        welcome=(TextView)findViewById(R.id.welcome);

       //if the user had signed in before it opens the choose class
        if(mAuth.getCurrentUser()!=null){
            Intent intent=new Intent(MainActivity.this,choose.class);
            startActivity(intent);
        }

        //the user picks if he wants to sign in or sign up and by intent he will go to the page he chose
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,signin.class);
                startActivity(intent);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });
    }
}
