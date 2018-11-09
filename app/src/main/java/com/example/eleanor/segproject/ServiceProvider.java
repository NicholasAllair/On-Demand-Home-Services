package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ServiceProvider extends User {
    EditText SPname;
    EditText SPemail;
    EditText SPpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_create);
    }

    public void onClickCreateSPProfile(View view){
        TextView invalidEmail = findViewById(R.id.InvalidEmail);
        TextView invalidName = findViewById(R.id.InvalidUsername);

        if(!isValidEmail(getEmail())){
            invalidEmail.setText(("Email invalid"));
        }
        if(!isValidUsername(getName())){
            invalidName.setText("Name invalid");
        }
        else {
            Intent SPIntent = new Intent(ServiceProvider.this, ServiceProviderWelcome.class);
            startActivity(SPIntent);
        }
    }

    public String getName(){
        SPname = findViewById(R.id.EnterSPName);
        return SPname.getText().toString();
    }
    public void setName(EditText name){
        this.SPname = name;
    }

    public String getEmail(){
        SPemail = findViewById(R.id.EnterSPEmail);
        return SPemail.getText().toString();
    }
    public void setEmail(EditText email){
        this.SPemail = email;
    }

    public String getPassword(){
        SPpassword = findViewById(R.id.EnterSPPassword);
        return SPpassword.getText().toString();
    }
    public void setPassword(EditText password){
        this.SPpassword = password;
    }
}
