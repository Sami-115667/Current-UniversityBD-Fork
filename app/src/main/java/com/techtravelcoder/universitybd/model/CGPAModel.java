package com.techtravelcoder.universitybd.model;

public class CGPAModel {
    private String currentSemester;
    private double totalGradePoint;
    private double totalCredit ;
    private double currentCGPA;

    public CGPAModel(){

    }

    public CGPAModel(String currentSemester, double totalGradePoint, double totalCredit, double currentCGPA) {
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
}
