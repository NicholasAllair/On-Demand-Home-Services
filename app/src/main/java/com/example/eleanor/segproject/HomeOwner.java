package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class HomeOwner extends User{

    public HomeOwner(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_homepage);

        editName = findViewById(R.id.EnterName);
        setName(editName);

        editEmail = findViewById(R.id.EnterEmail);
        setEmail(editEmail);


        editPassword = findViewById(R.id.EnterPassword);

        setPassword(editPassword);

        createProfile = findViewById(R.id.ReturnHome);

        final String invalidEmailMessage = "Email address invalid";
        final String invalidUserNameMessage = "Username invalid";

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnHome = new Intent(HomeOwner.this, HomeOwnerWelcome.class);

                do {
                    TextView invalidEmail = findViewById(R.id.InvalidEmail);
                    invalidEmail.setText(invalidEmailMessage);
                }while(!isValidEmail(editEmail.getText().toString()));

                do {
                    TextView invalidUser = findViewById(R.id.InvalidUsername);
                    invalidUser.setText(invalidUserNameMessage);
                }while(!isValidUsername(editName.getText().toString()));


               startActivity(returnHome);
            }
        });

    }
    public String getName(){ return editName.getText().toString(); }
    public void setName(EditText name){
        this.editName = name;
    }

    public String getEmail(){ return editEmail.getText().toString(); }
    public void setEmail(EditText email){
        this.editEmail = email;
    }

    public String getPassword(){ return editPassword.getText().toString(); }
    public void setPassword(EditText password){
        this.editPassword = password;
    }

}
