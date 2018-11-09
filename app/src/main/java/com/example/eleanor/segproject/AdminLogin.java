package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class AdminLogin extends AppCompatActivity {
    public static String adminUserName = "Admin";
    public static String adminPassword = "group5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_login);

    }

    public void onClickLoginToAdmin(View view) {
        TextView invalidName = findViewById(R.id.InvalidUsername);
        TextView invalidPassword = findViewById(R.id.InvalidPassword);


        EditText AdminNameEntered = findViewById(R.id.EnterAdminUsername);
        EditText AdminPasswordEntered = findViewById(R.id.EnterAdminPassword);

        invalidName.setText((" "));
        invalidPassword.setText((" "));

        if (!(AdminNameEntered.getText().toString().equals(adminUserName))) {
            invalidName.setText(("Email invalid"));
        }
        if (!(AdminPasswordEntered.getText().toString().equals(adminPassword))) {
            invalidPassword.setText(("Password invalid"));
        }else {
            Intent AdminIntent = new Intent(AdminLogin.this, Admin.class);
            startActivity(AdminIntent);
        }


    }

}


