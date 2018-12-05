package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import static com.example.eleanor.segproject.HomeOwner.HONAME;


public class HomeOwnerWelcome extends AppCompatActivity{
    TextView HOusername;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String uid;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_homepage);

        this.mAuth = FirebaseAuth.getInstance();
        currentUser = this.mAuth.getCurrentUser();
        uid = currentUser.getUid();

        db = FirebaseDatabase.getInstance().getReference().child("homeOwners").child(uid);

        setUserName();

    }

    public void setUserName(){
        HOusername = findViewById(R.id.HOusername);

        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue().toString();
                HOusername.setText(name);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        db.addValueEventListener(valueEventListener);
    }

    public void onSearchForSP(View view){
        Intent searchSP = new Intent(HomeOwnerWelcome.this, SearchForProvider.class);
        startActivity(searchSP);
    }

    public void viewBookings(View view){
        Intent vb = new Intent(HomeOwnerWelcome.this, ViewHOBookings.class);
        startActivity(vb);
    }
}
