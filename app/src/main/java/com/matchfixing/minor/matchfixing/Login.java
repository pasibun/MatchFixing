package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {

    EditText name, password;
    static String Name, Password;
    Context ctx=this;
    private static Context mContext;
    static Map<String, Integer> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        members = new HashMap<String, Integer>();
        name = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);
        mContext = this;
    }

    public void ButtonOnClick(View view)
    {
        startActivity(new Intent(this, Register.class));
    }

    public void main_login(View v){
        Name = name.getText().toString();
        Password = password.getText().toString();
        DbConnection b = new DbConnection();
        String databaseInfo = "name="+Name+"&password="+Password;
        String file = "login.php";
        String export = "Profile";
        String errormsg = "Voer uw inloggegevens in.";
        if(!Name.matches("") || !Password.matches("")) {
            b.execute(databaseInfo, file, export);
        }else Toast.makeText(ctx, errormsg, Toast.LENGTH_SHORT).show();
    }

    public static void checkLoginInfo(Users_Object us){
        String errormsg = "Uw inloggegevens komt niet overeen.";
        if (us != null){
            if(us.getPassword().matches(Password) && us.getUsername().matches(Name)) {
                Intent login = new Intent(mContext, Home.class);
                mContext.startActivity(login);
            }
        }else Toast.makeText(mContext, errormsg, Toast.LENGTH_SHORT).show();
    }
}