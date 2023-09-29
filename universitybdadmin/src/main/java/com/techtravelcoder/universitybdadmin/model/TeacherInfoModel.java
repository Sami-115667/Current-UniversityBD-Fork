package com.techtravelcoder.universitybdadmin.model;

public class TeacherInfoModel {

    String name ,dept,gmail,phone,description,image ;

    public TeacherInfoModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage(){
        return image;
    }

    public void setImage(){
        this.image=image;
    }

    public TeacherInfoModel(String name, String dept, String gmail, String phone, String description,String image) {
        this.name = name;
        this.dept = dept;
        this.gmail = gmail;
        this.phone = phone;
        this.description = description;
        this.image=image;
    }
}
