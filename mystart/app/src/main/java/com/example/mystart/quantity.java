package com.example.mystart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class quantity extends AppCompatActivity {
        int x;
        TextView tvProduct,tvPrice;
        EditText etAmount;
        ImageView ivPhoto;
        Button btnAdd,cancel;
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference storageReference;
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference;
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
            storageReference= storage.getReference();
            databaseReference=firebaseDatabase.getReference("customers");
            tvProduct=findViewById(R.id.tvProduct);
            tvPrice=findViewById(R.id.tvPrice);
            etAmount=findViewById(R.id.etAmount);
            ivPhoto=findViewById(R.id.ivPhoto);
            btnAdd=findViewById(R.id.btnAdd);
            cancel=findViewById(R.id.btnBack);

          x=getIntent().getIntExtra("name",0);
          storageReference.child(getResources().getStringArray(R.array.products)[x]).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
              @Override
              public void onSuccess(Uri uri) {
                  Glide.with(quantity.this).load(uri.toString()).placeholder(R.drawable.ic_launcher_foreground).into(ivPhoto);
              }
          });

          //going back to the previous page without adding any thing to the cart
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(quantity.this,choose.class);
                    startActivity(intent);
                }
            });

          tvProduct.setText(getResources().getStringArray(R.array.products)[x]);
          tvPrice.setText(getResources().getStringArray(R.array.prices)[x]+" shekels/kg");

          //add the amount with the product name and the price to the database
          btnAdd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final cartItem cartItem=new cartItem(Double.parseDouble(etAmount.getText().toString()),quantity.this,x);
                    databaseReference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            customer customer=dataSnapshot.getValue(com.example.mystart.customer.class);
                           if(customer.getCart()==null){
                               ArrayList<cartItem> newCart=new ArrayList<cartItem>();
                               customer.setCart(newCart);
                           }
                            customer.getCart().add(cartItem);
                            databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(customer);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Intent intent=new Intent(quantity.this,choose.class);
                    startActivity(intent);
              }
          });

    }
}
