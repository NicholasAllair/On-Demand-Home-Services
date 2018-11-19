package com.example.eleanor.segproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.eleanor.segproject.ServiceList.LISTOFSERVICES;

public class ServiceProviderAddService extends AppCompatActivity {

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

  
}
