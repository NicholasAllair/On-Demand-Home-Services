package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceConfigurationError;

public class ServiceList extends AppCompatActivity {

    public static ArrayList<Service> LISTOFSERVICES = new ArrayList<>(10);

    public ServiceList(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void addService(String serviceName, double payrate){
        Service aService = new Service(serviceName, payrate);
        LISTOFSERVICES.add(aService);
        
    }

    public void removeService(String serviceName){


    }

    public void editService(String servicename){

    }

    public static String getSize(){
        if(LISTOFSERVICES.isEmpty()){
            return "0";
        }
        return Integer.toString(LISTOFSERVICES.size());
    }

    public static String getLast(){
        if(LISTOFSERVICES.isEmpty()){
            return "List is Empty";
        }
        return (LISTOFSERVICES.get(LISTOFSERVICES.size()-1).getServiceName());
    }



}
