package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ServiceProvider extends User{
    public static String SPNAME;
    public static ArrayList<Service> servicesOffered;

    TextView invalidEmail, invalidName, invalidAddress, invalidPhone;
    EditText editName, editAddress, editPhone, editEmail, editDescription, editPassword;
    String description;
    CheckBox checkLicensed;

    public ServiceProvider(){
        //inherits userName, password, email
        super();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_create);
    }

    public void onClickCreateSPProfile(View view){
        invalidEmail = findViewById(R.id.invalidSPEmail);
        invalidName = findViewById(R.id.invalidSPUsername);
        invalidAddress = findViewById(R.id.invalidSPAddress);
        invalidPhone = findViewById(R.id.invalidSPPhone);

        editName = findViewById(R.id.enterSPName);
        editAddress = findViewById(R.id.enterSPAddress);
        editPhone = findViewById(R.id.enterSPPhone);
        editEmail = findViewById(R.id.enterSPEmail);
        editDescription = findViewById(R.id.enterSPDescription);
        editPassword = findViewById(R.id.enterSPPassword);

        setName(editName.getText().toString());
        setAddress(editAddress.getText().toString());
        setPhone(editPhone.getText().toString());
        setEmail(editEmail.getText().toString());
        setPassword(editPassword.getText().toString());
        setDescription(editDescription.getText().toString());

        if(!isValidEmail(getEmail()) || !isValidUsername(getName())
                || !isValidAddress(getAddress()) || !isValidPhone(getPhone()) ) {
            if (!isValidEmail(this.getEmail())) {
                invalidEmail.setText(("Email invalid"));
            }
            if (!isValidUsername(this.getName())) {
                invalidName.setText("Name invalid");
            }
            if (!isValidAddress(this.getAddress())) {
                invalidAddress.setText("Address invalid");
            }
            if (!isValidPhone(this.getPhone())) {
                invalidPhone.setText("Phone number invalid");
            }
        }
        else {
            Intent SPIntent = new Intent(ServiceProvider.this, ServiceProviderWelcome.class);
            startActivity(SPIntent);
        }

        SPNAME = getName();
    }

    public boolean isLicensed(){
        checkLicensed = findViewById(R.id.licensedCheckBox);

        if(checkLicensed.isChecked()){
            return true;
        }
        return false;
    }

    public void addService (Service e){
        servicesOffered.add(e);
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
