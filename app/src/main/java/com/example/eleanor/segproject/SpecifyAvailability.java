package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class SpecifyAvailability extends AppCompatActivity {
    Button addAvailability = findViewById(R.id.addAvail);
    EditText startTime = findViewById(R.id.startTime);
    EditText endTime = findViewById(R.id.endTime);
    ArrayList<String> availabilities;
    Spinner day = findViewById(R.id.daySpinner);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_availability);

        Spinner daySpinner = findViewById(R.id.daySpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);

    }

    public void onAddAvailClick(){
        availabilities.add(availabilityToString(this.day))
    }

    public static String formatDate(long milliSeconds, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public String availabilityToString(String day, String startTime, String endTime){
        return startTime + ": " + startTime + " - " + endTime;
    }

}
