package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class ServiceList extends AppCompatActivity {

    public static ArrayList<Service> LISTOFSERVICES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addService(String serviceName, double payrate){
        Service aService = new Service(serviceName, payrate);
        LISTOFSERVICES.add(aService);
    }

    public void removeService(String serviceName){


    }

    public void editService(String servicename){

    }



}
