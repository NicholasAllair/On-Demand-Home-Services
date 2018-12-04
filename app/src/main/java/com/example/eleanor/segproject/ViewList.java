package com.example.eleanor.segproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewList extends AppCompatActivity {
    private ListView dataListView;
    private EditText itemText;
    private EditText rateText;
    private Button findButton;
    private Button deleteButton;
    private Boolean searchMode = false;
    private Boolean itemSelected = false;
    private int selectedPosition = 0;
    public static int numServices;

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("service");

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    static ArrayAdapter<String> adapter;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        dataListView = (ListView) findViewById(R.id.dataListView);
        itemText = (EditText) findViewById(R.id.itemText);
        rateText = (EditText) findViewById(R.id.viewListRate);
        findButton = (Button) findViewById(R.id.findButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setEnabled(false);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                listItems);

        dataListView.setAdapter(adapter);
        dataListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        dataListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedPosition = position;
                        itemSelected = true;
                        deleteButton.setEnabled(true);
                    }
                });

        addValueEventListener();
    }

    private void addValueEventListener() {
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                adapter.clear();
                listKeys.clear();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    System.out.println("Current datasnapshot: " + next);

                    String key = next.getKey();

                    String serviceType = (String) next.child("serviceType").getValue();
                    System.out.println("service type:" + serviceType);

                    String rate = (String) next.child("rate").getValue();
                    System.out.println("service rate:" + rate);

                    listKeys.add(key);
                    adapter.add(serviceToString(serviceType, rate));

                    setNumServices(listKeys.size());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbRef.addValueEventListener(valueEventListener);
    }

    public void addItem(View view) {

        String serviceName = itemText.getText().toString();
        String serviceRate = rateText.getText().toString();

        key = dbRef.push().getKey();

        System.out.println("CURRENTKEY: " + key);

        itemText.setText("");
        rateText.setText("");

        dbRef.child(key).child("serviceType").setValue(serviceName);
        dbRef.child(key).child("rate").setValue(serviceRate);

        adapter.add(serviceToString(serviceName, serviceRate));
        adapter.notifyDataSetChanged();
    }

    public void deleteItem(View view) {
        dataListView.setItemChecked(selectedPosition, false);
        dbRef.child(listKeys.get(selectedPosition)).removeValue();
    }

    public void findItems(View view) {

        Query query;

        if (!searchMode) {
            findButton.setText("Clear");
            query = dbRef.orderByChild("service").equalTo(itemText.getText().toString());
            searchMode = true;
        } else {
            searchMode = false;
            findButton.setText("Find");
            query = dbRef.orderByKey();
        }

        if (itemSelected) {
            dataListView.setItemChecked(selectedPosition, false);
            itemSelected = false;
            deleteButton.setEnabled(false);
        }

        query.addListenerForSingleValueEvent(queryValueListener);
    }

    ValueEventListener queryValueListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            //NEED ITERATOR TO READ ALL DATA FROM ALL USERS
            Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
            Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

            adapter.clear();
            listKeys.clear();

            while (iterator.hasNext()) {
                DataSnapshot next = (DataSnapshot) iterator.next();

                String match = (String) next.child("service").getValue();
                String key = next.getKey();
                listKeys.add(key);
                adapter.add(match);
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public static String serviceToString(String service, String rate){
        return service + ": $" + rate + "/hour";
    }

    public void setNumServices(int n){
        numServices = n;
    }

    public static int getNumServices(){
        return numServices;
    }


}
