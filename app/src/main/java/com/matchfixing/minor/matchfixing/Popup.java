package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jylti on 7-11-2016.
 */

public class Popup extends Activity {
    public String matchDescription, firstName, groupOwner, description, date, time, lane, type;
    public int matchID,userID, groupID;

    TextView matchDateTimeField;
    TextView matchTypeField;
    TextView matchLaneField;
    TextView matchDescriptionField;

    Button attendMatchButton;

    static List<String> matchIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.matchpopup);

        matchIDs = new ArrayList<String>();

        matchDescription = getIntent().getStringExtra("matchDescription");
        matchID = getIntent().getIntExtra("matchID",0);
        userID = getIntent().getIntExtra("userID",0);
        groupID = getIntent().getIntExtra("groupID",0);
        firstName = getIntent().getStringExtra("firstName");
        groupOwner = getIntent().getStringExtra("groupOwner");
        description = getIntent().getStringExtra("desc");

        date = getIntent().getStringExtra("matchDate");
        time = getIntent().getStringExtra("matchTime");
        lane = getIntent().getStringExtra("matchLane");
        type = getIntent().getStringExtra("matchType");

        matchDateTimeField = (TextView) findViewById(R.id.textView6);
        matchDescriptionField = (TextView) findViewById(R.id.textView7);
        matchLaneField = (TextView) findViewById(R.id.textView19);
        matchTypeField = (TextView) findViewById(R.id.textView18);

        attendMatchButton = (Button) findViewById(R.id.attendMatch);

        matchDateTimeField.setText("Datum: " + date + "\n " + " Tijd: " + time);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.85), (int)(height*.5));

        if(userID > 0)
        {
            attendMatchButton.setText("Invite");
            matchDateTimeField.setText(firstName);
            matchLaneField.setText("");
            matchTypeField.setText("");
            matchDescriptionField.setText(description);
            attendMatchButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)
                {
                    InvitePeople.userID = Integer.toString(userID);
                    finish();
                    User_Group_Invite.mActivity.finish();
                }
            });
        }else if(groupID > 0){
            attendMatchButton.setText("Invite");
            matchDateTimeField.setText(groupOwner);
            matchLaneField.setText("");
            matchTypeField.setText("");
            matchDescriptionField.setText(Integer.toString(groupID));
            attendMatchButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)
                {
                    InvitePeople.groupID = Integer.toString(groupID);
                    finish();
                    User_Group_Invite.mActivity.finish();
                }
            });
        }
        else {
            matchDescriptionField.setText("Beschrijving: " + description);
            matchLaneField.setText("Baan: " + lane);
            matchTypeField.setText("Type: " + type);
            attendMatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GetMatch();
                    finish();
                }
            });
        }
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
