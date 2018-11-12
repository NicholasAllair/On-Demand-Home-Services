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
    public static String userName, password, email;

    public User(){
    }

    public String getName(){
        return this.userName;
    }
    public void setName(String name){
        this.userName = name;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public final static boolean isValidEmail(String target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return (target.contains("@"));
            //return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public final static boolean isValidUsername(CharSequence target) {
        return (!TextUtils.isEmpty(target));
    }
}

