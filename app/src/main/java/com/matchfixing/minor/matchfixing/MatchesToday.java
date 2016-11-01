package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_today);

        txtView = (TextView) findViewById(R.id.textView2);
        dateText = (TextView) findViewById(R.id.textView3);
        timeText = (TextView) findViewById(R.id.textView4);
        typeText = (TextView) findViewById(R.id.textView5);

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
                URL url = new URL("http://141.252.224.181:80/GetMatch.php");
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
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                MATCHDATE = user_data.getString("matchDate");
                MATCHTIME = user_data.getString("matchTime");
                MATCHTYPE = user_data.getString("MatchType");

                dateText.setText(MATCHDATE);
                timeText.setText(MATCHTIME);
                typeText.setText(MATCHTYPE);

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
        }
    }
}
