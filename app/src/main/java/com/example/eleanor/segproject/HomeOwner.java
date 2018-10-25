package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fieldvalidators.ProfileValidator;
import fieldvalidators.ProfileValidatorFactory;

public class HomeOwner extends AppCompatActivity {
    Button createProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);

        createProfile = (Button) findViewById(R.id.ReturnHome);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validate = new Intent(HomeOwner.this,
                        ProfileValidator.class);
                startActivity(validate);

                /*Intent returnHome = new Intent(HomeOwner.this,
                        MainActivity.class);
                startActivity(returnHome); */
            }
        });
    }
}
