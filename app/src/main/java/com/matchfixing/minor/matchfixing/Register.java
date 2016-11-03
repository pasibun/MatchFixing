package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jylti on 9-9-2016.
 */
public class Register extends Activity {

    EditText nameField, passwordField, passwordCheckField, ageField, playerClassField;
    String name, password, passwordcheck, age, playerClass;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        nameField = (EditText)findViewById(R.id.edtuserid);
        passwordField = (EditText)findViewById(R.id.edtpass);
        passwordCheckField = (EditText)findViewById(R.id.edtpasscheck);
        ageField = (EditText)findViewById(R.id.edtuserage);
        playerClassField = (EditText)findViewById(R.id.edtuserklasse);
    }

    public void register_register(View v){

        name = nameField.getText().toString();
        password = passwordField.getText().toString();
        passwordcheck = passwordCheckField.getText().toString();
        age = ageField.getText().toString();
        playerClass = playerClassField.getText().toString();
        if (password.equals(passwordcheck)) {
            String databaseInput = "username=" + name + "&password=" + password + "&age=" + age + "&playerClass=" + playerClass;
            String msg = "Data saved successfully";
            String file = "connection.php";
            DbConnection b = new DbConnection();
            b.execute(databaseInput, file, null);
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        }
        else{
            String text = "Make sure your passwords are equal.";
            Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
        }
    }
}
