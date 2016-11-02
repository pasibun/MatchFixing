package com.matchfixing.minor.matchfixing;

import android.app.Activity;
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
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jylti on 31-10-2016.
 */
public class MatchesToday extends Activity{
    String day, month, year;
    String MATCHID, MATCHDATE, MATCHTIME, MATCHTYPE;

    TextView txtView;

    // public TextView dateText;
    //TextView timeText;
    //TextView typeText;

    List<String> matches;
    Map<String, Integer> matchIDs;

    GridView matchGrid;

    private int previousSelectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_today);

        matches = new ArrayList<String>();
        matchIDs = new HashMap<String, Integer>();

        txtView = (TextView) findViewById(R.id.textView2);
        // dateText = (TextView) findViewById(R.id.textView3);
        //  timeText = (TextView) findViewById(R.id.textView4);
        // typeText = (TextView) findViewById(R.id.textView5);

        matchGrid = (GridView) findViewById(R.id.gridView);

        day = getIntent().getStringExtra("day");
        month = getIntent().getStringExtra("month");
        year = getIntent().getStringExtra("year");

        txtView.setText(day + "-" + month + "-" + year);

        String databaseInfo = "day=" + day + "&month=" + month + "&year=" + year;
        String file = "GetMatch.php";
        BackGround b = new BackGround();
        b.execute(databaseInfo, file, "MatchesToday");
    }
    //onPostExecute samen ff nakijken.
    // Waarom kan hier wel findViewById en in mijn classe niet.
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String day = params[0];
            String month = params[1];
            String year = params[2];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://141.252.224.164:80/GetMatch.php");
                String urlParams = "day=" + day + "&month=" + month + "&year=" + year;

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

                    matches.add(MATCHTIME + " " + MATCHTYPE);
                    matchIDs.put(MATCHTIME + " " + MATCHTYPE, Integer.parseInt(MATCHID));

                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                }
            }
            final GridView gv = (GridView) findViewById(R.id.gridView);
            gv.setAdapter(new GridViewAdapter(MatchesToday.this, matches){
                public View getView(int position, View convertView, ViewGroup parent){
                    View view = super.getView(position, convertView, parent);

                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.WHITE);

                    tv.setBackgroundColor(Color.parseColor("#ff0000"));
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
                    int a = matchIDs.get(matches.get(position));

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
    }
}