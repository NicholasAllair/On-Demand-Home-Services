package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import static com.example.eleanor.segproject.HomeOwner.HONAME;

public class HomeOwnerWelcome extends AppCompatActivity{
    TextView HOusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_homepage);

        setUserName(HONAME);
    }

    public void setUserName(String name){
        HOusername = findViewById(R.id.HOusername);
        HOusername.setText(name);
    }

    public void onSearchForSP(View view){
        Intent searchSP = new Intent(HomeOwnerWelcome.this, SearchForProvider.class);
        startActivity(searchSP);
    }

    public void viewBookings(View view){
        Intent vb = new Intent(HomeOwnerWelcome.this, ViewHOBookings.class);
        startActivity(vb);
    }
}
