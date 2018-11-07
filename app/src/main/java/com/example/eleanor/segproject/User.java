package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public abstract class User extends AppCompatActivity {
    Button createProfile;
    EditText editName, editEmail, editPassword;
    String content;

    public User(EditText editName, EditText editEmail, EditText editPassword){
        this.editName = editName;
        this.editEmail = editEmail;
        this.editPassword = editPassword;
    }

    public abstract String getName();
    public abstract void setName(EditText name);
    public abstract String getEmail();
    public abstract void setEmail(EditText email);
    public abstract String getPassword();
    public abstract void setPassword(EditText password);

}

