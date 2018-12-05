package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewHOBookings extends AppCompatActivity {
    DatabaseReference bookingsRef, spRef;
    TextView companyText, serviceText;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    String uid, bookingID;
    ListView bookingsList;
    ArrayAdapter<String> bookingsAdapter;
    ArrayList<String> bookingsArray = new ArrayList<String>();
    ArrayList<String> bookingsKeys = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ho_bookings);
        this.mAuth = FirebaseAuth.getInstance();

        currentUser = this.mAuth.getCurrentUser();
        uid = currentUser.getUid();
        System.out.println("UID: " + uid);

        //DatabaseReference to read all bookings for current user
        bookingsRef = FirebaseDatabase.getInstance().getReference().child("homeOwners").child(uid).child("Bookings");

        bookingsList = findViewById(R.id.bookingsList);
        bookingsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                bookingsArray);

        bookingsList.setAdapter(bookingsAdapter);
        bookingsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        readBookings();
    }

    public void readBookings(){
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Iterate through service providers in DB
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                bookingsAdapter.clear();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();

                    String c = next.child("Company").getValue().toString();
                    System.out.println("COMPANY: " + c);
                    String s = next.child("RequestedService").getValue().toString();
                    System.out.println("SERVICE: " + s);
                    String t = next.child("Timeslot").getValue().toString();
                    System.out.println("TIME: " + t);

                    bookingsAdapter.add(bookingToString(c, s, t));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        bookingsRef.addValueEventListener(valueEventListener);
    }

    public void rateBooking(){

    }

    public String bookingToString(String company, String service, String time){
        System.out.println("STRING: " + company + " - " + service + " - " + time);
        return company + " - " + service + " - " + time;
    }

}