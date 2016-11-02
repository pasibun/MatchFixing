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

        nameTV = (TextView) findViewById(R.id.name_field);

        name = getIntent().getStringExtra("name");

        nameTV.setText("Welcome " + name);
    }

    public void profile_profile(View view) {

        startActivity(new Intent(this, Profile.class));
        DbConnection b = new DbConnection();
        b.inputDatabase(null, null, "Profile");
    }

    public void calender_calender(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void invites_invites(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void group_group(View view) {
        startActivity(new Intent(this, Group.class));
    }

    public void matchmaker_matchmaker(View view) {
        startActivity(new Intent(this, Register.class));
    }
}
