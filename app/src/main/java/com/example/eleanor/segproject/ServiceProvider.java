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

public class ServiceProvider extends AppCompatActivity {
    Button createProfile;
    EditText editName, editEmail, editPassword;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);

        editName = findViewById(R.id.EnterName);
        editEmail = findViewById(R.id.EnterEmail);
        editPassword = findViewById(R.id.EnterPassword);
        createProfile = findViewById(R.id.ReturnHome);


        createProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                /*Intent validate = new Intent(HomeOwner.this,
                        ProfileValidatorFactory.class);
                startActivity(validate);*/

                    Intent returnHome = new Intent(ServiceProvider.this,
                            WelcomeScreen.class);
                    startActivity(returnHome);
                }
            });
        }
    }
