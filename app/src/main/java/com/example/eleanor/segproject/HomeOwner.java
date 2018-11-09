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
    public static String HONAME;

    EditText editName;
    EditText editEmail;
    EditText editPassword;

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

        HONAME = getName();
    }

    public String getName(){
        editName = findViewById(R.id.EnterName);
        return editName.getText().toString();
    }
    public void setName(EditText editName){
        this.editName = editName;
    }

    public String getEmail(){
        editEmail = findViewById(R.id.EnterEmail);
        return editEmail.getText().toString();
    }
    public void setEmail(EditText editEmail){
        this.editEmail = editEmail;
    }

    public String getPassword(){
        editPassword = findViewById(R.id.EnterPassword);
        return editPassword.getText().toString();
    }
    public void setPassword(EditText password){
        this.editPassword = password;
    }

}
