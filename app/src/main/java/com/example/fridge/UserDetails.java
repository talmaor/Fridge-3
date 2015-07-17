package com.example.fridge;

/**
 * Created by ספיר on 02/06/2015.
 */
public class UserDetails {
    private String user;
    private String pass;
    //private int groupId;

    public void setUserName(String userName) {
        user = userName;
    }

    public void setUserPassword(String userPassword) {
        pass = userPassword;
    }

    public String getUserName() {
        return user;
    }

    public String getUserPassword() {
        return pass;
    }
}
