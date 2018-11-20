package com.example.eleanor.segproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ServiceProviderViewServices extends ServiceProvider {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_view_services);

        ArrayList<String> StringServiceList = new ArrayList<String>();

        lv = (ListView) findViewById(R.id.SPlistview);

        for(int i=0; i< servicesOffered.size(); i++){
            StringServiceList.add(servicesOffered.get(i).toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                StringServiceList );

        lv.setAdapter(arrayAdapter);
    }
}
