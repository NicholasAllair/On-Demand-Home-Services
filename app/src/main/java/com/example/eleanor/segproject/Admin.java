package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;

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

        TextView numServices = findViewById(R.id.numServices);
        numServices.setText(ServiceList.getSize());

        TextView mostRecentElement = findViewById(R.id.mostRecentElement);
        mostRecentElement.setText(ServiceList.getLast());

        this.setName(ADMINUSERNAME);
        this.setPassword(ADMINPASSWORD);

    }


    public void onAddServiceClick(View view){
        Intent addService = new Intent(Admin.this, AddService.class);
        startActivity(addService);
    }

    public void onEditServiceClick(View view){
        Intent editService = new Intent(Admin.this, EditService.class);
        startActivity(editService);
    }

    public void onRemoveServiceClick(View view){
        Intent removeService = new Intent(Admin.this, RemoveService.class);
        startActivity(removeService);
    }

    public void onViewServicesClick(View view){
        Intent viewServices = new Intent(Admin.this, ViewList.class);
        startActivity(viewServices);
    }

}

