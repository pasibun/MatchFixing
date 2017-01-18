package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jylti on 11-11-2016.
 */

public class InvitePeople extends Activity {

    Spinner laneSpinner;
    String lane;
    TextView description;
    Button setupMatchBtn;

    private Context mContext;

    String matchType;
    String matchDegree;
    String matchTime;
    String matchDescription;
    String matchDate;

    static List<String> occupiedLanes;
    static String userID;
    static String groupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_people);

        SetupBtn();
        GetIntentValues();
        SetupUserInviteBtn();
        SetupGroupInviteBtn();

        mContext = this;

        SetSpinner("Type");
    }

    public void SetupBtn()
    {
        setupMatchBtn = (Button) findViewById(R.id.button6);
        description = (TextView) findViewById(R.id.editText);

        setupMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lane.equals("Bezet"))
                    SetupMatches();
                else
                {
                    String errormsg = "Deze baan is al gereserveerd voor de door u opgegeven tijd, kies een andere baan.";
                    Toast.makeText(mContext, errormsg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void SetupUserInviteBtn()
    {
        setupMatchBtn = (Button) findViewById(R.id.button7);

        setupMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserList = new Intent(InvitePeople.this, User_Group_Invite.class);
                UserList.putExtra("Invitation", "user");
                startActivity(UserList);
            }
        });
    }

    public void SetupGroupInviteBtn()
    {
        setupMatchBtn = (Button) findViewById(R.id.button8);

        setupMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserList = new Intent(InvitePeople.this, User_Group_Invite.class);
                UserList.putExtra("Invitation", "group");
                startActivity(UserList);
            }
        });
    }

    public void GetIntentValues()
    {
        matchType = getIntent().getStringExtra("MATCH_TYPE");
        matchDegree = getIntent().getStringExtra("MATCH_DEGREE");
        matchDate = getIntent().getStringExtra("DATE");
        matchTime = getIntent().getStringExtra("TIME");
    }

    public void SetupMatches()
    {
        matchDescription = description.getText().toString();

        String databaseInfo = "matchDate="+matchDate+"&matchTime="+matchTime+"&matchType="+matchType+"&UserID="+userID+
                "&GroupID="+groupID+"&MatchDegree="+matchDegree+"&playerRankMin="+"0"+"&playerRankMax="+"0"+"&Description="+matchDescription+"&lane="+lane;
        String fileName = "newMatch.php";
        DbConnection b = new DbConnection();
        b.execute(databaseInfo, fileName, "NewMatch");
    }

    private void SetSpinner(final String spinner) {
        laneSpinner = (Spinner) findViewById(R.id.spinner);
        occupiedLanes = NewMatchActivity.occupiedLanes;
        final String[] lanes;
        final String[] lanesLeft;

        lanes = new String[]{
                "1","2","3","4","5","6","7","8","9","10"
        };


        List<String> lanesList = new LinkedList<String>(Arrays.asList(lanes));
        if(occupiedLanes != null) {
            for (int i = 0; i < occupiedLanes.size(); ++i) {
                for (int j = 0; j < lanesList.size(); ++j) {
                    if (occupiedLanes.get(i).equals(lanes[j])) {
                        lanesList.set(j, "Bezet");
                    }
                }
            }
        }

        lanesLeft = lanesList.toArray(lanes);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_activity,lanesLeft);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_activity);
        laneSpinner.setAdapter(spinnerArrayAdapter);

        laneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lane = lanesLeft[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
