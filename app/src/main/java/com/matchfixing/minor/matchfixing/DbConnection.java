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
    private String export;

    public String inputDatabase(String inputDb, String file, String export){
        this.export = export;
            String data = "";
            int tmp;
            try {

                URL url = new URL("http://10.0.0.34:80/" + file);

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
        JSONObject root;
        JSONObject user_data;
        switch(export){
            case "Profile":{
                try {
                    if(s.length() != 16){
                        root = new JSONObject(s);
                        user_data = root.getJSONObject("user_data");
                        Users_Object uo = new Users_Object(
                                user_data.getString("id"),
                                user_data.getString("password"),
                                user_data.getString("firstName"),
                                user_data.getString("email"),
                                user_data.getString("address"),
                                user_data.getString("city"),
                                user_data.getString("gender"),
                                user_data.getString("dateOfBirth"),
                                user_data.getString("username"),
                                user_data.getString("mobilePhone"),
                                user_data.getString("phone"),
                                user_data.getDouble("scoreSingle"),
                                user_data.getDouble("scoreDouble"));
                        Login.checkLoginInfo(uo);
                    }else Login.checkLoginInfo(null);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
               break;
            }
            case "Register":{
                Register.succesfullRegister();
                break;
            }
            case "Group": {
                String[] users = s.split("&");
                for(int i = 0; i < users.length; ++i) {
                    try {
                        root = new JSONObject(users[i]);
                        user_data = root.getJSONObject("user_data");
                        Groups.personList.add(user_data.getString("firstName") + user_data.getString("lastName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case "GetMatch":{
                Popup p = new Popup();
                if(s != "")
                    p.CheckMatch(s);
            }
            case "GetMatchByID":{
                Popup p = new Popup();
                if(s != "")
                    p.CheckForMatchToUpdate(s);
            }
            case "GetLanes":{
                if(s != "")
                    NewMatchActivity.GetLanes(s);
            }
            case "GetGroup" : {
                String[] users = s.split("&");

                for(int i = 0; i < users.length; ++i) {
                    try {
                        root = new JSONObject(users[i]);
                        user_data = root.getJSONObject("user_data");
                        Groups_Object go = new Groups_Object(user_data.getString("GroupName"));
                        for (int j = 1; j < 15; j++){
                            if (!user_data.getString("Member"+ j).equals(null) || !user_data.getString("Member"+ j).equals("")) {
                                go.addMember(user_data.getString("Member" + j));
                            }
                        }
                        Groups.groupList.add(go);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }
}
