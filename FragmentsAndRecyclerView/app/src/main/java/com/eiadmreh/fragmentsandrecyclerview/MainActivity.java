package com.eiadmreh.fragmentsandrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import static com.eiadmreh.fragmentsandrecyclerview.R.layout.row_layout;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemSelected
{
    TextView tvName,tvPhone,tvDate;
    EditText etName,etPhone;
    ImageView ivContact;
    Button btnAdd;
    String CurrentDate;
    Calendar calendar=Calendar.getInstance();
    FragmentManager fragmentManager;
    listFrag listFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName=findViewById(R.id.tvName);
        tvPhone=findViewById(R.id.tvPhone);
        tvDate=findViewById(R.id.tvdate);
        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        ivContact=findViewById(R.id.ivContact);
        btnAdd=findViewById(R.id.btnAdd);
        fragmentManager=this.getSupportFragmentManager();
        listFrag =(listFrag)fragmentManager.findFragmentById(R.id.listFrag);

        CurrentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        tvDate.setText(CurrentDate);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty()||etPhone.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this, "please fillup all fields !!!", Toast.LENGTH_SHORT).show();
                else {
                    ApplicationClass.people.add(new Person(etName.getText().toString().trim(), etPhone.getText().toString().trim()));
                    Toast.makeText(MainActivity.this, "New person was added successfuly..", Toast.LENGTH_SHORT).show();
                    etName.setText(null);
                    etPhone.setText(null);
                    listFrag.notifyDataSetChanged();
                }
            }
        });
        onItemClicked(0);
        ivContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=tvPhone.getText().toString().trim();
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClicked(int Index) {
        tvName.setText(ApplicationClass.people.get(Index).getName());
        tvPhone.setText(ApplicationClass.people.get(Index).getPhone());
    }
}
