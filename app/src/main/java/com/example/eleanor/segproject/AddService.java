package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;


public class AddService extends AppCompatActivity {
    EditText serviceName;
    EditText hourlyPrice;
    Service newService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

    }

    public void onNewServiceClick(View view){
        serviceName = findViewById(R.id.serviceName);
        hourlyPrice = findViewById(R.id.hourlyPrice);

<<<<<<< Updated upstream
        ServiceList.addService(serviceName.getText().toString(), Double.parseDouble(hourlyPrice.getText().toString()));
=======
        newService = new Service();
        newService.setServiceName(serviceName.getText().toString());
        newService.setServiceRate(Double.parseDouble(hourlyPrice.getText().toString()));

        ServiceList.addService(newService.getServiceName(), newService.getServiceRate());
>>>>>>> Stashed changes

        Intent HOIntent = new Intent(AddService.this, Admin.class);
        startActivity(HOIntent);
    }
}
