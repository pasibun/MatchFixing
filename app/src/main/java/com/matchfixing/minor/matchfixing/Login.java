package com.matchfixing.minor.matchfixing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {

    EditText name, password;
    static String Name, Password;
    Context ctx=this;
    String NAME=null, PASSWORD=null, AGE=null, KALIBER = null, GENDER = null;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
        b.execute(databaseInfo, file, "Profile");
    }

    public static void checkLoginInfo(Users_Object us){
        if (Name.equals(us.getUsername()) && Password.equals(us.getPassword())){
            Intent login = new Intent(mContext, Home.class);
            mContext.startActivity(login);
        }
    }
}