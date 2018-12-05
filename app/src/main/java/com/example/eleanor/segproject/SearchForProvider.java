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
    DatabaseReference db,spRef,hodb;
    ListView resultsList, timesList;
    ArrayAdapter<String> resultsAdapter;
    ArrayList<String> resultsArray = new ArrayList<String>();
    ArrayList<String> allSPsArray = new ArrayList<String>();
    ArrayList<String> bookingsArray = new ArrayList<String>();
    ArrayList<String> bookingKeys = new ArrayList<String>();
    EditText filterText;
    Boolean itemSelected = false;
    int selectedResult = 0, selectedTime = 0;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String filterString, text, uid, companyID, time, selectedCompany;
    ArrayAdapter<String> timesAdapter;
    ArrayList<String> timesArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_provider);
        this.mAuth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance().getReference();

        //get current user info
        currentUser = this.mAuth.getCurrentUser();
        uid = currentUser.getUid();

        //find current user in database
        hodb = db.child("homeOwners").child(uid);

        //Assign UML references
        filter = findViewById(R.id.searchBy);
        filterText = findViewById(R.id.filterText);
        timesList = findViewById(R.id.timesList);


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
                        selectedResult = position;
                        itemSelected = true;
                        setSelectedCompany(resultsArray.get(position));
                        findCompanyID();
                        selectTime();
                    }
                });

        //adapter for timesList
        timesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                timesArray);

        timesList.setAdapter(timesAdapter);
        timesList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        timesList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedTime = position;
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

                    if(filterString.equals("Service Type")){
                        filterText.setHint("Service");
                        if(searchArray(nextArray, t)){
                            resultsAdapter.add(next.child("Company").getValue().toString());
                        }
                    }

                    if(filterString.equals("Availability")){
                        filterText.setHint("Day");
                        if(searchArray(next.child("Availabilities"), text)){
                            resultsAdapter.add(next.child("Company").getValue().toString());
                        }
                    }

                    allSPsArray.add(next.getKey());
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
        addValueEventListener(filterString, filterText);

        for(int i=0; i<allSPsArray.size(); i++){
            String spKey = allSPsArray.get(i);
            String sp = spRef.child(spKey).toString();
            String filteredValue = spRef.child(spKey).child(filterString).toString();
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

        //find length of searchString
        int sLength = searchString.length();

        //add items that match searchString to resultsAdapter
        for(int i=0; i<dataSnapshot.getChildrenCount(); i++){
            String iString = Integer.toString(i);
            String service = dataSnapshot.child(iString).getValue().toString();
            service = extractSubstring(service, sLength);

            if(service.equals(searchString)){
                return true;
            }
        }
        System.out.println("Result not found");
        return false;
    }

    public String extractSubstring(String fromDB, int length){
        return fromDB.substring(0, length);
    }

    public void onCreateBooking(View view){

        System.out.println("TEXT: " + getText());

        System.out.println("SELECTED POSITION: " + getSelectedCompany());

        String key = db.push().getKey();
        bookingKeys.add(key);

        time = timesArray.get(selectedTime);

        hodb.child("Bookings").child(key).child("Company").setValue(selectedCompany);
        hodb.child("Bookings").child(key).child("RequestedService").setValue(filterTextToString(filterText));
        hodb.child("Bookings").child(key).child("Timeslot").setValue(time);

        Intent book = new Intent(SearchForProvider.this, ViewHOBookings.class);
        startActivity(book);

    }

    public void selectTime(){
        //read db of service providers to show available times for selected SP
        ValueEventListener valueEventListener_times = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("SELECTED COMPANY: " + getSelectedCompany());
                System.out.println("COMPANY ID: " + getCompanyID());

                dataSnapshot = dataSnapshot.child(getCompanyID()).child("Availabilities");

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

    public void findCompanyID(){
        ValueEventListener valueEventListener_CID = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println("Finding Company ID");

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {

                    DataSnapshot next = (DataSnapshot) iterator.next();
                    System.out.println("DB HERE: " + next);

                    String c = next.child("Company").getValue().toString();
                    System.out.println("C: " + c);
                    if(c.equals(getSelectedCompany())){
                        setCompanyID(next.getKey());
                        System.out.println("CID: " + getCompanyID());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        spRef.addValueEventListener(valueEventListener_CID);
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public String filterTextToString(EditText filterText){
        return filterText.getText().toString();
    }

    public void setSelectedCompany(String selectedCompany){
        System.out.println("Setting company to : " + selectedCompany);
        this.selectedCompany = selectedCompany;
    }

    public String getSelectedCompany(){
        return this.selectedCompany;
    }

    public void setCompanyID(String companyID){
        this.companyID = companyID;
    }

    public String getCompanyID(){
        return this.companyID;
    }

}
