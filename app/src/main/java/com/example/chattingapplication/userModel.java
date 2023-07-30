package com.example.chattingapplication;

public class userModel {
    private String userid,userName,userEmail,userPassword;

    public userModel(){

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public userModel(String userid, String userName, String userEmail, String userPassword) {
        this.userid = userid;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
