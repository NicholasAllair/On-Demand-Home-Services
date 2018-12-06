package com.example.eleanor.segproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceProviderTest {

    @Test
    public void getDescription() {
        String description = "WIDE";
        String intendedDescription = "WIDE";
        getDescription();
        assertEquals(intendedDescription, description);
    }
}