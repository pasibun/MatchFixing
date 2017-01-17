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

public class Login extends Activity{

    EditText name, password;
    static String Name, Password;
    private static Context mContext;
    static Map<String, Integer> members;
    static Users_Object user;

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
        String databaseInfo = "username="+Name+"&password="+Password;
        String file = "Login.php";
        String export = "Profile";
        String errormsg = "Voer uw inloggegevens in.";
        if(!Name.matches("") || !Password.matches("")) {
            b.execute(databaseInfo, file, export);
        }else Toast.makeText(mContext, errormsg, Toast.LENGTH_SHORT).show();
    }

    public static void checkLoginInfo(Users_Object us){
        String errormsg = "Uw inloggegevens komt niet overeen.";
        if (us != null){
            if(us.getPassword().matches(Password) && us.getUsername().matches(Name)) {
                PersonaliaSingleton.getInstance().setUserID(us.GetUserID());
                PersonaliaSingleton.getInstance().SetName(us.getfName());
                PersonaliaSingleton.getInstance().SetEmail(us.getEmail());
                PersonaliaSingleton.getInstance().SetAddress(us.getAddress());
                PersonaliaSingleton.getInstance().SetCity(us.getCity());
                PersonaliaSingleton.getInstance().SetGender(us.getgender());
                PersonaliaSingleton.getInstance().SetBirth(us.getDateOfBirth());
                PersonaliaSingleton.getInstance().SetUsername(us.getUsername());
                PersonaliaSingleton.getInstance().SetMobile(us.getMobilePhone());
                PersonaliaSingleton.getInstance().SetPhone(us.getPhone());
                PersonaliaSingleton.getInstance().SetSingle(us.getScoreSingle());
                PersonaliaSingleton.getInstance().SetDouble(us.getScoreDouble());
                user = us;
                Intent login = new Intent(mContext, Home.class);
                mContext.startActivity(login);
            }
        }else Toast.makeText(mContext, errormsg, Toast.LENGTH_SHORT).show();
    }
}