package com.library.app.model;

import com.google.gson.annotations.SerializedName;

public class UserMD {
    @SerializedName("userName")
    private String userName;
    @SerializedName("passWord")

    private String passWord;
    @SerializedName("fullName")

    private String fullName;
    @SerializedName("phoneNumber")

    private String phoneNumber;
    @SerializedName("email")

    private String email;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
