package com.library.app.model;

public class User {
    private String userCode;
    private String account;
    private String password;
    private String name;

    public User(String userCode, String account, String password, String name) {
        this.userCode = userCode;
        this.account = account;
        this.password = password;
        this.name = name;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
}
