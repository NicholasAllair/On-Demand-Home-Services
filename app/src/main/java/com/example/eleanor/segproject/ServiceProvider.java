package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ServiceProvider extends User {
    public static String SPNAME;

    EditText editName;
    EditText editEmail;
    EditText editPassword;

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
        TextView invalidEmail = findViewById(R.id.InvalidSPEmail);
        TextView invalidName = findViewById(R.id.InvalidSPUsername);

        editName = findViewById(R.id.EnterSPName);
        editEmail = findViewById(R.id.EnterSPEmail);
        editPassword = findViewById(R.id.EnterSPPassword);

        setName(editName.getText().toString());
        setEmail(editEmail.getText().toString());
        setPassword(editPassword.getText().toString());

        if(!isValidEmail(getEmail()) || !isValidUsername(getName()) ){
            if(!isValidEmail(getEmail())) {
                invalidEmail.setText(("Email invalid"));
            }
            if(!isValidUsername(getName())){
                invalidName.setText("Name invalid");
            }
        }

        else {
            Intent SPIntent = new Intent(ServiceProvider.this, ServiceProviderWelcome.class);
            startActivity(SPIntent);
        }

        SPNAME = getName();
    }


}
