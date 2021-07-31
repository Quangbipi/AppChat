package com.quangminh.uiexample02.model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class AccountUser {
    public String account;
    public String password;
    public String name;
    public String dateOfBirth;
    public String phoneNumber;
    public String gender;

    public AccountUser() {
    }

    public AccountUser(String account, String password, String name, String dateOfBirth, String phoneNumber, String gender) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;

        this.gender = gender;
    }

    public boolean isValitk(){
        return !TextUtils.isEmpty(account) && Patterns.EMAIL_ADDRESS.matcher(account).matches();
    }

    public boolean isValimk(){
        return !TextUtils.isEmpty(password) && password.length() >=6;
    }
}
