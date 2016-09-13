package com.matchfixing.minor.matchfixing;

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
    String Name, Password, Age, PlayerClass;
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
        Age = ageField.getText().toString();
        PlayerClass = playerClassField.getText().toString();
        BackGround b = new BackGround();
        b.execute(Name, Password, Age);
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String username = params[0];
            String password = params[1];
            String age = params[2];
            // String playerClass = (String) arg0[3];

            String data = "";
            int tmp;

            try {
                System.out.println("trying");
                URL url = new URL("http://192.168.1.18:8080/connection.php");
                String urlParams = "username=" + username + "&password=" + password + "&age=" + age;
                System.out.println("connection");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                System.out.println("connection2");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                System.out.println("connection3");
                OutputStream os = httpURLConnection.getOutputStream();
                System.out.println("connection4");
                os.write(urlParams.getBytes());
                System.out.println("wrote");
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                System.out.println("returned");
                return data;

            /*String link="http://10.0.2.2:8080/connection.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8");
            data += "&" + URLEncoder.encode("playerClass", "UTF-8") + "=" + URLEncoder.encode(playerClass, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            System.out.println("Done");
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();*/
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }
}
