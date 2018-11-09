package com.example.eleanor.segproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.eleanor.segproject.HomeOwner.HONAME;

public class HomeOwnerWelcome extends AppCompatActivity{
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_homepage);

        setUserName(HONAME);
    }

    public String getUserName(){
        EditText username = findViewById(R.id.EnterName);
        return username.getText().toString();
    }

    public void setUserName(String name){
        username = findViewById(R.id.HOusername);
        username.setText(name);
    }
}
