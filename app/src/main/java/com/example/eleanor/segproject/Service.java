package com.example.eleanor.segproject;

public class Service {

    String serviceName;//string variable to hold name of service
    double serviceRate;//double variable to hold hourly rate of the service

    public Service (String s, double d ){//constructor for a new service given a name and pay rate

        serviceName = s;
        serviceRate = d;

    }


    //setter methods for service name & rate, (allows admin to edit the service)
    public void setServiceName(String s){ serviceName = s; }

    public void setServiceRate(double d){ serviceRate = d; }

    //getter methods for service name & rate

    public String getServiceName(){ return serviceName; }

    public double getServiceRate(){ return serviceRate; }

}
