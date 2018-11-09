package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button adminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onCreateHomeOwnerButtonClick(View view){
        Intent HOIntent = new Intent(MainActivity.this, HomeOwner.class);
        startActivity(HOIntent);
    }

    public void onCreateServiceProviderButtonClick(View view){
        Intent SPIntent = new Intent(MainActivity.this,
                ServiceProvider.class);
        startActivity(SPIntent);
    }

    public void onCreateAdminButtonClick(View view){
        Intent AdminIntent = new Intent(MainActivity.this,
                Admin.class);
        startActivity(AdminIntent);
    }
}
