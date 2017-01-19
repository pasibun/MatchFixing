package com.matchfixing.minor.matchfixing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;

/**
 * Created by jylti on 28-10-2016.
 */
public class MatchCalendar extends AppCompatActivity {
    CalendarView simpleCalendarView;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarview);

        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds
        simpleCalendarView.setDate(selectedDate); // set todays date in milliseconds

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView simpleCalendarView, int year, int month, int day) {

                int actualMonth = month + 1;//month starts at 0, but we want January to be 1.

                Intent newIntent = new Intent(ctx, MatchesToday.class);
                if(day >= 0 && day < 10)
                    newIntent.putExtra("day", "0" + Integer.toString(day));
                else
                    newIntent.putExtra("day", Integer.toString(day));

                if(actualMonth > 0 && actualMonth < 10)
                    newIntent.putExtra("month", "0" + Integer.toString(actualMonth));
                else
                    newIntent.putExtra("month", Integer.toString(actualMonth));

                newIntent.putExtra("year", Integer.toString(year));
                startActivity(newIntent);

                //Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + day + "\n" + "Month = " + month + "\n" + "Year = " + year, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void home_home(View view){
        startActivity(new Intent(this, Home.class));
    }

}
