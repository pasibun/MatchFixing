package com.example.test;

/**
 * Created by jylti on 22-9-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends Activity {

    String name, password, age, kaliber;
    TextView nameTV, ageTV, passwordTV, kaliberTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        nameTV = (TextView) findViewById(R.id.name_field);
        ageTV = (TextView) findViewById(R.id.password_field);
        passwordTV = (TextView) findViewById(R.id.age_field);
        kaliberTV = (TextView) findViewById(R.id.kaliber_field);

        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
        age = getIntent().getStringExtra("age");
        kaliber = getIntent().getStringExtra("kaliber");

        nameTV.setText("Welcome "+name);
        passwordTV.setText("Your password is "+password);
        ageTV.setText("Your age is "+age);
        kaliberTV.setText("Kaliber: " + kaliber);
    }
}
