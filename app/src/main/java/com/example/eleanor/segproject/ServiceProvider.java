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

<<<<<<< HEAD
=======
<<<<<<< HEAD
    Button createProfile;
    EditText editName, editEmail, editPassword;
    TextView result;

=======
>>>>>>> fe0457d9fb4c673612df7e9245e2416cfb66c156
>>>>>>> 4440d0553aafe5fb95192c034b4e8a4b13de9b39
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 4440d0553aafe5fb95192c034b4e8a4b13de9b39

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
<<<<<<< HEAD
=======

=======
>>>>>>> fe0457d9fb4c673612df7e9245e2416cfb66c156

        editName = (EditText) findViewById(R.id.EnterName);
        editEmail = (EditText) findViewById(R.id.EnterEmail);
        editPassword = (EditText) findViewById(R.id.EnterPassword);
        createProfile = (Button) findViewById(R.id.ReturnHome);


        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent validate = new Intent(ServiceProvider.this,
                        ProfileValidatorFactory.class);
                startActivity(validate);*/

                Intent returnHome = new Intent(ServiceProvider.this,
                        WelcomeScreen.class);
                startActivity(returnHome);
            }
        });
    }
}
>>>>>>> 4440d0553aafe5fb95192c034b4e8a4b13de9b39
