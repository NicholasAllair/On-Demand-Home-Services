package com.example.eleanor.segproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getName() {
        String name = "Daniel";
        String intendedName = "Daniel";
        getName();

        assertEquals(intendedName,name);

    }

    @Test
    public void getEmail() {
        String email= "eobasi@rocketmail.com";
        String intendedEmail= "eobasi@rocketmail.com";
        getEmail();
        assertEquals(intendedEmail,email);

    }

    @Test
    public void getPassword() {
        String password = "123443";
        String intendedPassword = "123443";
        getPassword();

        assertEquals(intendedPassword, password);

    }

}