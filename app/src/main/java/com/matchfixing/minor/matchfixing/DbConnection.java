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
            exput, MATCHID, MATCHDATE, MATCHTIME, MATCHTYPE;
    List<String> matches;
    Map<String, Integer> matchIDs;
    private int previousSelectedPosition = -1;

    public String inputDatabase(String inputDb, String file, String exput){
        this.exput = exput;
        if (inputDb != null || file != null) {
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://141.252.208.119:80/" + file);

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

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }
        else {
            execute();
            return null;
        }
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        switch(exput){
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "MatchesToday":{
//                String err = null;
//                String[] matchStrings = s.split("&");
//                int fieldID = -1;
//
//                for(int i = 0; i < matchStrings.length; ++i)
//                {
//                    try {
//                        JSONObject root = new JSONObject(matchStrings[i]);
//                        JSONObject user_data = root.getJSONObject("user_data");
//                        MATCHID = user_data.getString("MatchID");
//                        MATCHDATE = user_data.getString("matchDate");
//                        MATCHTIME = user_data.getString("matchTime");
//                        MATCHTYPE = user_data.getString("MatchType");
//
//                        matches.add(MATCHTIME + " " + MATCHTYPE);
//                        matchIDs.put(MATCHTIME + " " + MATCHTYPE, Integer.parseInt(MATCHID));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        err = "Exception: " + e.getMessage();
//                    }
//                }
//                final GridView gv = (GridView) findViewById(R.id.gridView);
//                gv.setAdapter(new GridViewAdapter(MatchesToday.this, matches){
//                    public View getView(int position, View convertView, ViewGroup parent){
//                        View view = super.getView(position, convertView, parent);
//
//                        TextView tv = (TextView) view;
//                        tv.setTextColor(Color.WHITE);
//
//                        tv.setBackgroundColor(Color.parseColor("#ff0000"));
//                        tv.setTextSize(25);
//
//                        return tv;
//                    }
//                });
//
//                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        TextView tv = (TextView) view;
//                        tv.setTextColor(Color.RED);
//
//                        TextView previousSelectedView = (TextView) gv.getChildAt(previousSelectedPosition);
//
//                        //with this method we retrieve the matchID. We need this to implement in the upcoming "MATCH ATTENDEES" table.
//                        int a = matchIDs.get(matches.get(position));
//
//                        // If there is a previous selected view exists
//                        if (previousSelectedPosition != -1)
//                        {
//                            previousSelectedView.setSelected(false);
//
//                            previousSelectedView.setTextColor(Color.WHITE);
//                        }
//                        previousSelectedPosition = position;
//                    }
//                });
                break;
            }
        }
    }
}
