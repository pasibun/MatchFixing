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

    private String lastName;
    public void SetLastName(String lastName) {this.lastName = lastName;}

    public String GetName()
    {
        return name;
    }

    public String GetLastName(){return lastName;}

    private String address;
    public void SetAddress(String address) {this.address = address;}

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

    private String password;
    public void SetPassword(String password) {this.password = password;}

    public String GetPassword(){return password; }

    private String mobile;
    public void SetMobile(String mobile) {this.mobile = mobile;}

    public String GetMobile(){return mobile; }

    private String phone;
    public void SetPhone(String phone) {this.phone = phone;}

    public String GetPhone(){return phone; }

    private double scoreSingle;
    public void SetSingle(double scoreSingle) {this.scoreSingle = scoreSingle;}

    public double GetSingle(){return scoreSingle; }

    private double scoreDouble;
    public void SetDouble(double scoreDouble) {this.scoreDouble = scoreDouble;}

    public double GetDouble(){return scoreDouble; }
}
