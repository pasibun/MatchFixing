package com.matchfixing.minor.matchfixing;

/**
 * Created by Matthijs on 28-10-2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Groups extends Activity {
    private Context ctx = this;
    private GridView GroupListGrid;
    static List<String> personList;
    static List<Groups_Object> groupList;
    private List<String> groupNameList;
    private String selectedGroup;
    private int previousSelectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupcontrol);
        GroupListGrid = (GridView) findViewById(R.id.GroupListGrid);
        personList = new ArrayList<>();
        groupNameList = new ArrayList<>();
        DbConnection b = new DbConnection();
        String file = "GetPersonInfo.php";
        String export = "Group";
        b.execute("", file, export);
        SetupView();
    }


    public void SetupView(){
        groupNameList.clear();
        for(Groups_Object go : groupList) {
            groupNameList.add(go.getGroupName().toString());
        }
        if(!groupNameList.isEmpty()) {
            final GridView gv = (GridView) findViewById(R.id.GroupListGrid);
            gv.setAdapter(new GridViewAdapter(Groups.this, groupNameList) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.WHITE);

                    tv.setBackgroundColor(Color.parseColor("#19ffffff"));
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

                    selectedGroup = groupNameList.get(position);


                    // If there is a previous selected view exists
                    if (previousSelectedPosition != -1 && groupNameList.size() > 1 && position != previousSelectedPosition)
                    {
                        previousSelectedView.setSelected(false);

                        previousSelectedView.setTextColor(Color.WHITE);
                    }
                    previousSelectedPosition = position;
                }
            });
        }
    }


    public void groupNew_groupNew(View view) {
        startActivity(new Intent(this, GroupManagment.class));
    }

    public void groupEdit_groupEdit(View view) {
        if(!selectedGroup.equals(null) || !selectedGroup.equals("")){
            for(Groups_Object go : groupList){
                if(selectedGroup.equals(go.getGroupName().toString())) {
                    GroupManagment.go= go;
                    startActivity(new Intent(this, GroupManagment.class));
                }
            }
        }
    }

    public void groupRemove_groupRemove(View view) {
        if (!selectedGroup.isEmpty()){
            for(int i = 0; i < groupList.size(); i++){
                if(selectedGroup.equals(groupList.get(i).getGroupName().toString())){
                    groupList.remove(i);
                    DbConnection b = new DbConnection();
                    String databaseInfo = "groupname=" +  selectedGroup;
                    String file = "RemoveGroup.php";
                    String export = "";
                    b.execute(databaseInfo, file, export);
                }
            }
            SetupView();
        }
    }
}
