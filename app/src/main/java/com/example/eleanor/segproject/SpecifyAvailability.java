package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class SpecifyAvailability extends AppCompatActivity {
    EditText startTime;
    EditText endTime;
    ArrayList<String> availabilities;
    Spinner day;
    ListView availabilityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_availability);

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        day = findViewById(R.id.daySpinner);
        availabilityList = findViewById(R.id.availabilityList);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter);

        availabilities = new ArrayList<String>();

    }

    public void onAddAvailClick(View view){
        String dayString = day.getSelectedItem().toString();
        String startTimeString = startTime.getText().toString();
        String endTimeString = endTime.getText().toString();

        String availString = availabilityToString(dayString, startTimeString, endTimeString);

        availabilities.add(availString);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, availabilities );

        availabilityList.setAdapter(arrayAdapter);
    }

    public void returnSPHome(View view){
        Intent spHome = new Intent(SpecifyAvailability.this, ServiceProviderWelcome.class);
        startActivity(spHome);
    }


    public String availabilityToString(String day, String startTime, String endTime){
        return (day + ": " + startTime + " - " + endTime);
    }

}
