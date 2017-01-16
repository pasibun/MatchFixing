package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jylti on 11-11-2016.
 */

public class InvitePeople extends Activity {

    Spinner laneSpinner;
    String lane;

    static List<String> occupiedLanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_people);

        SetSpinner("Type");
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
        for(int i = 0; i < occupiedLanes.size(); ++i){
            for(int j = 0; j < lanesList.size(); ++j){
                if(occupiedLanes.get(i).equals(lanes[j])){
                    lanesList.set(j,"Bezet");
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
