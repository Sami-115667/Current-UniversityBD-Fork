package com.techtravelcoder.universitybd.model;

public class UserModel {

    String userName,userPassword,userEmail,userPhoneNumber,userUniversity,userBloodGroup,userDept,userHall,userRoom,image1,image2,userId;

    boolean visible ;



    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public UserModel(String userName, String userPassword, String userEmail, String userPhoneNumber, String userUniversity, String userBloodGroup, String userDept, String userHall, String userRoom, String image1, String image2) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userUniversity = userUniversity;
        this.userBloodGroup = userBloodGroup;
        this.userDept = userDept;
        this.userHall = userHall;
        this.userRoom = userRoom;
        this.image1 = image1;
        this.image2 = image2;

    }

    public UserModel(String userName, String userPassword, String userEmail, String userPhoneNumber, String userUniversity, String userBloodGroup, String userDept,String userId) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userUniversity = userUniversity;
        this.userBloodGroup = userBloodGroup;
        this.userDept = userDept;
        this.visible=false;
        this.userId=userId;
    }

    public UserModel(String userName, String userPassword, String userEmail, String userPhoneNumber, String userUniversity, String userBloodGroup, String userDept, String userHall, String userRoom) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userUniversity = userUniversity;
        this.userBloodGroup = userBloodGroup;
        this.userDept = userDept;
        this.userHall = userHall;
        this.userRoom = userRoom;
    }


    public UserModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(String userUniversity) {
        this.userUniversity = userUniversity;
    }

    public String getUserBloodGroup() {
        return userBloodGroup;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        this.userBloodGroup = userBloodGroup;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getUserHall() {
        return userHall;
    }

    public void setUserHall(String userHall) {
        this.userHall = userHall;
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userName='" + userName.trim() + '\'' +
                ", userUniversity='" + userUniversity.trim() + '\'' +
                '}';
    }}
