package com.matchfixing.minor.matchfixing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    int hour_x, minute_x;

    static final int DIALOG_ID = 0;
    static final int DIALOG_ID2 = 1;

    public String dateString;
    public String timeString;

    String matchDate;
    String matchTime;
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

        //invitedPersonField = (TextView) findViewById(R.id.invitedPerson);

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

            if(minute_x >= 0 && minute_x < 10)
            {
                timeField.setText("Time: " + hour_x + ":" + "0" + minute_x);
                timeString = Integer.toString(hour_x) + ":" + "0" + Integer.toString(minute_x) + ":00";

                if (hour_x >= 0 && hour_x < 10)
                {
                    timeField.setText("Time: " + "0" + hour_x + ":" + "0" + minute_x);
                    timeString = Integer.toString(hour_x) + ":" + "0" + Integer.toString(minute_x) + ":00";
                }
            }
            else if(minute_x >= 10)
            {
                if (hour_x >= 0 && hour_x < 10)
                {
                    timeField.setText("Time: " + "0" + hour_x + ":" + minute_x);
                    timeString = Integer.toString(hour_x) + "0" + ":" + Integer.toString(minute_x) + ":00";
                }
                else
                {
                    timeField.setText("Time: " + hour_x + ":" + minute_x);
                    timeString = Integer.toString(hour_x) + ":" + Integer.toString(minute_x) + ":00";
                }
            }
        }
    };

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

//        invitedPersonName = invitedPersonField.getText().toString();
//
//        ParseDate();
//        ParseTime();
//        String databaseInfo = "matchDate="+matchDate+"&matchTime="+matchTime+"&matchType="+matchType+"&UserID="+invitedPersonName;
//        String fileName = "newMatch.php";
//        DbConnection b = new DbConnection();
//        b.execute(databaseInfo, fileName, "NewMatch");
    }
    private void StartIntent(Intent i)
    {
        i.putExtra("MATCH_TYPE", matchType);
        i.putExtra("MATCH_DEGREE", matchDegreeString);
        i.putExtra("DATE", matchDate);
        i.putExtra("TIME", matchTime);

        startActivity(i);
    }

    private void ParseTime()
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        matchTime = timeString;
    }

    private void ParseDate()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        matchDate = dateString;
    }

//    class BackGround extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String matchDate = params[0];
//            String matchTime = params[1];
//            String matchType = params[2];
//            String data="";
//            int tmp;
//
//            try {
//                URL url = new URL("http://141.252.224.181:80/newMatch.php");
//                String urlParams = "matchDate="+matchDate+"&matchTime="+matchTime+"&matchType="+matchType;
//
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setRequestMethod("POST");
//
//                OutputStream os = httpURLConnection.getOutputStream();
//                os.write(urlParams.getBytes());
//                os.flush();
//                os.close();
//
//                InputStream is = httpURLConnection.getInputStream();
//
//                while ((tmp = is.read()) != -1) {
//                    data += (char) tmp;
//                }
//
//                is.close();
//
//                httpURLConnection.disconnect();
//
//                return data;
//
//            } catch (Exception e) {
//                return new String("Exception: " + e.getMessage());
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//            s="Data saved successfully.";
//            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
//        }
//    }
}
