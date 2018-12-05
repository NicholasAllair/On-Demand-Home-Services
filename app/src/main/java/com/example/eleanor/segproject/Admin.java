package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Admin extends User {
    public static String ADMINUSERNAME = "Admin";
    public static String ADMINPASSWORD = "Group5";

    public Admin(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        this.setName(ADMINUSERNAME);
        this.setPassword(ADMINPASSWORD);

    }

    public void onViewServicesClick(View view){
        Intent viewServices = new Intent(Admin.this, ViewList.class);
        startActivity(viewServices);
    }

    public void onHomeClick(View view){
        Intent home = new Intent(Admin.this, MainActivity.class);
        startActivity(home);
    }



}

