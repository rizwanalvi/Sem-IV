package com.example.cropapp;

public class User {
    private String FullName=null;
    private  String Emailid = null;
    public User(){


    }
    public User(String fullName, String emailid) {
        FullName = fullName;
        Emailid = emailid;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmailid() {
        return Emailid;
    }

    public void setEmailid(String emailid) {
        Emailid = emailid;
    }
}
