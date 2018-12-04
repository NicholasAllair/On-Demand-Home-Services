package com.example.eleanor.segproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewAvailability extends ServiceProvider {
    private DatabaseReference mDatabase;
    ListView lv;
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayList<String> AvailabilityList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_availabilities);

        lv = (ListView) findViewById(R.id.viewAvailabilities);

        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        String uid = currentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("serviceProviders").child(uid);

        System.out.println("*******Current:" + mDatabase);

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                AvailabilityList);

        lv.setAdapter(arrayAdapter);


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
}
