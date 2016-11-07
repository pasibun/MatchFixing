package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jylti on 7-11-2016.
 */

public class Popup extends Activity {
    public String matchDescription;
    public int matchID;

    TextView matchDescField;
    TextView matchIDField;

    Button attendMatchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.matchpopup);

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

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

        attendMatchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String input = "MatchID="+Integer.toString(matchID) + "&MatchOwner=" + Integer.toString(1234)+ "&UserID2=" + Integer.toString(2345);
                String file = "AcceptMatch.php";

                DbConnection db = new DbConnection();
                db.execute(input, file, "");
                finish();
            }
        });
    }

}
