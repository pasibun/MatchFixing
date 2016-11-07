package com.matchfixing.minor.matchfixing;

/**
 * Created by jylti on 22-9-2016.
 * Edited by Matthijs
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends Activity {

    String name;
    TextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void profile_profile(View view) {
        startActivity(new Intent(this, Profile.class));
    }

    public void calender_calender(View view) {
        startActivity(new Intent(this, MatchCalendar.class));
    }

    public void invites_invites(View view) {
        startActivity(new Intent(this, Invitation.class));
    }

    public void group_group(View view) {
        startActivity(new Intent(this, Group.class));
    }

    public void matchmaker_matchmaker(View view) {
        startActivity(new Intent(this, NewMatchActivity.class));
    }
}
