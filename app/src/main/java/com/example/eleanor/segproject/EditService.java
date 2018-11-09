package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;


public class EditService extends AppCompatActivity {

    public ServiceList serviceList = new ServiceList();

    EditText serviceName;
    EditText newPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

    }

    public void onEditServiceClick(View view){

        serviceName = findViewById(R.id.ServiceToEdit);
        newPrice = findViewById(R.id.NewPriceOfService);

        TextView ServiceDoesNotExist = findViewById(R.id.ServiceDoesNotExist);

        if (!serviceList.isIn(serviceName.getText().toString())){
            ServiceDoesNotExist.setText("That Service Does Not Exist");
        }
        else {
            serviceList.editService(serviceName.getText().toString(), Double.parseDouble(newPrice.getText().toString()));

            Intent HOIntent = new Intent(EditService.this, Admin.class);
            startActivity(HOIntent);
        }
    }
}
