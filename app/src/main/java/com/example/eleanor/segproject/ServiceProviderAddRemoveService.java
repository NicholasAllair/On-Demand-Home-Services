package com.example.eleanor.segproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;

public class ServiceProviderAddRemoveService extends ServiceProvider {

    //public ServiceList serviceList = new ServiceList();

    DatabaseReference mDatabase;
    EditText serviceName;
    ListView servicesAvailable;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> myServicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_add_service);

        servicesAvailable = (ListView) findViewById(R.id.serviceListFromAdmin);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("service");

        //ArrayList<String> StringServiceList = new ArrayList<String>();

        /*for(int i=0; i<LISTOFSERVICES.size(); i++){
            StringServiceList.add(LISTOFSERVICES.get(i).toString());
        }*/

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_single_choice,
                listItems);

        myServicesAdapter

        servicesAvailable.setAdapter(arrayAdapter);

        addChildEventListener();
    }

    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = (String) dataSnapshot.child("service").getValue();
                if (value != null) {
                    arrayAdapter.add(value);
                    listKeys.add(dataSnapshot.getKey());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    listItems.remove(index);
                    listKeys.remove(index);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addChildEventListener(childListener);
    }

    /*public void onAddServiceSPClick(View view){

        serviceName = findViewById(R.id.SPserviceToAdd);

        TextView ServiceDoesNotExist = findViewById(R.id.SPserviceDoesNotExist);

        if (!serviceList.isIn(serviceName.getText().toString())){
            ServiceDoesNotExist.setText("That Service Does Not Exist");
        }
        else {

            servicesOffered.add(serviceList.getService(serviceName.getText().toString()));

            Intent HOIntent = new Intent(ServiceProviderAddRemoveService.this, ServiceProviderWelcome.class);
            startActivity(HOIntent);
        }
    }

    public void onRemoveServiceSPClick(View view){

        serviceName = findViewById(R.id.SPserviceToAdd);

        TextView ServiceDoesNotExist = findViewById(R.id.SPserviceDoesNotExist);

        if (!serviceList.isIn(serviceName.getText().toString())){
            ServiceDoesNotExist.setText("That Service Does Not Exist");
        }
        else {

            servicesOffered.add(serviceList.getService(serviceName.getText().toString()));

            Intent HOIntent = new Intent(ServiceProviderAddRemoveService.this, ServiceProviderWelcome.class);
            startActivity(HOIntent);
        }
    }*/
}




