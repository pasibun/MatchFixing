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
        //startActivity(new Intent(this, Home.class));
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










//class BackGround extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String name = params[0];
//            String password = params[1];
//            String data="";
//            int tmp;
//
//            try {
//                URL url = new URL("http://141.252.218.197:80/login.php");
//                String urlParams = "name="+name+"&password="+password;
//
//                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//                httpURLConnection.setDoOutput(true);
//                OutputStream os = httpURLConnection.getOutputStream();
//                os.write(urlParams.getBytes());
//                os.flush();
//                os.close();
//
//                InputStream is = httpURLConnection.getInputStream();
//                while((tmp=is.read())!=-1){
//                    data+= (char)tmp;
//                }
//
//                is.close();
//                httpURLConnection.disconnect();
//
//                return data;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return "Exception: "+e.getMessage();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "Exception: "+e.getMessage();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            String err = null;
//                try {
//                    JSONObject root = new JSONObject(s);
//                    JSONObject user_data = root.getJSONObject("user_data");
//                    NAME = user_data.getString("name");
//                    PASSWORD = user_data.getString("password");
//                    AGE = user_data.getString("age");
//                    KALIBER = user_data.getString("kaliber");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    err = "Exception: " + e.getMessage();
//                }
//
//            Intent i = new Intent(ctx, Home.class);
//            i.putExtra("name", NAME);
//            i.putExtra("password", PASSWORD);
//            i.putExtra("age", AGE);
//            i.putExtra("kaliber", KALIBER);
//            i.putExtra("err", err);
//            startActivity(i);
//        }
//    }
