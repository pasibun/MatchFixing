package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

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

    String playerMin;
    String playerMax;

    String matchDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmatch_details);

        SetupSeekBars();
        SetupBtn();
        GetIntentValues();
    }

    public void SetupMatches()
    {
        matchDescription = description.getText().toString();

        String databaseInfo = "matchDate="+matchDate+"&matchTime="+matchTime+"&matchType="+matchType+"&UserID="+"0"+
                "&GroupID="+"0"+"&MatchDegree="+matchDegree+"&playerRankMin="+playerMin+"&playerRankMax="+playerMax+"&Description="+matchDescription;
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
                SetupMatches();
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


}
