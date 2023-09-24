package com.techtravelcoder.universitybdadmin.model;

public class NewsModel {

    String author,title,description,date,image ;

   public NewsModel(){

   }



    public NewsModel(String author, String date, String description, String image, String title) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;

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
}
