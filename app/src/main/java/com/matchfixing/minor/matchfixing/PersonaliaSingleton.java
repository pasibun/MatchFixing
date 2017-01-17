package com.matchfixing.minor.matchfixing;

/**
 * Created by jylti on 12-1-2017.
 */

public class PersonaliaSingleton {

    private static PersonaliaSingleton instance = null;
    private PersonaliaSingleton() {
        // Exists only to defeat instantiation.
    }

    public static PersonaliaSingleton getInstance() {
        if(instance == null) {
            instance = new PersonaliaSingleton();
        }
        return instance;
    }

    private String userID;
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getUserID()
    {
        return userID;
    }

    private String email;
    public void SetEmail(String email) {this.email = email;}

    public String GetEmail(){return email; }

    private String name;
    public void SetName(String name) {
        this.name = name;
    }

    public String GetName()
    {
        return name;
    }

    private String address;
    public void SetAddress(String adress) {this.address = address;}

    public String GetAddress(){return address; }

    private String city;
    public void SetCity(String city) {this.city = city;}

    public String GetCity(){return city; }

    private String gender;
    public void SetGender(String gender) {this.gender = gender;}

    public String GetGender(){return gender; }

    private String dateOfBirth;
    public void SetBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public String GetBirth(){return dateOfBirth; }

    private String username;
    public void SetUsername(String username) {this.username = username;}

    public String GetUsername(){return username; }

    private String mobile;
    public void SetMobile(String mobile) {this.mobile = mobile;}

    public String GetMobile(){return mobile; }

    private String phone;
    public void SetPhone(String phone) {this.phone = phone;}

    public String GetPhone(){return phone; }

    private Double scoreSingle;
    public void SetSingle(Double scoreSingle) {this.scoreSingle = scoreSingle;}

    public Double GetSingle(){return scoreSingle; }

    private Double scoreDouble;
    public void SetDouble(Double scoreDouble) {this.scoreDouble = scoreDouble;}

    public Double GetDouble(){return scoreDouble; }
}
