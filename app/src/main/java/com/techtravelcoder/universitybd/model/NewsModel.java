package com.techtravelcoder.universitybd.model;



public class NewsModel {


    String author,title,description,date,image,uid,key,category;
    String userPic,userName,userUniversity;


    public NewsModel() {
        // Default constructor required for Firebase
    }


    public NewsModel(String image1, String userName, String userUniversity) {
        userPic=image1;
        this.userName=userName;
        this.userUniversity=userUniversity;
    }

    public NewsModel(String s_date, String s_desc, String s_img, String s_title, String s_category, String s_uid, String s_userName, String s_userUni, String s_userPic) {
        date=s_date;
        description=s_desc;
        image=s_img;
        title=s_title;
        category=s_category;
        uid=s_uid;
        userPic=s_userPic;
        userName=s_userName;
        userUniversity=s_userUni;
    }

    public NewsModel(String s_date, String s_desc, String s_img, String s_title, String s_category, String s_uid) {
        date=s_date;
        description=s_desc;
        image=s_img;
        title=s_title;
        category=s_category;
        uid=s_uid;
    }



    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(String userUniversity) {
        this.userUniversity = userUniversity;
    }

//    public NewsModel(String s_name, String s_date, String s_desc, String s_img, String s_title, String s_category, String s_uid) {
//        author=s_name;
//        date=s_date;
//        description=s_desc;
//        image=s_img;
//        title=s_title;
//        category=s_category;
//        uid=s_uid;
//
//    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setUid(String uid) {
        this.uid=uid;
    }
    public String getUid() {
        return uid;
    }

    public void setKey(String key) {
        this.key=key;
    }
    public String getKey() {
        return key;
    }
    public void setCategory(String category) {
        this.category=category;
    }
    public String getCategory() {
        return category;
    }
}
