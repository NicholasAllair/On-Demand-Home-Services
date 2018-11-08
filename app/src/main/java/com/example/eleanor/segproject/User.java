package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public abstract class User extends AppCompatActivity {
    Button createProfile;
    EditText editName, editEmail, editPassword;
    String content;

    public User(){
    }

    public abstract String getName();
    public abstract void setName(EditText name);
    public abstract String getEmail();
    public abstract void setEmail(EditText email);
    public abstract String getPassword();
    public abstract void setPassword(EditText password);

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}

