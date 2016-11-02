package com.matchfixing.minor.matchfixing;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LaptopPasibun on 31-10-2016.
 */

public class DbConnection extends AsyncTask<String, String, String>{
    private String name = null, password = null, age = null, kaliber = null, gender = null;

    public String inputDatabase(String inputDb, String file){
        String data="";
        int tmp;
        try {
            URL url = new URL("http://141.252.208.119:80/" +  file);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(inputDb.getBytes());
            os.flush();
            os.close();

            InputStream is = httpURLConnection.getInputStream();
            while((tmp=is.read())!=-1){
                data+= (char)tmp;
            }

            is.close();
            httpURLConnection.disconnect();

            return data;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Exception: "+e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception: "+e.getMessage();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject root = new JSONObject(s);
            JSONObject user_data = root.getJSONObject("user_data");
            name = user_data.getString("name");
            Profile.name = name;
            password  = user_data.getString("password");
            Profile.password = password;
            age  = user_data.getString("age");
            Profile.age = age;
            kaliber = user_data.getString("kaliber");
            Profile.kaliber = kaliber;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
