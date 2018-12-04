package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class HomeOwner extends User{
    public static String HONAME;

    public FirebaseAuth mAuth;

    TextView invalidEmail, invalidName;
    EditText editName, editEmail, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_create);
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void onClickCreateHOProfile(View view){
        invalidEmail = findViewById(R.id.InvalidEmail);
        invalidName = findViewById(R.id.InvalidUsername);

        editName = findViewById(R.id.EnterHOName);
        editEmail = findViewById(R.id.EnterHOEmail);
        editPassword = findViewById(R.id.EnterHOPassword);

        setName(editName.getText().toString());
        setEmail(editEmail.getText().toString());
        setPassword(editPassword.getText().toString());

        if(!isValidEmail(getEmail()) || !isValidUsername(getName()) ) {
            if (!isValidEmail(this.getEmail())) {
                invalidEmail.setText(("Email invalid"));
            }
            if (!isValidUsername(this.getName())) {
                invalidName.setText("Name invalid");
            }
        }
        else {
            Intent HOIntent = new Intent(HomeOwner.this, HomeOwnerWelcome.class);
            startActivity(HOIntent);
        }


        HONAME = getName();
    }


}
