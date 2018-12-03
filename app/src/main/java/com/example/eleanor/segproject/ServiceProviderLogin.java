package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ServiceProviderLogin extends ServiceProvider{

    TextView loginFailed;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_login);

        loginFailed = findViewById(R.id.loginFailed);
    }

    public void signIn(View view){

        emailText = findViewById(R.id.SPemail);
        passwordText = findViewById(R.id.SPpassword);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent spWelcome = new Intent(ServiceProviderLogin.this,
                                    ServiceProviderWelcome.class);
                            startActivity(spWelcome);

                        } else {
                            loginFailed.setText("Login failed.");
                        }
                    }
                });
    }



}
