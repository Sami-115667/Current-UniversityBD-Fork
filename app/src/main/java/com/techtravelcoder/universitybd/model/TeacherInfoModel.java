package com.techtravelcoder.universitybd.model;

public class TeacherInfoModel {

    String dept, description, gmail, image, name, phone;

    public TeacherInfoModel(String dept, String description, String gmail, String image, String name, String phone) {
        this.dept = dept;
        this.description = description;
        this.gmail = gmail;
        this.image = image;
        this.name = name;
        this.phone = phone;
    }

    public TeacherInfoModel(){

    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
