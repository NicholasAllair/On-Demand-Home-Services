package com.example.eleanor.segproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class ViewListTest {

    @Test
    public void getNumServices() {
        int numServices = 2;
        int intendedNumServices = 2;
        getNumServices();
        assertEquals(intendedNumServices, numServices);
    }

}