package com.example.mystart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText etemail,etpassword,etname,etphone;
    Button btnsignup;
    String email,password,name,phone;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    long Index;
    customer Customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        etemail=findViewById(R.id.etmail);
        etpassword=findViewById(R.id.etpass);
        btnsignup=findViewById(R.id.btnsignup);
        etname=findViewById(R.id.etname);
        etphone=findViewById(R.id.etphone);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=etemail.getText().toString().trim();
                password=etpassword.getText().toString().trim();
                name=etname.getText().toString().trim();
                phone=etphone.getText().toString().trim();
                Customer=new customer(name,phone,email);
                databaseReference=database.getReference("customers");
                func();


            }
        });


    }

    private void func() {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(signup.this,"createUserWithEmail:success",Toast.LENGTH_LONG).show() ;
                            databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(Customer);
                            startActivity(new Intent(signup.this,choose.class));

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(signup.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            Toast.makeText(signup.this,"createUserWithEmail:failure",
                                    Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });
    }
}
