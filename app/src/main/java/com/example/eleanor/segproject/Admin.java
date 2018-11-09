package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

    }

    public void onAddServiceClick(View view){
        Intent addService = new Intent(Admin.this, AddService.class);
        startActivity(addService);
    }

    public void onEditServiceClick(View view){
        Intent editService = new Intent(Admin.this, EditService.class);
        startActivity(editService);
    }

    public void onRemoveServiceClick(View view){
        Intent removeService = new Intent(Admin.this, RemoveService.class);
        startActivity(removeService);
    }



}

