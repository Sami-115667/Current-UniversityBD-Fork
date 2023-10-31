package com.techtravelcoder.universitybd.model;



public class NewsModel {


    String author,title,description,date,image,uid,key,category;


    public NewsModel() {
        // Default constructor required for Firebase
    }

    public NewsModel(String author, String date, String description, String image, String title,String category) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
        this.category=category;

    }

    public NewsModel(String s_name, String s_date, String s_desc, String s_img, String s_title, String s_category, String s_uid) {
        author=s_name;
        date=s_date;
        description=s_desc;
        image=s_img;
        title=s_title;
        category=s_category;
        uid=s_uid;
    }


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
