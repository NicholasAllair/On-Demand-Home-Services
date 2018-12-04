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

public class ServiceProviderViewServices extends ServiceProvider {
    private DatabaseReference mDatabase;
    ListView lv;
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_view_services);

        ArrayList<String> StringServiceList = new ArrayList<String>();

        lv = (ListView) findViewById(R.id.SPlistview);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("serviceProviders");

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                StringServiceList );

        lv.setAdapter(arrayAdapter);

        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        String uid = currentUser.getUid();

        addValueEventListener();

    }

    private void addValueEventListener() {
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                listKeys.clear();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    int count = (int) next.child("myServices").getChildrenCount();

                    String key = next.getKey();

                    for(int i=0; i< count; i++){
                        String n = Integer.toString(i);
                        String service = (String) next.child("myServices")
                                .child(n).getValue();
                        arrayAdapter.add(service);
                    }

                    listKeys.add(key);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(valueEventListener);
    }
}
