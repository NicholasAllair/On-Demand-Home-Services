package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;


public class AddService extends AppCompatActivity {

    public ServiceList serviceList = new ServiceList();
    private DatabaseReference mDatabase;

    Service newService;
    EditText serviceName;
    EditText hourlyPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

    }

    public void onNewServiceClick(View view){
        serviceName = findViewById(R.id.serviceName);
        hourlyPrice = findViewById(R.id.hourlyPrice);

        TextView isNameValid = findViewById(R.id.ServiceNameValid);
        TextView isPriceValid = findViewById(R.id.priceValid);


        if(serviceName.getText().toString().isEmpty()){
            isNameValid.setText("Service name invalid");
        }
        if(hourlyPrice.getText().toString().isEmpty()){
            isPriceValid.setText("Price invalid");
        }
        else {
            // this is how you write to the database
            String name = serviceName.getText().toString();
            Double price = Double.valueOf(hourlyPrice.getText().toString());
            mDatabase = FirebaseDatabase.getInstance().getReference();
            Service service = new Service(name, price);
            mDatabase.child("service").push().setValue(service);

            serviceList.addService(serviceName.getText().toString(), Double.parseDouble(hourlyPrice.getText().toString()));

            newService = new Service(serviceName.getText().toString(), Double.parseDouble(hourlyPrice.getText().toString()));
            Intent HOIntent = new Intent(AddService.this, Admin.class);
            startActivity(HOIntent);
        }
    }
}
