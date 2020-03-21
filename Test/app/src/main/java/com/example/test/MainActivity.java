package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button,nextpage;
    TextView txtview;
    EditText editTxt;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        txtview=(TextView)findViewById(R.id.textView);
        editTxt=(EditText)findViewById(R.id.editText);
        nextpage=(Button)findViewById(R.id.next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=editTxt.getText().toString();
                if(name.isEmpty())
                    txtview.setText("no text found");
                else
                 txtview.setText(editTxt.getText().toString());
            }
        });

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Act2.class);
                intent.putExtra("key",name);
                startActivity(intent);
            }
        });
    }
}
