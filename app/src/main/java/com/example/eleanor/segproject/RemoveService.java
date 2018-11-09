package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    public void onRemoveServiceClick(View view){

        serviceName = findViewById(R.id.ServiceToRemove);

        TextView ServiceDoesNotExist = findViewById(R.id.ServiceDoesNotExist);

        if (!serviceList.isIn(serviceName.getText().toString())){
            ServiceDoesNotExist.setText("That Service Does Not Exist");
        }
        else {
            serviceList.removeService(serviceName.getText().toString());

            Intent HOIntent = new Intent(RemoveService.this, Admin.class);
            startActivity(HOIntent);
        }
    }
}

