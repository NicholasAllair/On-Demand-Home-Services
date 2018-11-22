package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onCreateHomeOwnerButtonClick(View view){
        Intent HOIntent = new Intent(MainActivity.this, HomeOwner.class);
        startActivity(HOIntent);
    }

    public void onCreateServiceProviderButtonClick(View view) {
        Intent SPIntent = new Intent(MainActivity.this, ServiceProvider.class);
        startActivity(SPIntent);
    }

    public void onLoginAdminButtonClick(View view) {
        Intent aIntent = new Intent(MainActivity.this, LoginAdmin.class);
        startActivity(aIntent);
    }
}
