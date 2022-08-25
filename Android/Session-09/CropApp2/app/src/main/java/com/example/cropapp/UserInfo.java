package com.example.cropapp;

public class UserInfo {
    private String UserEmail;
    private String UserName;
    private String ImagePath;
    private String BatchCode;

    public UserInfo(){

    }
    public UserInfo(String userEmail, String userName,  String batchCode,String imagePath) {
        UserEmail = userEmail;
        UserName = userName;
        ImagePath = imagePath;
        BatchCode = batchCode;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getBatchCode() {
        return BatchCode;
    }

    public void setBatchCode(String batchCode) {
        BatchCode = batchCode;
    }
}
