package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by jylti on 9-9-2016.
 */
public class RegisterActivity extends Activity {

    EditText nameField, passwordField, passwordCheckField, ageField, playerClassField;
    String Name, Password, PasswordCheck, Age, PlayerClass;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText)findViewById(R.id.edtuserid);
        passwordField = (EditText)findViewById(R.id.edtpass);
        passwordCheckField = (EditText)findViewById(R.id.edtpasscheck);
        ageField = (EditText)findViewById(R.id.edtuserage);
        playerClassField = (EditText)findViewById(R.id.edtuserklasse);
    }

    public void register_register(View v){

        Name = nameField.getText().toString();
        Password = passwordField.getText().toString();
        PasswordCheck = passwordCheckField.getText().toString();
        Age = ageField.getText().toString();
        PlayerClass = playerClassField.getText().toString();
        if (Password == PasswordCheck) {

            BackGround b = new BackGround();
            b.execute(Name, Password, Age, PlayerClass);
        }
        else{
            String text = "Make sure your passwords are equal.";
            Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
        }
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String username = params[0];
            String password = params[1];
            String age = params[2];
            String playerClass = params[3];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://141.252.218.197:80/connection.php");
                String urlParams = "username=" + username + "&password=" + password + "&age=" + age + "&playerClass=" + playerClass;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");

                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();

                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();

                httpURLConnection.disconnect();

                return data;

            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            s="Data saved successfully.";
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }
}
