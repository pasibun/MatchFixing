package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
import java.util.List;

/**
 * Created by jylti on 7-11-2016.
 */

public class Popup extends Activity {
    public String matchDescription;
    public int matchID;

    TextView matchDescField;
    TextView matchIDField;

    Button attendMatchButton;

    static List<String> matchIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.matchpopup);

        matchIDs = new ArrayList<String>();

        matchDescription = getIntent().getStringExtra("matchDescription");
        matchID = getIntent().getIntExtra("matchID",0);

        matchDescField = (TextView) findViewById(R.id.textView6);
        matchIDField = (TextView) findViewById(R.id.textView7);

        attendMatchButton = (Button) findViewById(R.id.attendMatch);

        matchDescField.setText(matchDescription);
        matchIDField.setText(Integer.toString(matchID));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.85), (int)(height*.3));
        
        attendMatchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                GetMatch();
                finish();
            }
        });
    }

    private void GetMatch()
    {
        String input = "MatchID="+Integer.toString(matchID);
        String file = "GetAllAcceptedMatches.php";

        DbConnection db = new DbConnection();
        db.execute(input, file, "GetMatch");
    }

    public void CheckMatch(String user_data)
    {
        String[] matchStrings = user_data.split("&");
        String selectedMatchID = "";
        for(int i = 0; i < matchStrings.length; ++i) {
            try {

                JSONObject root = new JSONObject(matchStrings[i]);
                JSONObject s = root.getJSONObject("user_data");

                String MATCHID = s.getString("MatchID");
                selectedMatchID = s.getString("SelectedMatchID");
                matchIDs.add(MATCHID);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        CheckIfIDExists(selectedMatchID);
    }

    private void CheckIfIDExists(String id)
    {
        if(!matchIDs.contains(id))
        {
            new Popup().AcceptMatch("AcceptMatch.php", id);
        }
        else {
            new Popup().GetMatchByID("GetAcceptedMatchesByID.php", id);
        }
    }

    private void AcceptMatch(String file, String matchID)
    {
        String input = "MatchID="+matchID + "&MatchOwner=" + Integer.toString(1234)+ "&UserID2=" + Integer.toString(3456);

        DbConnection db = new DbConnection();
        db.execute(input, file, "");
    }

    private void GetMatchByID(String file, String matchID)
    {
        String input = "MatchID="+matchID;

        DbConnection db = new DbConnection();
        db.execute(input, file, "GetMatchByID");
    }

    private void UpdateMatch(String file, String matchID)
    {
        String input = "MatchID="+matchID + "&UserID=" + Integer.toString(5678);

        DbConnection db = new DbConnection();
        db.execute(input, file, "");
    }

    public void CheckForMatchToUpdate(String user_data)
    {
        try {
            JSONObject root = new JSONObject(user_data);
            JSONObject s = root.getJSONObject("user_data");

            String MATCHID = s.getString("MatchID");
            String MATCHOWNER = s.getString("MatchOwner");
            String USERID3 = s.getString("UserID3");
            String USERID4 = s.getString("UserID4");

            if (USERID3.equals("null")) {
                new Popup().UpdateMatch("UpdateMatch.php", MATCHID);
            } else if (USERID4.equals("null")) {
                new Popup().UpdateMatch("Update4thPlayer.php", MATCHID);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
