package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;


public class AddService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

    }

    public void onNewServiceClick(View view){


        LISTOFSERVICES.add(service);

        Intent HOIntent = new Intent(AddService.this, Admin.class);
        startActivity(HOIntent);
    }
}
