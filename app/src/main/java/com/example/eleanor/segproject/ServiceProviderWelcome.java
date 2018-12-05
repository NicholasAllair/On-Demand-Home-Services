package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ServiceProviderWelcome extends AppCompatActivity {
    TextView SPusername;
    DatabaseReference userRef;
    String name, email, uid;
    ArrayList<String> listKeys, listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovider_homepage);

        listKeys = new ArrayList<String>();
        listItems = new ArrayList<String>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            uid = user.getUid();
        } else {
            // No user is signed in
            System.out.println("no");
        }

        userRef = FirebaseDatabase.getInstance().getReference().child("serviceProviders");

        addValueEventListener();



    }

    private void addValueEventListener() {
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("dataSnapshot");
                System.out.println(dataSnapshot);
                String value = (String) dataSnapshot.child(uid).child("Company").getValue().toString();
                System.out.println("COMPANY: " + value);
                setUserName(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }

        };
        userRef.addValueEventListener(valueEventListener);

    }

    public void onSpecifyAvailabilityClick(View view){
        Intent specAvail = new Intent(ServiceProviderWelcome.this, SpecifyAvailability.class);
        startActivity(specAvail);
    }

    public void onViewAvailabilityClick(View view){
        Intent viewAvail = new Intent(ServiceProviderWelcome.this, ViewAvailability.class);
        startActivity(viewAvail);
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
