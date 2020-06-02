package com.example.mystart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class choose extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference userRef;
    DatabaseReference ordersRef;
    ArrayList<String> product=new ArrayList<String>();
    fruitlistAdapter fruitlistAdapter;
    String[] productss;
    Dialog dialog,cartDialog;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    ExtendedFloatingActionButton placeorder,shopingCart,signOut;
     order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        userRef=database.getReference("customers").child(mAuth.getCurrentUser().getUid());
        placeorder=findViewById(R.id.placeorder);
        shopingCart=findViewById(R.id.shopingCart);
        signOut=findViewById(R.id.signout);
        ordersRef=database.getReference("orders");
        productss=getResources().getStringArray(R.array.products);

        for(int i=0;i<productss.length;i++){
            product.add(productss[i]);
  ;      }

        //listView shows the name of the product with photo and price
        fruitlistAdapter=new fruitlistAdapter(choose.this,product);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(fruitlistAdapter);

        //when the user pick an item it move on to the quantity class
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(choose.this,productss[position],Toast.LENGTH_LONG).show();
                Intent intent=new Intent(choose.this,quantity.class);
                intent.putExtra("name",position);
                startActivity(intent);
            }
        });

        //dialog page that shows the order with the total price
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(choose.this);
                dialog.setContentView(R.layout.order_confirm);
                dialog.setTitle("confirmation");
                final TextView orders=dialog.findViewById(R.id.orders);
                final   Button confirm=dialog.findViewById(R.id.confirm);
                final Button cancel=dialog.findViewById(R.id.cancel);

                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                        if(dataSnapshot.getValue(customer.class).getCart()==null){
                            confirm.setEnabled(false);
                            orders.setText("no items in the cart");
                        }
                        else{
                             order=new order(dataSnapshot.getValue(customer.class));
                            confirm.setEnabled(true);
                            orders.setText(order.toString());
                        }

                        //In here it add the order to the database.
                        confirm.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                ordersRef.push().setValue(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(choose.this,"your order was passed",Toast.LENGTH_LONG).show();

                                       customer c= dataSnapshot.getValue(customer.class);
                                       c.getCart().clear();
                                        userRef.setValue(c);
                                        dialog.cancel();

                                    }
                                });


                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        //In here it shows what is there in rhe cart with the possibility to edit or delete it
        shopingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDialog=new Dialog(choose.this);
                cartDialog.setContentView(R.layout.shoping_cart);
                cartDialog.setTitle("my cart");
                final ListView cartList=cartDialog.findViewById(R.id.cartList);
                //add the changes to the database
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<cartItem> newCart=dataSnapshot.getValue(customer.class).getCart();
                        if(newCart==null){
                            newCart=new ArrayList<cartItem>();
                        }

                        cartItemsAdapter cartItemsAdapter=new cartItemsAdapter(choose.this,newCart);
                        cartList.setAdapter(cartItemsAdapter);
                        cartItemsAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                final   Button ok=cartDialog.findViewById(R.id.ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cartDialog.cancel();
                    }
                });
                cartDialog.show();

            }
        });

        //obviously it signs out :)
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent=new Intent(choose.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
