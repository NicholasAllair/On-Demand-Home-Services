package com.example.eleanor.segproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void getServiceName() {
        String serviceName =  "laundry";
        String intendedService = "laundry";
        getServiceRate();
        assertEquals(intendedService,serviceName);

    }

    @Test
    public void getServiceRate() {
        double serviceRate = 1.2;
        double intendedRate = 1.2;
        double range = 0.1;
        getServiceRate();
        assertEquals(intendedRate, serviceRate, range);

    }
}