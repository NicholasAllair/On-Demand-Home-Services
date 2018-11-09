package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button homeOwnerButton;
    Button serviceProviderButton;
    Button adminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeOwnerButton = (Button) findViewById(R.id.HomeownerProfile);
        serviceProviderButton = (Button) findViewById(R.id.ServiceProviderProfile);
        adminButton = (Button) findViewById(R.id.Admin);


        homeOwnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,
                        HomeOwner.class);
                startActivity(intent1);
            }
        });

        serviceProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,
                        ServiceProvider.class);
                startActivity(intent2);
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this,
                        Admin.class);
                startActivity(intent3);
            }
        });
    }

    public void createHomeOwnerButtonClick(View view){

    }
}
