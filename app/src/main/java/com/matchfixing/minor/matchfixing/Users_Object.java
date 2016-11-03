package com.matchfixing.minor.matchfixing;

import java.util.Date;

/**
 * Created by LaptopPasibun on 27-10-2016.
 */

public class Users_Object {
    private String fName, lName, email, password, address, city, sex, username;
    private Date dateOfBirth;
    private int mobilePhone, phone;
    private double scoreSingle, scoreDouble;

    public Users_Object(String fName, String lName, String email, String password, String address,
                        String city, String sex, Date dateOfBirth, String username, int mobilePhone,
                        int phone, double scoreSingle, double scoreDouble){
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.scoreSingle = scoreSingle;
        this.scoreDouble = scoreDouble;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public double getScoreDouble() {
        return scoreDouble;
    }

    public double getScoreSingle() {
        return scoreSingle;
    }

    public int getMobilePhone() {
        return mobilePhone;
    }

    public int getPhone() {
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

    public String getSex() {
        return sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public void setMobilePhone(int mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setScoreDouble(double scoreDouble) {
        this.scoreDouble = scoreDouble;
    }

    public void setScoreSingle(double scoreSingle) {
        this.scoreSingle = scoreSingle;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
