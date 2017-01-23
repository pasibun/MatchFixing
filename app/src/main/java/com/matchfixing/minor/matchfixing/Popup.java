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
    public String matchDescription, firstName, lastName, groupOwner, groupName, description, date, time, lane, type;
    public int matchID,userID, groupID;

    TextView matchDateTimeField;
    TextView matchTypeField;
    TextView matchLaneField;
    TextView matchDescriptionField;

    Button attendMatchButton;

    static List<String> matchIDs;
    static String matchType;
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
        lastName = getIntent().getStringExtra("lastName");
        groupOwner = getIntent().getStringExtra("groupOwner");
        groupName = getIntent().getStringExtra("groupName");
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
            matchDateTimeField.setText("");
            matchLaneField.setText("");
            matchTypeField.setText("");
            matchDescriptionField.setText(firstName + " " + lastName);
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
            matchDateTimeField.setText(groupName);
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
        db.matchType  = type;
        db.execute(input, file, "GetMatch");
    }

    public void CheckMatch(String user_data, String matchType)
    {
        String[] matchStrings = user_data.split("&");
        String selectedMatchID = "";
        String selectedMatchType = matchType;
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
        CheckIfIDExists(selectedMatchID, selectedMatchType);
    }

    private void CheckIfIDExists(String id, String type)
    {
        if(!matchIDs.contains(id))
        {
            new Popup().AcceptMatch("AcceptMatch.php", id, type);
        }
        else {
            new Popup().GetMatchByID("GetAcceptedMatchesByID.php", id);
        }
    }

    private void AcceptMatch(String file, String matchID, String matchType)
    {

        String input = "MatchID="+matchID + "&MatchOwner=" + Integer.toString(1234)+ "&UserID2=" + PersonaliaSingleton.getInstance().getUserID() + "&matchType=" + matchType;

        DbConnection db = new DbConnection();
        if(matchType.equals("Single")){
            db.matchId = matchID;
            db.execute(input, file, "DeleteMatch");
        }else
            db.execute(input, file, "");
    }

    private void GetMatchByID(String file, String matchID)
    {
        String input = "MatchID="+matchID;

        DbConnection db = new DbConnection();
        db.execute(input, file, "GetMatchByID");
    }

    private void UpdateMatch(String file, String matchID, boolean deleteMatch)
    {
        String input = "MatchID="+matchID + "&UserID=" + PersonaliaSingleton.getInstance().getUserID();

        DbConnection db = new DbConnection();
        if(deleteMatch) {
            db.matchId = matchID;
            db.execute(input, file, "DeleteMatch");
        }
        else{
            db.execute(input, file, "");
        }
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
                new Popup().UpdateMatch("UpdateMatch.php", MATCHID, false);
            } else if (USERID4.equals("null")) {
                new Popup().UpdateMatch("Update4thPlayer.php", MATCHID, true);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
