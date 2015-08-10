package com.example.fridge;

import java.io.Serializable;


public class UserDetails implements Serializable {
    private String user;
    private String pass;
    private String userID;
    private String groupID;

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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
