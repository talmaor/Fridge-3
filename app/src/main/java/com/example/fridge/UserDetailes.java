package com.example.fridge;

/**
 * Created by talmaor on 5/26/15.
 */
public class UserDetailes {
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
