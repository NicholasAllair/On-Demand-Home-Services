package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SpecifyAvailability extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_availability);

        CalendarView calendar = findViewById(R.id.specAvailCalendar);
        TextView showDate = findViewById(R.id.showDate);
        long selectedDate = calendar.getDate();

        showDate.setText(formatDate(selectedDate, "dd/MM/yyyy"));

    }

    public static String formatDate(long milliSeconds, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
