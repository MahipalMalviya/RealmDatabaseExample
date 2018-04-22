package com.example.realmdatabaseexample;

import io.realm.RealmObject;

public class User extends RealmObject {

    private String userName;
    private String emailId;
    private String phoneNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
