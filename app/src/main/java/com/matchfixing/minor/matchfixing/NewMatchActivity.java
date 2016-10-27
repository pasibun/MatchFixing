package com.matchfixing.minor.matchfixing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import java.util.Calendar;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                if (hour_x >= 0 && hour_x < 10)
                    timeField.setText("Time: " + "0" + hour_x + ":" + "0" + minute_x);
            }
            else if(minute_x >= 10)
            {
                if (hour_x >= 0 && hour_x < 10)
                    timeField.setText("Time: " + "0" + hour_x + ":" + minute_x);
                else
                    timeField.setText("Time: " + hour_x + ":" + minute_x);
            }
        }
    };
}
