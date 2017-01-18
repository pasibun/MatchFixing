package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    List<String> groups;
    Map<String, Integer> userIDs;
    Map<String, Integer> groupIDs;

    private int previousSelectedPosition = -1;
    public static Activity mActivity;

    String invitationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_group_list);

        invitationType = getIntent().getStringExtra("Invitation");

        users = new ArrayList<String>();
        userIDs = new HashMap<String, Integer>();

        groups = new ArrayList<String>();
        groupIDs = new HashMap<String, Integer>();

        mActivity = this;

        userGrid = (GridView) findViewById(R.id.gridView);

        SetupView();

        User_Group_Invite.BackGround b = new User_Group_Invite.BackGround();
        b.execute();
    }

    public void SetupView()
    {
        final GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new GridViewAdapter(User_Group_Invite.this, users){
            public View getView(int position, View convertView, ViewGroup parent){
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
                int clickedUserID = userIDs.get(users.get(position));

                int userID = clickedUserID;

                Intent Popup = new Intent(User_Group_Invite.this, Popup.class);

                if(invitationType.equals("user")) {
                    Popup.putExtra("userID", userID);
                    Popup.putExtra("firstName", FIRSTNAME);
                }else{
                    Popup.putExtra("groupID", userID);
                    Popup.putExtra("groupOwner", GROUPOWNER);
                }
                startActivity(Popup);

                // If there is a previous selected view exists
                if (previousSelectedPosition != -1)
                {
                    previousSelectedView.setSelected(false);

                    previousSelectedView.setTextColor(Color.WHITE);
                }
                previousSelectedPosition = position;
            }
        });
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String data = "";
            int tmp;
            URL url;

            try {
                if(invitationType.equals("user")) {
                    url = new URL("http://141.252.224.166:80/GetProfiles.php");
                }else                {
                    url = new URL("http://141.252.224.166:80/GetGroups.php");
                }

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);

                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }
        @Override
        protected void onPostExecute(String s) {
            String err = null;
            String[] matchStrings = s.split("&");
            int fieldID = -1;

            for(int i = 0; i < matchStrings.length; ++i)
            {
                try {
                    JSONObject root = new JSONObject(matchStrings[i]);
                    JSONObject user_data = root.getJSONObject("user_data");

                    if(invitationType.equals("user")) {
                        USERID = user_data.getString("userID");
                        USERNAME = user_data.getString("userName");
                        FIRSTNAME = user_data.getString("firstName");

                        users.add(USERNAME + " " + FIRSTNAME);
                        userIDs.put(USERNAME + " " + FIRSTNAME, Integer.parseInt(USERID));
                    }
                    else{
                        GROUPID = user_data.getString("groupID");
                        GROUPOWNER = user_data.getString("groupOwner");

                        users.add(GROUPID + " " + GROUPOWNER);
                        userIDs.put(GROUPID + " " + GROUPOWNER, Integer.parseInt(GROUPID));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                }
            }
            SetupView();
        }
    }
}
