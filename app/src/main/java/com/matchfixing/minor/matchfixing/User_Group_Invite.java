package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jylti on 17-1-2017.
 */

public class User_Group_Invite extends Activity {
    String USERNAME, FIRSTNAME, USERID, GROUPID, GROUPOWNER;

    GridView userGrid;
    List<String> users;
    List<String> usersTemp;
    List<String> names;
    List<String> lastNames;
    List<String> groupNames;
    Map<String, Integer> userIDs;

    private int previousSelectedPosition = -1;
    public static Activity mActivity;

    String invitationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_group_list);

        invitationType = getIntent().getStringExtra("Invitation");

        users = new ArrayList<String>();
        names = new ArrayList<>();
        lastNames = new ArrayList<>();
        usersTemp = new ArrayList<String>();
        userIDs = new HashMap<String, Integer>();
        groupNames = new ArrayList<>();

        mActivity = this;

        userGrid = (GridView) findViewById(R.id.gridView);

        SetupView();
    }

    public void home_home(View view) {
        startActivity(new Intent(this, Home.class));
    }

    public void SetupView() {
        if (!invitationType.equals("user") && users.isEmpty()) {
            for (Groups_Object go : Groups.groupList) {
                users.add(go.getGroupName().toString());
                usersTemp.add(go.getGroupID()+ " " + go.getGroupName().toString());
                userIDs.put(go.getGroupID() + " " + go.getGroupName().toString(), go.getGroupID());
                groupNames.add(go.getGroupName());
            }
        } else {
            users = Groups.personList;
            for(Users_Object u : Groups.personListObject) {
                userIDs.put(u.getUsername() + " " + u.getfName(), Integer.parseInt(u.GetUserID()));
                usersTemp.add(u.getUsername() + " " + u.getfName());
                names.add(u.getfName());
                lastNames.add(u.getlName());
            }
        }
        final GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new GridViewAdapter(User_Group_Invite.this, users) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view;
                tv.setTextColor(Color.WHITE);

                tv.setBackgroundColor(Color.parseColor("#23db4e"));
                tv.setTextSize(25);

                return tv;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                tv.setTextColor(Color.RED);

                TextView previousSelectedView = (TextView) gv.getChildAt(previousSelectedPosition);

                //with this method we retrieve the matchID. We need this to implement in the upcoming "MATCH ATTENDEES" table.
                int clickedUserID = userIDs.get(usersTemp.get(position));
                String firstName = "";
                String lastName = "";
                if(names.size() > 0) {
                    firstName = names.get(position);

                    lastName = lastNames.get(position);
                }
                String groupName = "";
                if(groupNames.size() > 0)
                    groupName = groupNames.get(position);

                int userID = clickedUserID;

                Intent Popup = new Intent(User_Group_Invite.this, Popup.class);

                if (invitationType.equals("user")) {
                    Popup.putExtra("userID", userID);
                    Popup.putExtra("firstName", firstName);
                    Popup.putExtra("lastName", lastName);
                } else {
                    Popup.putExtra("groupID", userID);
                    Popup.putExtra("groupOwner", GROUPOWNER);
                    Popup.putExtra("groupName", groupName);
                }
                startActivity(Popup);

                // If there is a previous selected view exists
                if (previousSelectedPosition != -1 && position != previousSelectedPosition) {
                    previousSelectedView.setSelected(false);

                    previousSelectedView.setTextColor(Color.WHITE);
                }
                previousSelectedPosition = position;
            }
        });
    }
}