package com.matchfixing.minor.matchfixing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewMatchActivity extends AppCompatActivity {

    Button btn;
    Button button_stpd;

    Spinner matchTypeSpinner;
    Spinner matchDegreeSpinner;
    Spinner privateSpinner;

    TextView dateField;
    TextView timeField;
    TextView matchTypeField;
    TextView matchDegreeField;
    TextView invitedPersonField;

    int year_x, month_x, day_x;
    int hour_x, minute_x, hourBefore, hourAfter;

    static final int DIALOG_ID = 0;
    static final int DIALOG_ID2 = 1;

    public String dateString;
    public String timeString;

    public String timeStringBefore;
    public String timeStringAfter;

    String matchDate;
    String matchTime;
    String matchTimeBefore;
    String matchTimeAfter;
    String matchType;
    String matchDegreeString;
    String matchVisibility;

    String invitedPersonName;

    Context ctx=this;
    String MATCHDATE = null, MATCHTIME = null, MATCHTYPE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmatch_activity);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        ShowDialogOnButtonClick();
        ShowTimePickerDialog();
        SetSpinner("Type");
    }

    private void SetSpinner(final String spinner)
    {
        matchTypeSpinner = (Spinner)findViewById(R.id.spinner);
        matchDegreeSpinner = (Spinner)findViewById(R.id.spinner2);
        privateSpinner = (Spinner) findViewById(R.id.spinner3);

        final String[] matchTypes;
        final String[] matchDegree;
        final String[] privateMatch;


            matchTypes = new String[]{
                    "Single",
                    "Double",
                    "Tournament"
            };
            matchDegree = new String[]
            {
                   "Friendly",
                   "Competitive"
            };

            privateMatch = new String[]
            {
                  "Private",
                  "Public"
            };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_activity,matchTypes);
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_activity,matchDegree);
        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(this, R.layout.spinner_activity,privateMatch);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_activity);
        matchTypeSpinner.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_activity);
        matchDegreeSpinner.setAdapter(spinnerArrayAdapter2);

        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_activity);
        privateSpinner.setAdapter(spinnerArrayAdapter3);

        matchTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    matchType = matchTypes[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        matchDegreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                matchDegreeString = matchDegree[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        privateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matchVisibility = privateMatch[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void ShowDialogOnButtonClick()
    {
        btn = (Button)findViewById(R.id.dateBtn);

        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    public void ShowTimePickerDialog()
    {
        button_stpd = (Button)findViewById(R.id.timeBtn);

        button_stpd.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        showDialog(DIALOG_ID2);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id == DIALOG_ID)
        {
            return new DatePickerDialog(this, dPickerListener, year_x, month_x, day_x);
        }
        if(id == DIALOG_ID2)
        {
            return new TimePickerDialog(this, tPickerListener, hour_x, minute_x, true);
        }
        return  null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateField = (TextView)findViewById(R.id.DateTextField);

            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;

            String monthString;
            String day;

            String yearString = Integer.toString(year_x);
            if(month_x >= 1 && month_x < 10)
            {
                monthString = "0" + Integer.toString(month_x);
            }
            else
                monthString = Integer.toString(month_x);

            if(day_x >= 1 && day_x < 10)
                day = "0" + Integer.toString(day_x);
            else
                day = Integer.toString(day_x);

            dateString = yearString + "-" + monthString + "-" + day;

            dateField.setText("Date: " + day_x+ " / "  + month_x + " / " + year_x);
        }
    };

    private TimePickerDialog.OnTimeSetListener tPickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timeField = (TextView)findViewById(R.id.TimeTextField);

            hour_x = hourOfDay;
            minute_x = minute;

            hourBefore = hour_x - 1;
            hourAfter = hour_x + 1;

            if(minute_x >= 0 && minute_x < 10)
            {
                timeField.setText("Time: " + hour_x + ":" + "0" + minute_x);
                timeString = Integer.toString(hour_x) + ":" + "0" + Integer.toString(minute_x) + ":00";
                timeStringAfter = Integer.toString(hourAfter) + ":" + "0" + Integer.toString(minute_x) + ":00";
                timeStringBefore = Integer.toString(hourBefore) + ":" + "0" + Integer.toString(minute_x) + ":00";

                if (hour_x >= 0 && hour_x < 10)
                {
                    timeField.setText("Time: " + "0" + hour_x + ":" + "0" + minute_x);
                    timeString = Integer.toString(hour_x) + ":" + "0" + Integer.toString(minute_x) + ":00";
                    timeStringAfter = Integer.toString(hourAfter) + ":" + "0" + Integer.toString(minute_x) + ":00";
                    timeStringBefore = Integer.toString(hourBefore) + ":" + "0" + Integer.toString(minute_x) + ":00";
                }
            }
            else if(minute_x >= 10)
            {
                if (hour_x >= 0 && hour_x < 10)
                {
                    timeField.setText("Time: " + "0" + hour_x + ":" + minute_x);
                    timeString = Integer.toString(hour_x) + "0" + ":" + Integer.toString(minute_x) + ":00";
                    timeStringAfter = Integer.toString(hourAfter) + "0" + ":" + Integer.toString(minute_x) + ":00";
                    timeStringBefore = Integer.toString(hourBefore) + "0" + ":" + Integer.toString(minute_x) + ":00";
                }
                else
                {
                    timeField.setText("Time: " + hour_x + ":" + minute_x);
                    timeString = Integer.toString(hour_x) + ":" + Integer.toString(minute_x) + ":00";
                    timeStringAfter = Integer.toString(hourAfter) + ":" + Integer.toString(minute_x) + ":00";
                    timeStringBefore = Integer.toString(hourBefore) + ":" + Integer.toString(minute_x) + ":00";
                }
            }
            GetOccupiedLanes();
        }
    };

    public void GetOccupiedLanes()
    {
        String databaseInfo = "hourBefore="+timeStringBefore+"&hourAfter="+timeStringAfter+"&matchDate="+dateString;
        String fileName = "GetOccupiedLanes.php";
        DbConnection b = new DbConnection();
        b.execute(databaseInfo, fileName, "GetLanes");
    }


    static List<String> occupiedLanes;
    static String LANE;

    static void GetLanes(String s)
    {
        String[] lanes = s.split("&");
        occupiedLanes = new ArrayList<String>();

        for(int i = 0; i < lanes.length; ++i){
            try{
                JSONObject root = new JSONObject(lanes[i]);
                JSONObject user_data = root.getJSONObject("user_data");
                LANE = user_data.getString("lane");

                occupiedLanes.add(LANE);
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

    public void SetupMatch(View v)
    {
        Intent matchDetails = new Intent(NewMatchActivity.this, NewMatchDetails.class);
        Intent invitePeople = new Intent(NewMatchActivity.this, InvitePeople.class);

        ParseDate();
        ParseTime();

        if(matchVisibility.equals("Public"))
        {
            StartIntent(matchDetails);
        }
        else
        {
            StartIntent(invitePeople);
        }
    }
    private void StartIntent(Intent i)
    {
        i.putExtra("MATCH_TYPE", matchType);
        i.putExtra("MATCH_DEGREE", matchDegreeString);
        i.putExtra("DATE", matchDate);
        i.putExtra("TIME", matchTime);
        i.putExtra("HOUR_BEFORE", matchTimeBefore);
        i.putExtra("HOUR_AFTER", matchTimeAfter);
        NewMatchDetails.occupiedLanes = occupiedLanes;
        InvitePeople.occupiedLanes = occupiedLanes;

        startActivity(i);
    }

    private void ParseTime()
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        matchTime = timeString;
        matchTimeAfter = timeStringAfter;
        matchTimeBefore = timeStringBefore;
    }

    private void ParseDate()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        matchDate = dateString;
    }
}
