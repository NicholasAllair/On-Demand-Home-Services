package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.eleanor.segproject.ServiceProvider.SPNAME;

public class ServiceProviderWelcome extends AppCompatActivity {
    TextView SPusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovider_homepage);

        setUserName(SPNAME);
    }



    public void setUserName(String name){
        SPusername = findViewById(R.id.SPusername);
        SPusername.setText(name);
    }
}
