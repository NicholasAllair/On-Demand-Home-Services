package com.example.eleanor.segproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;
import static com.example.eleanor.segproject.ServiceProvider.servicesOffered;

public class ServiceProviderAddService extends AppCompatActivity {

    public ServiceList serviceList = new ServiceList();

    EditText serviceName;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_add_service);

        lv = (ListView) findViewById(R.id.serviceListFromAdmin);

        ArrayList<String> StringServiceList = new ArrayList<String>();

        for(int i=0; i<LISTOFSERVICES.size(); i++){
            StringServiceList.add(LISTOFSERVICES.get(i).toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                StringServiceList );

        lv.setAdapter(arrayAdapter);
    }

    public void onEditServiceClick(View view){

        serviceName = findViewById(R.id.ServiceToAdd);

        TextView ServiceDoesNotExist = findViewById(R.id.ServiceDoesNotExist);

        if (!serviceList.isIn(serviceName.getText().toString())){
            ServiceDoesNotExist.setText("That Service Does Not Exist");
        }
        else {


            Intent HOIntent = new Intent(ServiceProviderAddService.this, ServiceProviderWelcome.class);
            startActivity(HOIntent);
        }
    }
}




