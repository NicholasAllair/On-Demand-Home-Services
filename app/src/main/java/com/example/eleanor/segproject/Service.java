package com.example.eleanor.segproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.*;

public class Service extends AppCompatActivity {
    private DatabaseReference serviceDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // serviceDB = FirebaseDatabase.getInstance().getReference();
    }

    String serviceName;//string variable to hold name of service
    double serviceRate;//double variable to hold hourly rate of the service

    //default Constructor
    public Service (){
        this.serviceName = "EmptyService";
        this.serviceRate = 0.0;
    }

    public Service (String s, double d ){//constructor for a new service given a name and pay rate

        this.serviceName = s;
        this.serviceRate = d;

    }

    public void writeNewService(String name, String rate) {

        serviceDB.child("services").child("serviceName").setValue(name);
        serviceDB.child("services").child("serviceRate").setValue(rate);
    }


    //setter methods for service name & rate, (allows admin to edit the service)
    public void setServiceName(String s){ this.serviceName = s; }

    public void setServiceRate(double d){ this.serviceRate = d; }

    //getter methods for service name & rate

    public String getServiceName(){ return this.serviceName; }

    public double getServiceRate(){ return this.serviceRate; }

    public String toString(){
        return (getServiceName() + " " + getServiceRate());
    }

}
