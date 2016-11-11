package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invitation extends Activity {
    String MATCHID, MATCHDATE, MATCHTIME, MATCHTYPE;

    int userID;

    List<String> matches;
    Map<String, Integer> matchIDs;

    GridView matchGrid;

    TextView txtView;

    private int previousSelectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_today);

        FindMatchByInvitation();
        SetupView();
    }

    public void FindMatchByInvitation()
    {
        userID = 123;

        matches = new ArrayList<String>();
        matchIDs = new HashMap<String, Integer>();

        txtView = (TextView) findViewById(R.id.textView2);

        matchGrid = (GridView) findViewById(R.id.gridView);

        String databaseInfo = "userID="+userID;
        String fileName = "GetInvitations.php";
        Invitation.BackGround b = new Invitation.BackGround();
        b.execute(Integer.toString(userID));

    }

    public void SetupView()
    {
        final GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new GridViewAdapter(Invitation.this, matches){
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view;
                tv.setTextColor(Color.WHITE);

                tv.setBackgroundColor(Color.parseColor("#23db4e"));
                tv.setTextSize(25);

                return tv;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                tv.setTextColor(Color.RED);

                TextView previousSelectedView = (TextView) gv.getChildAt(previousSelectedPosition);

                //with this method we retrieve the matchID. We need this to implement in the upcoming "MATCH ATTENDEES" table.
                int clickedMatchID = matchIDs.get(matches.get(position));

                String matchDescription = matches.get(position);
                int matchID = clickedMatchID;

                Intent Popup = new Intent(Invitation.this, Popup.class);
                Popup.putExtra("matchDescription", matchDescription);
                Popup.putExtra("matchID", matchID);
                startActivity(Popup);

                // If there is a previous selected view exists
                if (previousSelectedPosition != -1)
                {
                    previousSelectedView.setSelected(false);

                    previousSelectedView.setTextColor(Color.WHITE);
                }
                previousSelectedPosition = position;
            }
        });
    }
    //    //onPostExecute samen ff nakijken.
//    // Waarom kan hier wel findViewById en in mijn classe niet.
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String userID = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://141.252.224.161:80/GetInvitations.php");
                String urlParams = "userID=" + userID;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
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
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }
        @Override
        protected void onPostExecute(String s) {
            String err = null;
            String[] matchStrings = s.split("&");
            int fieldID = -1;

            for(int i = 0; i < matchStrings.length; ++i)
            {
                try {
                    JSONObject root = new JSONObject(matchStrings[i]);
                    JSONObject user_data = root.getJSONObject("user_data");
                    MATCHID = user_data.getString("MatchID");
                    MATCHDATE = user_data.getString("matchDate");
                    MATCHTIME = user_data.getString("matchTime");
                    MATCHTYPE = user_data.getString("MatchType");

                    matches.add(MATCHDATE + " " + MATCHTIME + " " + MATCHTYPE);
                    matchIDs.put(MATCHTIME + " " + MATCHTYPE, Integer.parseInt(MATCHID));

                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                }
            }
            SetupView();
        }
    }
}
