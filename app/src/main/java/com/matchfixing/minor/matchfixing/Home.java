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

import java.util.ArrayList;

public class Home extends Activity {

    String name;
    TextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Groups.groupList = new ArrayList<>();
        Groups.personList = new ArrayList<>();
        Groups.personListObject = new ArrayList<>();
        DbConnection b = new DbConnection();
        String databaseInfo = "username=" + Login.user.getUsername();
        String file = "GetGroups.php";
        String export = "GetGroup";
        b.execute(databaseInfo, file, export);

        b = new DbConnection();
        databaseInfo = "username=" +  PersonaliaSingleton.getInstance().GetUsername();
        file = "GetPersonInfo.php";
        export = "Group";
        b.execute(databaseInfo, file, export);
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
        startActivity(new Intent(this, Groups.class));
    }

    public void matchmaker_matchmaker(View view) {
        startActivity(new Intent(this, NewMatchActivity.class));
    }
}
