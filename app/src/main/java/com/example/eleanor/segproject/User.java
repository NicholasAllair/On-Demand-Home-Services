package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Character.isLetter;


public abstract class User extends AppCompatActivity {
    Button createProfile;
    EditText editName, editEmail, editPassword;
    String content;
    public static String userName, password, email, address, phone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public User() {
    }

    public String getName() {
        return this.userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public final static boolean isValidPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target));
    }

    public final static boolean isValidAddress(CharSequence target) {
        return (!TextUtils.isEmpty(target));
    }

    public final static boolean isValidPhone(CharSequence target) {

        //FOLLOWING CODE IS NOT WORKING -- FIX
        /*String sTarget = target.toString();

        if (TextUtils.isEmpty(sTarget)) {
            return false;
        } else if (((Integer) sTarget.length()).equals(10)) {
            return false;
        } else {
            for (int i = 0; i < sTarget.length(); i++) {
                if (isLetter(sTarget.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
        */

        return (!TextUtils.isEmpty(target));
    }


}
