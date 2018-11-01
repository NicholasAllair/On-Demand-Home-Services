package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fieldvalidators.ProfileValidator;
import fieldvalidators.ProfileValidatorFactory;

public class HomeOwner extends AppCompatActivity {
    Button createProfile;
    EditText editServiceProvider, editEmailService, editPasswordService;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);

        editServiceProvider = (EditText) findViewById(R.id.EnterName);
        editEmailService = (EditText) findViewById(R.id.EnterEmail);
        editPasswordService = (EditText) findViewById(R.id.EnterPassword);
        createProfile = (Button) findViewById(R.id.ReturnHome);


        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent validate = new Intent(HomeOwner.this,
                        ProfileValidatorFactory.class);
                startActivity(validate);*/

                Intent returnHome = new Intent(HomeOwner.this,
                        WelcomeScreen.class);
                startActivity(returnHome);
            }
        });
    }
}
