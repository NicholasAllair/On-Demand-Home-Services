package com.example.eleanor.segproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeOwnerWelcome extends AppCompatActivity{

   HomeOwner homeOwner;

   public HomeOwnerWelcome(HomeOwner homeOwner){
       this.homeOwner = homeOwner;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_welcome);
        TextView username = (TextView) findViewById(R.id.username);
        username.setText(homeOwner.getName());
    }


}
