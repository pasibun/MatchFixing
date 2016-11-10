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
import java.util.List;
import java.util.Map;

/**
 * Created by LaptopPasibun on 31-10-2016.
 */

public class DbConnection extends AsyncTask<String, String, String>{
    private String name = null, password = null, age = null, kaliber = null, gender = null,
            export, MATCHID, MATCHDATE, MATCHTIME, MATCHTYPE;
    List<String> matches;
    Map<String, Integer> matchIDs;
    private int previousSelectedPosition = -1;

    public String inputDatabase(String inputDb, String file, String export){
        this.export = export;
        //if (inputDb != null || file != null) {
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://141.252.224.129:80/" + file);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(inputDb.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        return data;
       // }
       // return null;
//        else {
//            execute();
//            return null;
//        }
    }

    @Override
    protected String doInBackground(String... params) {
        String inputDb = params[0];
        String file = params[1];
        String export = params[2];
        String testreturn = inputDatabase(inputDb, file, export);

        return testreturn;
    }

    @Override
    protected void onPostExecute(String s) {
        switch(export){
            case "Profile":{
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

                    Users_Object uo = new Users_Object(null,null,null,password,null,null,null,null,name,0, 0,0.0,0.0);
                    Login.checkLoginInfo(uo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               break;

            }
            case "": {
                break;
            }
        }
    }
}
