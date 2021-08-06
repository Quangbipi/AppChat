package com.quangminh.uiexample02.model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class AccountUser {
    private String account;
    private String password;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String gender;
    private String userId;

    public AccountUser() {
    }

    public AccountUser(String account, String password, String name, String dateOfBirth, String phoneNumber, String gender, String userId) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.gender = gender;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isValitk(){
        return !TextUtils.isEmpty(account) && Patterns.EMAIL_ADDRESS.matcher(account).matches();
    }

    public boolean isValimk(){
        return !TextUtils.isEmpty(password) && password.length() >=6;
    }
}
