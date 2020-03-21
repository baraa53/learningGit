package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    ArrayList<Fruit> arrayList;
    Context context;
    public CustomAdapter(Context context, ArrayList<Fruit> arrayList) {
        super(context,R.layout.fruit_list);
        this.arrayList=arrayList;
        this.context=context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = arrayList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.fruit_list, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            TextView title = convertView.findViewById(R.id.txt_title);
            ImageView imag = convertView.findViewById(R.id.img_image);
            TextView Price=convertView.findViewById(R.id.txt_price);


        }
        return convertView;
    }
}
