package com.example.shady;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText name,phone,web,location;
    ImageView sad,ok,happy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.etname);
        phone=findViewById(R.id.etphone);
        web=findViewById(R.id.etweb);
        location=findViewById(R.id.etlocation);
        sad=findViewById(R.id.ivsad);
        ok=findViewById(R.id.ivok);
        happy=findViewById(R.id.ivhappy2);
        ok.setOnClickListener(this);

        sad.setOnClickListener(this);
        happy.setOnClickListener(this);

        //////////////////////////////////////////////////////////////////////////////////////////


    }


   /* public void onClick_all(View v) {
        int mood=v.getId();
        if(name.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||web.getText().toString().isEmpty()||location.getText().toString().isEmpty())
            Toast.makeText(Main2Activity.this,"pls fill up all fields",Toast.LENGTH_SHORT).show();
        else{
            Intent intent=new Intent();
            if(mood==R.id.ivhappy2)
                intent.putExtra("mood","happy");
            else if(mood==R.id.ivok)
                intent.putExtra("mood","ok");
            else
                intent.putExtra("mood","sad");
            intent.putExtra("name",name.getText().toString().trim());
            intent.putExtra("phone",phone.getText().toString().trim());
            intent.putExtra("web",web.getText().toString().trim());
            intent.putExtra("location",location.getText().toString().trim());
            setResult(RESULT_OK,intent);
            Main2Activity.this.finish();
        }

    }*/

    @Override
    public void onClick(View v) {
        int mood=v.getId();
        if(name.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||web.getText().toString().isEmpty()||location.getText().toString().isEmpty())
            Toast.makeText(Main2Activity.this,"pls fill up all fields",Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent();
            if (mood == R.id.ivhappy2)
                intent.putExtra("mood", "happy");
            else if (mood == R.id.ivok)
                intent.putExtra("mood", "ok");
            else
                intent.putExtra("mood", "sad");
            intent.putExtra("name", name.getText().toString().trim());
            intent.putExtra("phone", phone.getText().toString().trim());
            intent.putExtra("web", web.getText().toString().trim());
            intent.putExtra("location", location.getText().toString().trim());
            setResult(RESULT_OK, intent);
            Main2Activity.this.finish();
        }
    }
}
