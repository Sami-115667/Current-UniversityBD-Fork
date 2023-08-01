package com.techtravelcoder.universitybd.model;

public class TeacherInfoModel {

    String name,department,email,phone,image ;

    public TeacherInfoModel(){

    }

    public TeacherInfoModel(String name, String department, String email, String phone, String image) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
