package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;

public class ViewList extends AppCompatActivity {
    ListView lv;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        lv = (ListView) findViewById(R.id.serviceListView);

        ArrayList<String> stringServiceList = new ArrayList<String>();

        for(int i=0; i<LISTOFSERVICES.size(); i++){
            stringServiceList.add(LISTOFSERVICES.get(i).toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                stringServiceList );

        lv.setAdapter(arrayAdapter);
    }

    addFireBaseDatabaseListner() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Service service = dataSnapshot.getValue(Service.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                System.out.println(databaseError.getMessage());
            }
        };
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(postListener);
    }

    public void viewList(){

    }
}
