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
    String MATCHID, MATCHDATE, MATCHTIME, MATCHTYPE, MATCHLANE, MATCHDESCRIPTION;

    int userID;
    String s;

    List<String> matches = null;
    List<String> matchDates = null;
    List<String> matchTimes = null;
    List<String> matchLanes = null;
    List<String> matchTypes = null;
    Map<String, Integer> matchIDs = null;
    List<String> matchDescriptionsList = null;

    GridView matchGrid;

    TextView txtView;

    private int previousSelectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_today);

        userID = Integer.parseInt(PersonaliaSingleton.getInstance().getUserID());

        matches = new ArrayList<String>();
        matchIDs = new HashMap<String, Integer>();
        matchDescriptionsList = new ArrayList<>();
        matchDates = new ArrayList<>();
        matchTimes = new ArrayList<>();
        matchLanes = new ArrayList<>();
        matchTypes = new ArrayList<>();

        s = Integer.toString(userID);

        txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText("Hier vind je matches waar mensen jou persoonlijk voor uitgenodigd hebben. Klik een match aan om de details te kunnen zien.");
        matchGrid = (GridView) findViewById(R.id.gridView);

        SetupView();

        String databaseInfo = "userID="+userID;
        String fileName = "GetInvitations.php";
        Invitation.BackGround b = new Invitation.BackGround();

        b.execute(s);
    }

    public void home_home(View view){
        startActivity(new Intent(this, Home.class));
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
                int clickedMatchID = -1;
                //with thclickedMatchIDis method we retrieve the matchID. We need this to implement in the upcoming "MATCH ATTENDEES" table.
                if(matchIDs != null) {
                    clickedMatchID = matchIDs.get(matches.get(position));
                }
                String matchDescription = matches.get(position);
                String description = matchDescriptionsList.get(position);
                String matchDate = matchDates.get(position);
                String matchTime = matchTimes.get(position);
                String matchLane = matchLanes.get(position);
                String matchType = matchTypes.get(position);

                int matchID = clickedMatchID;

                Intent Popup = new Intent(Invitation.this, Popup.class);
                Popup.putExtra("matchDescription", matchDescription);
                Popup.putExtra("desc", description);
                Popup.putExtra("matchID", matchID);
                Popup.putExtra("matchDate", matchDate);
                Popup.putExtra("matchTime", matchTime);
                Popup.putExtra("matchLane", matchLane);
                Popup.putExtra("matchType", matchType);
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
                URL url = new URL("http://141.252.218.158:80/GetInvitations.php");

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
                    MATCHLANE = user_data.getString("lane");
                    MATCHDESCRIPTION = user_data.getString("description");

                    //dates times lanes types
                    matchDates.add(MATCHDATE);
                    matchTimes.add(MATCHTIME);
                    matchTypes.add(MATCHTYPE);
                    matchLanes.add(MATCHLANE);

                    matches.add(MATCHDATE + " " + MATCHTIME + " " + MATCHTYPE + " " + MATCHLANE);
                    matchIDs.put(MATCHDATE + " " + MATCHTIME + " " + MATCHTYPE + " " + MATCHLANE, Integer.parseInt(MATCHID));
                    matchDescriptionsList.add(MATCHDESCRIPTION);

                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                }
            }
            SetupView();
        }
    }
}
