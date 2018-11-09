package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;


public class HomeOwner extends User{

    public HomeOwner(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_create);
    }

    public void onClickCreateHOProfile(){
        Intent HOIntent = new Intent(HomeOwner.this, HomeOwnerWelcome.class);
        startActivity(HOIntent);
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
