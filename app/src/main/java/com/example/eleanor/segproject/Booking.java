package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.Iterator;

public class Booking extends AppCompatActivity {
    DatabaseReference bookingDB, spRef;
    TextView companyText, serviceText;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    String company, homeowner, service, uid, bookingID;
    ListView timesList;
    ArrayAdapter<String> timesAdapter;
    ArrayList<String> timesArray = new ArrayList<String>();
    Boolean itemSelected = false;
    int selectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        this.mAuth = FirebaseAuth.getInstance();

        //DatabaseReference to read all bookings
        bookingDB = FirebaseDatabase.getInstance().getReference().child("Bookings");
        //DatabaseReference to read all service providers
        spRef = FirebaseDatabase.getInstance().getReference().child("serviceProviders");

        companyText = findViewById(R.id.companyText);
        serviceText = findViewById(R.id.serviceText);
        timesList = findViewById(R.id.timesList);

        currentUser = this.mAuth.getCurrentUser();
        uid = currentUser.getUid();
        System.out.println("UID: " + uid);

        findCurrentBooking();

        //adapter for timesList
        timesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                timesArray);

        timesList.setAdapter(timesAdapter);
        timesList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        timesList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedPosition = position;
                        itemSelected = true;
                    }
                });


    }

    public void findCurrentBooking(){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {

                    DataSnapshot next = (DataSnapshot) iterator.next();
                    String h = next.child("Homeowner").getValue().toString();
                    System.out.println("H: " + h);
                    if(h.equals(uid)){
                        bookingID = next.getKey();
                        System.out.println("BID: " + bookingID);

                        String company = next.child("Company").getValue().toString();
                        companyText.setText(company);
                        serviceText.setText(next.child("RequestedService").getValue().toString());

                        findCompanyID(company);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        bookingDB.addValueEventListener(valueEventListener);
    }

    public void findCompanyID(final String company){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {

                    DataSnapshot next = (DataSnapshot) iterator.next();
                    String c = next.child("Company").getValue().toString();
                    System.out.println("C: " + c);
                    if(c.equals(company)){
                        String companyID = next.getKey();
                        System.out.println("CID: " + companyID);
                        displayTimes(companyID);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        spRef.addValueEventListener(valueEventListener);
    }

    public void displayTimes(final String companyID){
        ValueEventListener valueEventListener_times = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot = dataSnapshot.child(companyID).child("Availabilities");

                for(int i=0; i<dataSnapshot.getChildrenCount(); i++){
                    String iString = Integer.toString(i);
                    String thisTime = dataSnapshot.child(iString).getValue().toString();
                    System.out.println("THIS TIME:" + thisTime);
                    timesAdapter.add(thisTime);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        spRef.addValueEventListener(valueEventListener_times);
    }

    public void onFinishBooking(View view){
        String selectedTime = timesArray.get(selectedPosition);
        bookingDB.child(bookingID).child("SelectedTime").setValue(selectedTime);

        Intent home = new Intent(Booking.this, HomeOwnerWelcome.class);
        startActivity(home);
    }

    private void setCompany(String company){
        this.company = company;
    }

    private void setHomeowner(String homeowner){
        this.homeowner = homeowner;
    }

    private void setService(String service){
        this.service = service;
    }

    private String getCompany(){
        return company;
    }

    private String getHomeowner(){
        return homeowner;
    }

    private String getService(){
        return service;
    }
}
