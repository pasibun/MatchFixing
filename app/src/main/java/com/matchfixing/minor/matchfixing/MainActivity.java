package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.matchfixing.minor.matchfixing.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void ButtonOnClick(View view)
    {
        /*EditText nameField = (EditText)findViewById(R.id.edtuserid);
        EditText passwordField = (EditText)findViewById(R.id.edtpass);
        EditText passwordCheckField = (EditText)findViewById(R.id.edtpasscheck);
        EditText ageField = (EditText)findViewById(R.id.edtuserage);
        EditText playerClassField = (EditText)findViewById(R.id.edtuserklasse);


        String username = nameField.getText().toString();
        String password = passwordField.getText().toString();
        String age = ageField.getText().toString();
        String playerClass = playerClassField.getText().toString();

        System.out.println("knop");

        //if(password == passwordCheck) {
            new RegisterActivity().execute(username, password, age, playerClass);
        //}*/
        startActivity(new Intent(this, RegisterActivity.class));

    }
}
