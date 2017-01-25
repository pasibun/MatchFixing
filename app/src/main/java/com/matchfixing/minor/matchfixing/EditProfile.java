package com.matchfixing.minor.matchfixing;

/**
 * Created by jylti on 22-9-2016.
 * Edited by Matthijs
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends Activity {

    static String userID;
    String name, lastname, username, password, passwordCheck, birthDate, gender, email, address, city, phone, mobile;
    double singleScore, doubleScore;
    public static EditText userNameET, passwordET, passwordCheckET, sureNameET, lastNameET, dateOfBirthET, genderET, emailET, addressET, cityET, phoneET, mobileET, singleScoreET, doubleScoreET;
    public static Bundle instances;
    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        this.instances = savedInstanceState;
        ctx = this;
        userID = PersonaliaSingleton.getInstance().getUserID();
        InitializeLabels();
        SetUserInfo();
    }

    public void save_Profile(View view){
        lastname = lastNameET.getText().toString();
        name = sureNameET.getText().toString();
        email = emailET.getText().toString();
        address = addressET.getText().toString();
        city = cityET.getText().toString();
        gender = genderET.getText().toString();
        birthDate = dateOfBirthET.getText().toString();
        username = userNameET.getText().toString();
        password = passwordET.getText().toString();
        passwordCheck = passwordCheckET.getText().toString();
        mobile = mobileET.getText().toString();
        phone = phoneET.getText().toString();
        singleScore = Double.parseDouble(singleScoreET.getText().toString());
        doubleScore = Double.parseDouble(doubleScoreET.getText().toString());
        if (password.equals(passwordCheck) && !name.equals("") && !username.equals("") && !lastname.equals("") && singleScore > 0 && doubleScore > 0)
            SetSingleInfo();
        else Toast.makeText(ctx, "Voer alle gegevens correct in!", Toast.LENGTH_SHORT).show();
    }

    public void home_home1(View view){
        startActivity(new Intent(this, Home.class));
    }

    private void InitializeLabels()
    {
        userNameET = (EditText)findViewById(R.id.usernameedt);
        passwordET = (EditText)findViewById(R.id.passedt);
        passwordCheckET = (EditText)findViewById(R.id.passcheckedt);
        sureNameET = (EditText)findViewById(R.id.fullnameedt);
        lastNameET = (EditText)findViewById(R.id.surnameedt);
        dateOfBirthET = (EditText)findViewById(R.id.userbirthdateedt);
        genderET = (EditText)findViewById(R.id.usergenderedt);
        emailET = (EditText)findViewById(R.id.UserMailedt);
        addressET = (EditText)findViewById(R.id.UserAdressedt);
        cityET = (EditText)findViewById(R.id.UserCityedt);
        phoneET = (EditText)findViewById(R.id.UserPhoneedt);
        mobileET = (EditText)findViewById(R.id.UserMobileedt);
        singleScoreET = (EditText)findViewById(R.id.UserSingleedt);
        doubleScoreET = (EditText)findViewById(R.id.UserDoubleedt);
    }

    private void SetUserInfo()
    {
        name = PersonaliaSingleton.getInstance().GetName();
        lastname = PersonaliaSingleton.getInstance().GetLastName();
        email = PersonaliaSingleton.getInstance().GetEmail();
        address = PersonaliaSingleton.getInstance().GetAddress();
        city = PersonaliaSingleton.getInstance().GetCity();
        gender = PersonaliaSingleton.getInstance().GetGender();
        birthDate = PersonaliaSingleton.getInstance().GetBirth();
        username = PersonaliaSingleton.getInstance().GetUsername();
        password = PersonaliaSingleton.getInstance().GetPassword();
        mobile = PersonaliaSingleton.getInstance().GetMobile();
        phone = PersonaliaSingleton.getInstance().GetPhone();
        singleScore = PersonaliaSingleton.getInstance().GetSingle();
        doubleScore = PersonaliaSingleton.getInstance().GetDouble();
        SetLabels();
    }

    private void SetLabels()
    {
        userNameET.setText( username);
        passwordET.setText(password);
        passwordCheckET.setText(password);
        sureNameET.setText(name);
        lastNameET.setText(lastname);
        dateOfBirthET.setText(birthDate);
        genderET.setText(gender);
        emailET.setText(email);
        addressET.setText(address);
        cityET.setText(city);
        phoneET.setText(phone);
        mobileET.setText(mobile);
        singleScoreET.setText(Double.toString(singleScore));
        doubleScoreET.setText(Double.toString(doubleScore));
    }

    private void SetSingleInfo()
    {
        PersonaliaSingleton.getInstance().SetName(name);
        PersonaliaSingleton.getInstance().SetLastName(lastname);
        PersonaliaSingleton.getInstance().SetEmail(email);
        PersonaliaSingleton.getInstance().SetAddress(address);
        PersonaliaSingleton.getInstance().SetCity(city);
        PersonaliaSingleton.getInstance().SetGender(gender);
        PersonaliaSingleton.getInstance().SetBirth(birthDate);
        PersonaliaSingleton.getInstance().SetUsername(username);
        PersonaliaSingleton.getInstance().SetPassword(password);
        PersonaliaSingleton.getInstance().SetMobile(mobile);
        PersonaliaSingleton.getInstance().SetPhone(phone);
        PersonaliaSingleton.getInstance().SetSingle(singleScore);
        PersonaliaSingleton.getInstance().SetDouble(doubleScore);
        executeDB();
    }

    private void executeDB(){
        String databaseInput = "username=" + username + "&id=" + PersonaliaSingleton.getInstance().getUserID() +  "&name=" + name + "&lastName=" + lastname + "&password=" + password + "&birthDate=" + birthDate + "&playerGender=" + gender
                + "&email=" + email + "&address=" + address + "&city=" + city + "&phone=" + phone + "&mobile=" + mobile
                + "&singleScore=" + singleScore + "&doubleScore=" + doubleScore;

        String file = "editProfile.php";
        String export = "";
        DbConnection b = new DbConnection();
        b.execute(databaseInput, file, export);
    }
}
