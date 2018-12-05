package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchForProvider extends AppCompatActivity {
    Spinner filter;
    DatabaseReference db;
    DatabaseReference spRef;
    ListView resultsList;
    ArrayAdapter<String> resultsAdapter;
    ArrayList<String> resultsArray = new ArrayList<String>();
    ArrayList<String> allSPsArray = new ArrayList<String>();
    EditText filterText;
    Boolean itemSelected = false;
    int selectedPosition = 0;
    public FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String uid;
    ArrayList<String> bookingsArray = new ArrayList<String>();
    ArrayList<String> bookingKeys = new ArrayList<String>();
    String filterString, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_provider);
        this.mAuth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance().getReference();

        //get current user info
        currentUser = this.mAuth.getCurrentUser();
        uid = currentUser.getUid();

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
                android.R.layout.simple_list_item_single_choice,
                resultsArray);

        resultsList.setAdapter(resultsAdapter);
        resultsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        resultsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedPosition = position;
                        itemSelected = true;
                    }
                });

        //define DatabaseReference to read all service providers
        spRef = FirebaseDatabase.getInstance().getReference().child("serviceProviders");

        //Get currently selected item from spinner
        filterString = filter.getSelectedItem().toString();

        //Get text to filter by
        text = filterText.getText().toString();
        setText(text);
    }

    private void addValueEventListener(final String filterString, final EditText filterText) {
        //READ ALL SPs FROM DB filtered by selection --> NEED ITERATOR
        //filterString: options are Service Type, Availability, Rating
        //filterText: based on user input
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Define which array in db is being probed -- availabilities, myServices, ratings
                String s = convertToRefName(filterString);
                String t = filterTextToString(filterText);

                //Iterate through service providers in DB
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                resultsAdapter.clear();
                resultsArray.clear();


                while (iterator.hasNext()) {
                    //Read UIDs of all SPs into array allSPsArray
                    DataSnapshot next = (DataSnapshot) iterator.next();

                    //find relevant array child -- myServices Availabilities, etc.
                    DataSnapshot nextArray = next.child(s);
                    System.out.println("NEXT ARRAY: " + nextArray);

                    if(filterString.equals("Service Type")){
                        filterText.setHint("Service");
                        if(searchArray(nextArray, t)){
                            resultsAdapter.add(next.child("Company").getValue().toString());
                            System.out.println("COMPANY TRUE: " + next.child("Company").getValue().toString());
                        }
                    }

                    if(filterString.equals("Availability")){
                        filterText.setHint("Day");
                        if(searchArray(next.child("Availabilities"), text)){
                            resultsAdapter.add(next.child("Company").getValue().toString());
                        }
                    }

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

        //Fill resultsArray with ServiceProviders from SPs array that match the filterString and text

        resultsArray.clear();
        System.out.println("Results array: " +  resultsArray);
        addValueEventListener(filterString, filterText);

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

    public boolean searchArray(DataSnapshot dataSnapshot, String searchString){
        //dataSnapshot is the reference to the array myServices in the SP
        System.out.println("SNAP: " + dataSnapshot);

        //find length of searchString
        int sLength = searchString.length();
        System.out.println("sLENGTH" + sLength);

        //add items that match searchString to resultsAdapter
        for(int i=0; i<dataSnapshot.getChildrenCount(); i++){
            String iString = Integer.toString(i);
            System.out.println("iSTRING: " + iString);
            String service = dataSnapshot.child(iString).getValue().toString();
            System.out.println("CURRENT SERVICE:" + service);


            service = extractSubstring(service, sLength);
            System.out.println("CURRENT SERVICE SUBSTRING:" + service);

            if(service.equals(searchString)){
                return true;
            }
        }
        System.out.println("Result not found");
        return false;
    }


    public String extractSubstring(String fromDB, int length){
        System.out.println("From Db: " + fromDB);
        System.out.println("length: " + length);
        System.out.println("substring: " + fromDB.substring(0, length));
        return fromDB.substring(0, length);
    }

    public void onCreateBooking(View view){

        System.out.println("TEXT: " + getText());

        String selectedCompany = resultsArray.get(selectedPosition);
        System.out.println("SELECTED POSITION: " + selectedCompany);

        String key = db.push().getKey();
        bookingKeys.add(key);

        db.child("Bookings").child(key).child("Company").setValue(selectedCompany);
        db.child("Bookings").child(key).child("Homeowner").setValue(uid);
        db.child("Bookings").child(key).child("RequestedService").setValue(filterTextToString(filterText));

        Intent book = new Intent(SearchForProvider.this, Booking.class);
        startActivity(book);

    }

    public String bookingToString(String Homeowner, String Company){
        System.out.println("H: " + Homeowner + " C: " + Company);
        return("H: " + Homeowner + " C: " + Company);

    }

    public void setText(String text){
        System.out.println("SETTING TEXT:" + text);
        this.text = text;
    }

    public String getText(){
        System.out.println("GETTING TEXT:" + text);
        return text;
    }

    public String filterTextToString(EditText filterText){
        return filterText.getText().toString();
    }
}
