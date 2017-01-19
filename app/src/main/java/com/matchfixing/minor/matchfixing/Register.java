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

    EditText userNameField, nameField,  passwordField, passwordCheckField, ageField, genderField, mailField,
             adressField, cityField, phoneField, mobileField, singleScoreField, doubleScoreField;
    String userName, name, password, passwordcheck, birthDate, playerGender, email, adress, city, phone, mobile, singleScore, doubleScore;
    private static String msg;
    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        userNameField = (EditText)findViewById(R.id.edtuserid);
        nameField = (EditText)findViewById(R.id.edtfullname);
        passwordField = (EditText)findViewById(R.id.edtpass);
        passwordCheckField = (EditText)findViewById(R.id.edtpasscheck);
        ageField = (EditText)findViewById(R.id.edtuserbirthdate);
        genderField = (EditText)findViewById(R.id.edtusergender);
        mailField = (EditText)findViewById(R.id.edtUserMail);
        adressField = (EditText)findViewById(R.id.edtUserAdress);
        cityField = (EditText)findViewById(R.id.edtUserCity);
        phoneField = (EditText)findViewById(R.id.edtUserPhone);
        mobileField = (EditText)findViewById(R.id.edtUserMobile);
        singleScoreField = (EditText)findViewById(R.id.edtUserSingle);
        doubleScoreField = (EditText)findViewById(R.id.edtUserDouble);

        ctx = this;
    }

    public void home_home(View view){
        startActivity(new Intent(this, Home.class));
    }

    public void register_register(View v){
        userName = userNameField.getText().toString();
        name = nameField.getText().toString();
        password = passwordField.getText().toString();
        passwordcheck = passwordCheckField.getText().toString();
        birthDate = ageField.getText().toString();
        playerGender = genderField.getText().toString();
        email = mailField.getText().toString();
        adress = adressField.getText().toString();
        city = cityField.getText().toString();
        phone = phoneField.getText().toString();
        mobile = mobileField.getText().toString();
        singleScore = singleScoreField.getText().toString();
        doubleScore = doubleScoreField.getText().toString();

        if (password.equals(passwordcheck)) {
            if(userName != null && birthDate != null && playerGender != null) {
                String databaseInput = "username=" + userName + "&name=" + name + "&password=" + password + "&birthDate=" + birthDate + "&playerGender=" + playerGender
                        + "&email=" + email + "&adress=" + adress + "&city=" + city + "&phone=" + phone + "&mobile=" + mobile
                        + "&singleScore=" + singleScore + "&doubleScore=" + doubleScore;

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
