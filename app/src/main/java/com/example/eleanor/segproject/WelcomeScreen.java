package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity{

    int userType;

    public WelcomeScreen(int userType){
        //determine what kind of user is seeing this welcome screen: 0 = admin, 1 = homeowner, 2 = service provider
        this.userType = userType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        TextView username = (TextView) findViewById(R.id.username);
        username.setText("username");
    }


}
