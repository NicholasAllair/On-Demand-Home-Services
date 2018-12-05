package com.example.eleanor.segproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class HomeOwner extends User{
    public static String HONAME;

    public FirebaseAuth mAuth;

    TextView invalidEmail, invalidName;
    EditText editName, editEmail, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_create);
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void onClickCreateHOProfile(View view){
        invalidEmail = findViewById(R.id.InvalidEmail);
        invalidName = findViewById(R.id.InvalidUsername);

        editName = findViewById(R.id.EnterHOName);
        editEmail = findViewById(R.id.EnterHOEmail);
        editPassword = findViewById(R.id.EnterHOPassword);

        String HOname = editName.getText().toString();
        String HOemail = editEmail.getText().toString();
        String HOpassword = editPassword.getText().toString();

        setName(HOname);
        setEmail(HOemail);
        setPassword(HOpassword);

        if(!isValidEmail(getEmail()) || !isValidUsername(getName()) ) {
            if (!isValidEmail(this.getEmail())) {
                invalidEmail.setText(("Email invalid"));
            }
            if (!isValidUsername(this.getName())) {
                invalidName.setText("Name invalid");
            }
        }
        else {
            this.mAuth.createUserWithEmailAndPassword(HOemail, HOpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                System.out.println(user);
                                // new user created successfully
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference dbRef = database.getReference().child("homeOwners");

                                dbRef.child(user.getUid());
                                dbRef.child(user.getUid()).child("Name").setValue(getName());
                                dbRef.child(user.getUid()).child("Email").setValue(getEmail());
                                dbRef.child(user.getUid()).child("Password").setValue(getPassword());

                                Intent SPIntent = new Intent(HomeOwner.this, HomeOwnerWelcome.class);
                                startActivity(SPIntent);

                            } else {
                                System.out.println("Failed to create user");
                                System.out.println(task.getException());
                            }
                        }
                    });
        }

    }

    public void existingHOUser(View view){
        Intent login = new Intent(HomeOwner.this, HomeOwnerLogin.class);
        startActivity(login);
    }

}
