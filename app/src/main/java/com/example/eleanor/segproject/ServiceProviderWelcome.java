package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.eleanor.segproject.ServiceProvider.SPNAME;

public class ServiceProviderWelcome extends AppCompatActivity {
    TextView SPusername;

    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovider_homepage);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            System.out.println("EMAIL: " + user.getEmail());
            name = user.getDisplayName();
            email = user.getEmail();
        } else {
            // No user is signed in
            System.out.println("no");
        }

        setUserName(name);
    }

    public void onSpecifyAvailabilityClick(View view){
        Intent specAvail = new Intent(ServiceProviderWelcome.this, SpecifyAvailability.class);
        startActivity(specAvail);
    }

    public void onServiceProviderAddService(View view){
        Intent addSPService = new Intent(ServiceProviderWelcome.this, ServiceProviderAddRemoveService.class);
        startActivity(addSPService);
    }

    public void onServiceProviderViewServices(View view){
        Intent viewServiceSP = new Intent( ServiceProviderWelcome.this, ServiceProviderViewServices.class );
        startActivity(viewServiceSP);
    }

    public void setUserName(String name){
        SPusername = findViewById(R.id.SPusername);
        SPusername.setText(name);
    }





}
