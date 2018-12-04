package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


public class SpecifyAvailability extends AppCompatActivity {
    public FirebaseAuth mAuth;
    EditText startTime, endTime;
    ArrayList<String> availabilities;
    Spinner day;
    ListView availabilityList;
    String uid;
    ArrayAdapter<String> arrayAdapter;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_availability);

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        day = findViewById(R.id.daySpinner);
        availabilityList = findViewById(R.id.availabilityList);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.days,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter);

        availabilities = new ArrayList<String>();
        System.out.println("AVAILABILITIES ARRAY: " + availabilities);

        this.mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        uid = currentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("serviceProviders").child(uid);

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                availabilities );

        availabilityList.setAdapter(arrayAdapter);

        addValueEventListener();
    }

    private void addValueEventListener() {
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Read Availabilities from database for current user

                arrayAdapter.clear();

                long n = dataSnapshot.child("Availabilities").getChildrenCount();
                int numChildren = (int) n;

                for(int i=0; i<numChildren; i++){
                    String iString = Integer.toString(i);
                    arrayAdapter.add(dataSnapshot.child("Availabilities")
                            .child(iString).getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(valueEventListener);
    }



    public void onAddAvailClick(View view){
        String dayString = day.getSelectedItem().toString();
        String startTimeString = startTime.getText().toString();
        String endTimeString = endTime.getText().toString();

        String availString = availabilityToString(dayString, startTimeString, endTimeString);

        availabilities.add(availString);

        mDatabase.child("Availabilities").setValue(availabilities);


    }

    public void returnSPHome(View view){
        Intent spHome = new Intent(SpecifyAvailability.this, ServiceProviderWelcome.class);
        startActivity(spHome);
    }


    public String availabilityToString(String day, String startTime, String endTime){
        return (day + ": " + startTime + " - " + endTime);
    }

}
