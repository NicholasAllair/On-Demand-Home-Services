package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ServiceProvider extends User {
    public static String SPNAME;
    public static ArrayList<Service> servicesOffered;
    public FirebaseAuth mAuth;

    TextView invalidEmail, invalidName, invalidAddress, invalidPhone;
    EditText editName, editAddress, editPhone, editEmail, editDescription, editPassword;
    String description;
    CheckBox checkLicensed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_create);
        this.mAuth = FirebaseAuth.getInstance();
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

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

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
            this.mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                System.out.println(user);
                                // new user created successfully
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference dbRef = database.getReference().child("serviceProviders");

                                dbRef.child(user.getUid());
                                dbRef.child(user.getUid()).child("Company").setValue(getName());
                                dbRef.child(user.getUid()).child("Address").setValue(getAddress());
                                dbRef.child(user.getUid()).child("PhoneNumber").setValue(getPhone());
                                dbRef.child(user.getUid()).child("Email").setValue(getEmail());
                                dbRef.child(user.getUid()).child("Description").setValue(getDescription());
                                dbRef.child(user.getUid()).child("Licensed").setValue(isLicensed());
                                dbRef.child(user.getUid()).child("Password").setValue(getPassword());

                                Intent SPIntent = new Intent(ServiceProvider.this, ServiceProviderWelcome.class);
                                startActivity(SPIntent);

                            } else {
                                System.out.println("Failed to create user");
                                System.out.println(task.getException());
                            }
                        }
                    });
        }

        SPNAME = getName();
    }

    public void existingUser(View view){
        Intent login = new Intent(ServiceProvider.this, ServiceProviderLogin.class);
        startActivity(login);
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
