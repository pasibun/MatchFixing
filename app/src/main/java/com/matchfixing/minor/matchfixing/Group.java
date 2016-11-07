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
    private Context ctx = this;
    private EditText groupNameField;
    private String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupcontrol);
        groupNameField = (EditText)findViewById(R.id.textCreateGroup);
    }

    public void group_group(View view) {
        groupName = groupNameField.getText().toString();

        if (groupName != null){
            Groups_Object go = new Groups_Object(groupName);
            String file = "";
            String inputDb = "";
            String export = "";
            DbConnection b = new DbConnection();
            //b.execute(inputDb, file, export);
        }else{
            String msg = "Voer een groepsnaam in.";
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        }
    }
}
