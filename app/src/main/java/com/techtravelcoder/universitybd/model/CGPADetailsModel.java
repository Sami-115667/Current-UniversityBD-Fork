package com.techtravelcoder.universitybd.model;

public class CGPADetailsModel {
    private String currentSemester;
    private double totalGradePoint;
    private double totalCredit ;
    private double currentCGPA;

    String uid,key ;
    public CGPADetailsModel(){

    }

    public CGPADetailsModel(double currentCGPA,String currentSemester,  double totalCredit,double totalGradePoint) {
        this.currentSemester = currentSemester;
        this.totalGradePoint = totalGradePoint;
        this.totalCredit = totalCredit;
        this.currentCGPA = currentCGPA;

    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public double getTotalGradePoint() {
        return totalGradePoint;
    }

    public void setTotalGradePoint(double totalGradePoint) {
        this.totalGradePoint = totalGradePoint;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public double getCurrentCGPA() {
        return currentCGPA;
    }

    public void setCurrentCGPA(double currentCGPA) {
        this.currentCGPA = currentCGPA;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
