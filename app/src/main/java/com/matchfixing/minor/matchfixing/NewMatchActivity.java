package com.matchfixing.minor.matchfixing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
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

    TextView dateField;
    TextView timeField;
    TextView matchTypeField;

    int year_x, month_x, day_x;
    int hour_x, minute_x;

    static final int DIALOG_ID = 0;
    static final int DIALOG_ID2 = 1;

    public String dateString;
    public String timeString;

    Date matchDate;
    Date matchTime;
    String matchType;

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
        SetSpinner();
    }

    private void SetSpinner()
    {
        matchTypeSpinner = (Spinner)findViewById(R.id.spinner);
        matchTypeField = (TextView) findViewById(R.id.matchType);

        final String[] matchTypes = new String[]{
                "Single",
                "Double",
                "Tournament"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_activity,matchTypes);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_activity);
        matchTypeSpinner.setAdapter(spinnerArrayAdapter);

        matchTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matchTypeField.setText("Matchtype: " + matchTypes[position]);
                matchType = matchTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void ShowDialogOnButtonClick()
    {
        btn = (Button)findViewById(R.id.button);

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
        button_stpd = (Button)findViewById(R.id.button2);

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
                    timeString = Integer.toString(hour_x) + ":" + "0" + Integer.toString(minute_x) + ":00";
                }
                else
                {
                    timeField.setText("Time: " + hour_x + ":" + minute_x);
                    timeString = Integer.toString(hour_x) + ":" + "0" + Integer.toString(minute_x) + ":00";
                }
            }
        }
    };

    public void SetupMatch(View v)
    {
        ParseDate();
        ParseTime();
        String databaseInfo = "matchDate="+matchDate+"&matchTime="+matchTime+"&matchType="+matchType;
        String fileName = "newMatch.php";

        DbConnection b = new DbConnection();
        b.inputDatabase(databaseInfo, fileName, null);
    }

    private void ParseTime()
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            matchTime = format.parse(timeString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void ParseDate()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            matchDate = format.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
