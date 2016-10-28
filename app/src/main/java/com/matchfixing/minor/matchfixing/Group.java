package com.matchfixing.minor.matchfixing;

/**
 * Created by Matthijs on 28-10-2016.
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Group extends Activity {
    private Context ctx=this;
    private EditText groupNameField;
    private String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupControl);
        groupNameField = (EditText)findViewById(R.id.createGroup);
    }
    //Hier moet nog ff duidelijk een koppeling komen tusse gebruiker en de groep.
    public void group_group(View view) {
        groupName = groupNameField.getText().toString();

        if (groupName != null){
            Groups_Object go = new Groups_Object(groupName);
        }else{
            String errorMsg = "Please enter a group name.";
            Toast.makeText(ctx, errorMsg, Toast.LENGTH_LONG).show();
        }
    }
}
