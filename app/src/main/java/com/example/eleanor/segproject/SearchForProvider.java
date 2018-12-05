package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchForProvider extends AppCompatActivity {
    Spinner filter;
    DatabaseReference spRef;
    ListView resultsList;
    ArrayAdapter<String> resultsAdapter;
    ArrayList<String> resultsArray = new ArrayList<String>();
    ArrayList<String> allSPsArray = new ArrayList<String>();
    EditText filterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_provider);

        //Assign UML references
        filter = findViewById(R.id.searchBy);
        filterText = findViewById(R.id.filterText);

        //Set up spinner adapter -- options are Service Type, Availability, Rating
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.filterBy,
                android.R.layout.simple_spinner_item);

        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(filterAdapter);

        //set up listView and adapter for results list
        resultsList = findViewById(R.id.resultsList);
        resultsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                resultsArray);
        resultsList.setAdapter(resultsAdapter);

        //define DatabaseReference to read all service providers
        spRef = FirebaseDatabase.getInstance().getReference().child("serviceProviders");

    }

    private void addValueEventListener(final String filterString, final String filterText) {
        //READ ALL SPs FROM DB filtered by selection --> NEED ITERATOR
        //filterString: options are Service Type, Availability, Rating
        //filterText: based on user input
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                resultsAdapter.clear();

                while (iterator.hasNext()) {
                    //Read UIDs of all SPs into array allSPsArray
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    String s = convertToRefName(filterString);
                    String nextResult = next.child(s).getValue().toString();
                    System.out.println("RESULT: " + nextResult);

                    //compare result to desired text
                    int lengthOfText = filterText.length();
                    

                    resultsAdapter.add(nextResult);

                    allSPsArray.add(next.getKey().toString());
                    System.out.println("ARRAY: " + allSPsArray);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        spRef.addValueEventListener(valueEventListener);
    }

    public void onSearch(View view){
        //Get currently selected item from spinner
        String filterString = filter.getSelectedItem().toString();

        //Get text to filter by
        String text = filterText.getText().toString();

        //Fill resultsArray with ServiceProviders from SPs array that match the filterString and text

        addValueEventListener(filterString, text);


        for(int i=0; i<allSPsArray.size(); i++){
            String spKey = allSPsArray.get(i);
            System.out.println("SP: " + spKey);
            String sp = spRef.child(spKey).toString();
            String filteredValue = spRef.child(spKey).child(filterString).toString();
            System.out.println("FILTERED VALUE: " + filteredValue);

        }
    }

    public String convertToRefName(String input){
        if(input.equals("Service Type")){
            return "myServices";
        }
        else if(input.equals("Availability")){
            return "Availabilities";
        }
        else if(input.equals("Rating")){
            return "Rating";
        }

        return "String not compatible";
    }

}
