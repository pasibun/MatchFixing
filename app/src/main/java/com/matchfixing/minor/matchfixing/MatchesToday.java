package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by jylti on 31-10-2016.
 */
public class MatchesToday extends Activity{
    String day, month, year;
    String MATCHDATE, MATCHTIME, MATCHTYPE;

    TextView txtView;

    public TextView dateText;
    TextView timeText;
    TextView typeText;

    List<String> matches;
    GridView matchGrid;

    private int previousSelectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_today);

        matches = new ArrayList<String>();

        txtView = (TextView) findViewById(R.id.textView2);
       // dateText = (TextView) findViewById(R.id.textView3);
      //  timeText = (TextView) findViewById(R.id.textView4);
       // typeText = (TextView) findViewById(R.id.textView5);

        matchGrid = (GridView) findViewById(R.id.gridView);

        day = getIntent().getStringExtra("day");
        month = getIntent().getStringExtra("month");
        year = getIntent().getStringExtra("year");

        txtView.setText(day + "-" + month + "-" + year);

        BackGround b = new BackGround();
        b.execute(day, month, year);
    }

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

            for(int i = 0; i < matchStrings.length; ++i)
            {
                try {
                    JSONObject root = new JSONObject(matchStrings[i]);
                    JSONObject user_data = root.getJSONObject("user_data");
                    MATCHDATE = user_data.getString("matchDate");
                    MATCHTIME = user_data.getString("matchTime");
                    MATCHTYPE = user_data.getString("MatchType");

                    matches.add(MATCHTIME + " " + MATCHTYPE);

                    final GridView gv = (GridView) findViewById(R.id.gridView);
                    gv.setAdapter(new GridViewAdapter(MatchesToday.this, matches){
                        public View getView(int position, View convertView, ViewGroup parent){
                            View view = super.getView(position, convertView, parent);

                            TextView tv = (TextView) view;
                            tv.setTextColor(Color.WHITE);
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

                            // If there is a previous selected view exists
                            if (previousSelectedPosition != -1)
                            {
                                // Set the last selected View to deselect
                                previousSelectedView.setSelected(false);

                                // Set the last selected View text color as deselected item
                                previousSelectedView.setTextColor(Color.WHITE);
                            }

                            // Set the current selected view position as previousSelectedPosition
                            previousSelectedPosition = position;
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                }
            }
        }
    }
}
