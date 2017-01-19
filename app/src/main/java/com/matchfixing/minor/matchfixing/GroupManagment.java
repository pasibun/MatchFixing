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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupManagment extends Activity{
    private Context ctx;
    private EditText groupName;
    private GridView personListGrid;
    private Spinner SearchPersonDropdown;
    private String selectedPerson;
    private String selectedPersonRemove;
    private List<String> selectedPersons;
    static Groups_Object go = null;

    private int previousSelectedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_creation);
        ctx=this;
        selectedPersons = new ArrayList<>();
        SearchPersonDropdown = (Spinner) findViewById(R.id.SearchPersonDropdown);
        groupName = (EditText) findViewById(R.id.GroupNameText);
        personListGrid = (GridView) findViewById(R.id.PersonListGrid);
        fillSpinnerList();
        if(go != null)
            editGroup();
    }

    public void home_home(View view){
        startActivity(new Intent(this, Home.class));
    }

    private void editGroup(){
        groupName.setText(go.getGroupName().toString());
        selectedPersons = go.getGroupinvite();
        for(int i = 0; i < selectedPersons.size(); i++){
            if (selectedPersons.get(i).equals(Login.user.getUsername())){
                selectedPersons.remove(i);
            }
        }
        SetupView();
    }

    public void SetupView(){
        if(!selectedPersons.isEmpty()) {
            final GridView gv = (GridView) findViewById(R.id.PersonListGrid);
            gv.setAdapter(new GridViewAdapter(GroupManagment.this, selectedPersons) {
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

                    selectedPersonRemove = selectedPersons.get(position);

                    // If there is a previous selected view exists
                    if (previousSelectedPosition != -1 && selectedPersons.size() > 1 && position != previousSelectedPosition) {
                        previousSelectedView.setSelected(false);

                        previousSelectedView.setTextColor(Color.WHITE);
                    }
                    previousSelectedPosition = position;
                }
            });
        }
    }

    public void fillSpinnerList(){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_activity,Groups.personList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_activity);
        SearchPersonDropdown.setAdapter(spinnerArrayAdapter);
        SearchPersonDropdown.setClickable(true);
        SearchPersonDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPerson = Groups.personList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void removePerson_removePerson(View View){
        if(previousSelectedPosition != -1) {
            selectedPersons.remove(previousSelectedPosition);
            SetupView();
        }else Toast.makeText(ctx, "Selecteer een persoon.", Toast.LENGTH_SHORT).show();
    }

    public void addPerson_addPerson(View View){
        if(!selectedPerson.equals("") || !selectedPerson.equals(null)) {
            if(selectedPersons.size()>= 1 && !selectedPerson.equals(PersonaliaSingleton.getInstance().GetUsername())) {
                for (String s : selectedPersons) {
                    if (!s.equals(selectedPerson) && selectedPersons.size() < 15) {
                        selectedPersons.add(selectedPerson);
                        SetupView();
                        break;
                    } else
                        Toast.makeText(ctx, "Persoon is al toegevoegt", Toast.LENGTH_SHORT).show();
                }
            }else{
                selectedPersons.add(selectedPerson);
                SetupView();
            }
        }
    }

    public void saveGroup_saveGroup(View view) {
        String name = PersonaliaSingleton.getInstance().GetUsername();
        if (!groupName.getText().toString().equals("") && selectedPersons.size() >= 1 ||
                !groupName.getText().toString().equals(null) && selectedPersons.size() >= 1){
            DbConnection b = new DbConnection();
            String databaseInfo = "username=" +  name + "&groupname=" + groupName.getText().toString();
            int count=1;
            for(String s : selectedPersons) {
                if(!s.equals(name))
                    databaseInfo = databaseInfo + "&member" + count +"="+  s;
                count++;
            }
            String file = "SaveGroup.php";
            String export = "";
            b.execute(databaseInfo, file, export);

            b = new DbConnection();
            databaseInfo = "username=" + name;
            file = "GetGroups.php";
            export = "GetGroup";
            b.execute(databaseInfo, file, export);

            startActivity(new Intent(this, Home.class));
        }else Toast.makeText(ctx, "Voer een groepsnaam in!", Toast.LENGTH_SHORT).show();
    }

    public void cancelGroup_cancelGroup(View view) {
        startActivity(new Intent(this, Home.class));
    }
}
