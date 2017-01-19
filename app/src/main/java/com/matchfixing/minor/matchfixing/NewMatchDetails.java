package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jylti on 10-11-2016.
 */

public class NewMatchDetails extends Activity {

    SeekBar playerMinRanking;
    SeekBar playerMaxRanking;

    Button setupMatchBtn;

    TextView minimum;
    TextView maximum;
    TextView description;

    String matchType;
    String matchDegree;
    String matchTime;
    String matchDate;
    String hourBefore;
    String hourAfter;

    String playerMin;
    String playerMax;

    String matchDescription;
    Spinner laneSpinner;
    String lane;
    static List<String> occupiedLanes;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmatch_details);

        mContext = this;

        SetupSeekBars();
        SetupBtn();
        GetIntentValues();

        SetSpinner("Type");
    }

    public void home_home(View view){
        startActivity(new Intent(this, Home.class));
    }

    public void SetupMatches()
    {
        matchDescription = description.getText().toString();

        String databaseInfo = "matchDate="+matchDate+"&matchTime="+matchTime+"&matchType="+matchType+"&UserID="+"0"+
                "&GroupID="+"0"+"&MatchDegree="+matchDegree+"&playerRankMin="+playerMin+"&playerRankMax="+playerMax+"&Description="+matchDescription+"&lane="+lane;
        String fileName = "newMatch.php";
        DbConnection b = new DbConnection();
        b.execute(databaseInfo, fileName, "NewMatch");
    }

    public void SetupBtn()
    {
        setupMatchBtn = (Button) findViewById(R.id.button5);
        description = (TextView) findViewById(R.id.editText3);

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

    public void GetIntentValues()
    {
        matchType = getIntent().getStringExtra("MATCH_TYPE");
        matchDegree = getIntent().getStringExtra("MATCH_DEGREE");
        matchDate = getIntent().getStringExtra("DATE");
        matchTime = getIntent().getStringExtra("TIME");
        hourBefore = getIntent().getStringExtra("HOUR_BEFORE");
        hourAfter = getIntent().getStringExtra("HOUR_AFTER");
    }

    public void SetupSeekBars()
    {
        playerMaxRanking = (SeekBar) findViewById(R.id.seekBar);
        playerMinRanking = (SeekBar) findViewById(R.id.seekBar3);

        minimum = (TextView) findViewById(R.id.textView14);
        maximum = (TextView) findViewById(R.id.textView13);


        playerMinRanking.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            float progress_value;
            @Override
            public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser)
            {
                progress_value = (float)progress/10;

                minimum.setText(Float.toString(progress_value));
                playerMin = Float.toString(progress_value);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                minimum.setText(Float.toString(progress_value));
                playerMin = Float.toString(progress_value);
            }
        });

        playerMaxRanking.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            float progress_value;
            @Override
            public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser)
            {
                progress_value = (float)progress/10;

                maximum.setText(Float.toString(progress_value));
                playerMax = Float.toString(progress_value);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                maximum.setText(Float.toString(progress_value));
                playerMax = Float.toString(progress_value);
            }
        });
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
