package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class ServiceProvider extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_create);
    }

    public void onClickCreateSPProfile(View view){
        Intent returnHome = new Intent(ServiceProvider.this, ServiceProviderWelcome.class);
        startActivity(returnHome);
    }
}
