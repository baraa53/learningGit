package com.example.mystart;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class fruitlistAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> arrayList;

    FirebaseStorage storage=FirebaseStorage.getInstance();
    StorageReference storageReference=storage.getReference();

    //this activity extends ArrayAdapter to allow showing textView with ImageView on the lestView

    public fruitlistAdapter(@NonNull Context context, ArrayList<String> arrayList) {
        super(context, 0,arrayList);
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fruitRow=convertView;
        if(fruitRow==null)
            fruitRow= LayoutInflater.from(context).inflate(R.layout.fruit_row,parent,false);
        TextView fruitName=fruitRow.findViewById(R.id.rowfruitName);
        TextView price=fruitRow.findViewById(R.id.rowprice);
        final ImageView picture=fruitRow.findViewById(R.id.rowphoto);

        fruitName.setText(context.getResources().getStringArray(R.array.products)[position]);
        price.setText(context.getResources().getStringArray(R.array.prices)[position]+" shekels");

        storageReference.child(arrayList.get(position)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               Glide.with(context).load(uri.toString()).placeholder(R.drawable.ic_launcher_foreground).into(picture);
            }
        });

        return fruitRow;
    }
}
