package com.example.eleanor.segproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
