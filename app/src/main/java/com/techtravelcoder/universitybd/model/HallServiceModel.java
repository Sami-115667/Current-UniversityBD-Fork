package com.techtravelcoder.universitybd.model;

import com.techtravelcoder.universitybd.service.HallServiceActivity;

import java.io.Serializable;
import java.util.Map;

public class HallServiceModel implements Serializable {

    private String text1,text2,text3,text4,collectUniName,hallName;
    private Map<String,String> images;

    public HallServiceModel(){

    }

    public HallServiceModel(String text1, String text2, String text3, String text4, String collectUniName, String hallName, Map<String, String> images) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.collectUniName = collectUniName;
        this.hallName = hallName;
        this.images = images;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getCollectUniName() {
        return collectUniName;
    }

    public void setCollectUniName(String collectUniName) {
        this.collectUniName = collectUniName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }
}
