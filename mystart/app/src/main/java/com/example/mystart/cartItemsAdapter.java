package com.example.mystart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class cartItemsAdapter extends ArrayAdapter<cartItem> {
    Context context;
    ArrayList<cartItem> items;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("customers");
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    //this activity extends ArrayAdapter to allow showing textView with more two button on the listview

    public cartItemsAdapter(@NonNull Context context ,ArrayList<cartItem> items) {
        super(context, 0,items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row==null)
              row= LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        TextView amount=row.findViewById(R.id.rowAmount);
        TextView name=row.findViewById(R.id.fruitName);
        Button edit=row.findViewById(R.id.edit);
        Button delete=row.findViewById(R.id.delete);
        name.setText(items.get(position).getProduct());
        amount.setText("amount: "+items.get(position).getAmount()+" kg");


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(position);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<cartItem> cart= dataSnapshot.getValue(customer.class).getCart();
                        cart.remove(position);
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("cart").setValue(cart);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return row;

    }

    //in this function the amount of the product changes as the customer want and it change it on the firebase with changing the price to fit the new amount and change the total price
    public void showDialog( final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inf.inflate(R.layout.edit_amount, null);
        builder.setView(view);
        Button confirm=view.findViewById(R.id.dialogconfirm);
        final EditText amount=view.findViewById(R.id.dialodAmount);
        amount.setText(items.get(position).getAmount()+"");
        final AlertDialog ad=builder.show();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<cartItem> cart= dataSnapshot.getValue(customer.class).getCart();
                        cartItem newItem=cart.get(position);
                        newItem.setPrice((newItem.getPrice()/newItem.getAmount())*Double.parseDouble(amount.getText().toString()));
                        newItem.setAmount(Double.parseDouble(amount.getText().toString()));



                        cart.set(position,newItem);
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("cart").setValue(cart);
                        ad.dismiss();
                        notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
