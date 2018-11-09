package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HomeOwner extends User{
    EditText name;
    EditText email;
    EditText password;


    public HomeOwner(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_create);
    }

    public void onClickCreateHOProfile(View view){
        TextView invalidEmail = findViewById(R.id.InvalidEmail);
        TextView invalidName = findViewById(R.id.InvalidUsername);

        if(!isValidEmail(getEmail())){
            invalidEmail.setText(("Email invalid"));
        }
        if(!isValidUsername(getName())){
            invalidName.setText("Name invalid");
        }
        else {
            Intent HOIntent = new Intent(HomeOwner.this, HomeOwnerWelcome.class);
            startActivity(HOIntent);
        }
    }

    public String getName(){
        name = findViewById(R.id.EnterName);
        return name.getText().toString();
    }
    public void setName(EditText name){
        this.name = name;
    }

    public String getEmail(){
        email = findViewById(R.id.EnterEmail);
        return email.getText().toString();
    }
    public void setEmail(EditText email){
        this.email = email;
    }

    public String getPassword(){
        password = findViewById(R.id.EnterPassword);
        return password.getText().toString();
    }
    public void setPassword(EditText password){
        this.password = password;
    }

}
