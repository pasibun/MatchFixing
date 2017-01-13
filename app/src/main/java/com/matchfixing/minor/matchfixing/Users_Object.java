package com.matchfixing.minor.matchfixing;

import java.util.Date;

/**
 * Created by LaptopPasibun on 27-10-2016.
 */

public class Users_Object {
    public  String userID, fName, lName, email, password, address, city, username, gender, mobilePhone, phone;
    private String dateOfBirth;
    private double scoreSingle, scoreDouble;

    public Users_Object(String userID, String password, String fName, String email, String address,
                        String city, String gender, String dateOfBirth, String username, String mobilePhone,
                        String phone, double scoreSingle, double scoreDouble){
        this.userID = userID;
        this.password = password;
        this.fName = fName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.scoreSingle = scoreSingle;
        this.scoreDouble = scoreDouble;
    }

    public String GetUserID() { return userID; }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getScoreDouble() {
        return scoreDouble;
    }

    public double getScoreSingle() {
        return scoreSingle;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPassword() {
        return password;
    }

    public String getgender() {
        return gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setScoreDouble(double scoreDouble) {
        this.scoreDouble = scoreDouble;
    }

    public void setScoreSingle(double scoreSingle) {
        this.scoreSingle = scoreSingle;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
