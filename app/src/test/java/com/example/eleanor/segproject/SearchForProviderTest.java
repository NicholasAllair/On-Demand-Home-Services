package com.example.eleanor.segproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchForProviderTest {

    @Test
    public void convertToRefName() {
        String input = "Service Type";
        String intendedInput = "Service Type";
        convertToRefName();
        assertEquals(intendedInput, input);

    }

    @Test
    public void getText() {
        String text = "HI";
        String intendedText = "HI";
        getText();
        assertEquals(intendedText, text);

    }


    @Test
    public void getSelectedCompany() {
        String selectedCompany = "MIT";
        String intendedSelectedCompany = "MIT";
        getSelectedCompany();
        assertEquals(intendedSelectedCompany,selectedCompany);

    }

    @Test
    public void getCompanyID() {
        String companyID = "12GDT";
        String intendedCompanyID = "12GDT";
        getCompanyID();
        assertEquals(intendedCompanyID,companyID);
    }


    }


