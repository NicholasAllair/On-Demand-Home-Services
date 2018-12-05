package com.example.eleanor.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeOwnerLogin extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView loginFailed;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_login);

        this.mAuth = FirebaseAuth.getInstance();

        loginFailed = findViewById(R.id.HOloginFailed);

        emailText = findViewById(R.id.HOemail);
        passwordText = findViewById(R.id.HOpassword);
    }

    public void signHOIn(View view){

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent spWelcome = new Intent(HomeOwnerLogin.this,
                                    HomeOwnerWelcome.class);
                            startActivity(spWelcome);

                        } else {
                            loginFailed.setText("Login failed.");
                        }
                    }
                });
    }
}
