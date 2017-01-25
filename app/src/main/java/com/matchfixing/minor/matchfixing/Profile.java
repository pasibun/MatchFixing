package com.matchfixing.minor.matchfixing;

/**
 * Created by jylti on 22-9-2016.
 * Edited by Matthijs
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends Activity {

    static String userID;
    String name, lastname, username, birthDate, gender, email, address, city, phone, mobile;
    double singleScore, doubleScore;
    public static TextView nameTV,userNameTV, dateOfBirthTV, genderTV, emailTV, adressTV, cityTV, phoneTV, mobileTV, singleScoreTV, doubleScoreTV;
    public static Bundle instances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        this.instances = savedInstanceState;

        userID = PersonaliaSingleton.getInstance().getUserID();
        InitializeLabels();
        SetUserInfo();
    }

    public void home_home1(View view){
        startActivity(new Intent(this, Home.class));
    }

    public void edit_Profile(View view){startActivity(new Intent(this, EditProfile.class));}

    public void InitializeLabels()
    {
        nameTV = (TextView) findViewById(R.id.name_field);
        userNameTV = (TextView) findViewById(R.id.username_field);
        dateOfBirthTV = (TextView) findViewById(R.id.birthdate_field);
        genderTV = (TextView) findViewById(R.id.gender_field);
        emailTV = (TextView) findViewById(R.id.email_field);
        adressTV = (TextView) findViewById(R.id.adress_field);
        cityTV = (TextView) findViewById(R.id.city_field);
        phoneTV = (TextView) findViewById(R.id.phone_field);
        mobileTV = (TextView) findViewById(R.id.mobilephone_field);
        singleScoreTV = (TextView) findViewById(R.id.singlescore_field);
        doubleScoreTV = (TextView) findViewById(R.id.doublescore_field);
    }

    private void SetLabels()
    {
        nameTV.setText(nameTV.getText() + " " +  name + " " + lastname);
        userNameTV.setText(userNameTV.getText() + " " +  username);
        dateOfBirthTV.setText(dateOfBirthTV.getText() + ": " +  birthDate);
        genderTV.setText(genderTV.getText() + " " +  gender);
        emailTV.setText(emailTV.getText() + " " +  email);
        adressTV.setText(adressTV.getText() + " " +  address);
        cityTV.setText(cityTV.getText() + " " +  city);
        phoneTV.setText(phoneTV.getText() + " " +  phone);
        mobileTV.setText(mobileTV.getText() + " " +  mobile);
        singleScoreTV.setText(singleScoreTV.getText() + " " +  Double.toString(singleScore));
        doubleScoreTV.setText(doubleScoreTV.getText() + " " +  Double.toString(doubleScore));
    }

    public void SetUserInfo()
    {
        name = PersonaliaSingleton.getInstance().GetName();
        lastname = PersonaliaSingleton.getInstance().GetLastName();
        email = PersonaliaSingleton.getInstance().GetEmail();
        address = PersonaliaSingleton.getInstance().GetAddress();
        city = PersonaliaSingleton.getInstance().GetCity();
        gender = PersonaliaSingleton.getInstance().GetGender();
        birthDate = PersonaliaSingleton.getInstance().GetBirth();
        username = PersonaliaSingleton.getInstance().GetUsername();
        mobile = PersonaliaSingleton.getInstance().GetMobile();
        phone = PersonaliaSingleton.getInstance().GetPhone();
        singleScore = PersonaliaSingleton.getInstance().GetSingle();
        doubleScore = PersonaliaSingleton.getInstance().GetDouble();
        SetLabels();
    }
}
