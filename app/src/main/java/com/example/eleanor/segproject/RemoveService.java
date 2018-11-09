package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;


public class RemoveService extends AppCompatActivity {

    public ServiceList serviceList = new ServiceList();

    EditText serviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_service);

    }

    public void onNewServiceClick(View view){
        serviceName = findViewById(R.id.serviceName);

        serviceList.removeService(serviceName.getText().toString());

        Intent HOIntent = new Intent(RemoveService.this, Admin.class);
        startActivity(HOIntent);
    }
}

