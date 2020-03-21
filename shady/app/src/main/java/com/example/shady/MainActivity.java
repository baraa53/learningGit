package com.example.shady;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    ImageView ivhappy,ivphone,ivWeb,ivLocation;
    String name,phone,web,location,mood;
    Button btnCreate;
    final int intentCode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        ivhappy=findViewById(R.id.ivhappy);
        ivphone=findViewById(R.id.ivphone);
        ivWeb=findViewById(R.id.ivWeb);
        ivLocation=findViewById(R.id.ivLocation);
        btnCreate=findViewById(R.id.cndCreateContact);
        //hiding icons
        ivhappy.setVisibility(View.GONE);
        ivphone.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);
        ///////////////////////////////////////////////////////////////////////////////////////////
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(i,intentCode);
            }
        });
        ivphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                startActivity(in);
            }
        });
        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+web));
                startActivity(in);
            }
        });
        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q="+location));
                startActivity(in);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==intentCode) {
           if (resultCode == RESULT_OK) {
               ivhappy.setVisibility(View.VISIBLE);
               ivphone.setVisibility(View.VISIBLE);
               ivWeb.setVisibility(View.VISIBLE);
               ivLocation.setVisibility(View.VISIBLE);
               name=data.getStringExtra("name");
               tvTitle.setText(name);
               phone=data.getStringExtra("phone");
               web=data.getStringExtra("web");
               location=data.getStringExtra("location");
               mood=data.getStringExtra("mood");
               if(mood.equals("happy"))
                   ivhappy.setImageResource(R.drawable.happy);
               else if(mood.equals("sad"))
                   ivhappy.setImageResource(R.drawable.sad);
               else
                   ivhappy.setImageResource(R.drawable.ok);
           }
           Toast.makeText(MainActivity.this,"i love shady and layan and byan and ahmad forsan ",Toast.LENGTH_SHORT).show();
       }
    }
}
