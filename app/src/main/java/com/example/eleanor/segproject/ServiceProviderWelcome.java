package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.eleanor.segproject.ServiceProvider.SPNAME;
import static com.example.eleanor.segproject.ServiceProvider.servicesOffered;

public class ServiceProviderWelcome extends AppCompatActivity {
    TextView SPusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovider_homepage);

        setUserName(SPNAME);
    }

    public void onSpecifyAvailabilityClick(View view){
        Intent specAvail = new Intent(ServiceProviderWelcome.this, SpecifyAvailability.class);
        startActivity(specAvail);
    }

    public void onServiceProviderAddService(View view){
        Intent addSPService = new Intent(ServiceProviderWelcome.this, ServiceProviderAddRemoveService.class);
        startActivity(addSPService);
    }

    public void setUserName(String name){
        SPusername = findViewById(R.id.SPusername);
        SPusername.setText(name);
    }



}
