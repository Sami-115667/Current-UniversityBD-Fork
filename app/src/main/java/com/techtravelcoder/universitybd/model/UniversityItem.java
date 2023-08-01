package com.techtravelcoder.universitybd.model;

public class UniversityItem {

    private int university_image ;
    private String university_name ;

    public UniversityItem(int university_image, String university_name) {
        this.university_image = university_image;
        this.university_name = university_name;
    }

    public int getUniversity_image() {
        return university_image;
    }

    public String getUniversity_name() {
        return university_name;
    }
}
