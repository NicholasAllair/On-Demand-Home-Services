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

public class Admin extends AppCompatActivity {
    Button createNewService;
    EditText newService, ratePerHour;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);

        newService = (EditText) findViewById(R.id.EnterName);
        ratePerHour = (EditText) findViewById(R.id.EnterEmail);

        createNewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent validate = new Intent(Admin.this,
                        ProfileValidatorFactory.class);
                startActivity(validate);*/

                Intent returnHome = new Intent(Admin.this,
                        AdminWelcomeScreen.class);
                startActivity(returnHome);
            }
        });
    }
}
