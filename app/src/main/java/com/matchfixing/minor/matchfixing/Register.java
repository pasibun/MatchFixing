package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private static String msg;
    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        nameField = (EditText)findViewById(R.id.edtuserid);
        passwordField = (EditText)findViewById(R.id.edtpass);
        passwordCheckField = (EditText)findViewById(R.id.edtpasscheck);
        ageField = (EditText)findViewById(R.id.edtuserage);
        playerClassField = (EditText)findViewById(R.id.edtuserklasse);
        ctx = this;
    }

    public void register_register(View v){
        name = nameField.getText().toString();
        password = passwordField.getText().toString();
        passwordcheck = passwordCheckField.getText().toString();
        age = ageField.getText().toString();
        playerClass = playerClassField.getText().toString();
        if (password.equals(passwordcheck)) {
            if(name != null && age != null && playerClass != null) {
                String databaseInput = "username=" + name + "&password=" + password + "&age=" + age + "&playerClass=" + playerClass;
                String file = "connection.php";
                String export = "Register";
                DbConnection b = new DbConnection();
                b.execute(databaseInput, file, export);
            }else{
                msg = "Voer alle gegevens in aub.";
                Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
            }
        }
        else{
            msg = "Uw wachtwoord komt niet overeen.";
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        }
    }

    static void succesfullRegister(){
        msg = "Data is goed opgeslagen. Login aub.";
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        Intent login = new Intent(ctx, Login.class);
        ctx.startActivity(login);
    }
}
