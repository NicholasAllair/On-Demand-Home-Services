package com.example.eleanor.segproject;

import android.os.Bundle;
import android.widget.TextView;

public class WelcomeScreen{
    HomeOwner homeOwner;

    public WelcomeScreen(HomeOwner homeOwner){
        this.homeOwner = homeOwner;
    }

    String name = homeOwner.editName.getText().toString();

    private void onCreate(Bundle savedInstanceState) {
        homeOwner.onCreate(savedInstanceState);
        homeOwner.setContentView(R.layout.activity_welcome_screen);

        TextView t = (TextView) homeOwner.findViewById(R.id.username);
        t.setText(name);

    }

}
