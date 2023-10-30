package com.techtravelcoder.universitybd.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.HallServiceModel;

import java.util.Map;

public class HallDetailsActivity extends AppCompatActivity {

    ImageView imgMain, img2, img3, img4, img5;
    TextView hallName, uniName, text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_details);

        imgMain = findViewById(R.id.detailsHallImageId);
        img2 = findViewById(R.id.iv_img2);
        img3 = findViewById(R.id.iv_img3);
        img4 = findViewById(R.id.iv_img4);
        img5 = findViewById(R.id.iv_img5);
        text1 = findViewById(R.id.tv_text1);
        text2 = findViewById(R.id.tv_text2);
        text3 = findViewById(R.id.tv_text3);
        text4 = findViewById(R.id.tv_text4);

        Intent intent = getIntent();
        HallServiceModel selectedItem = (HallServiceModel) intent.getSerializableExtra("selectedItem");

        if (selectedItem != null) {
            // Populate your views with data from the selectedItem

            text1.setText(selectedItem.getText1());
            text2.setText(selectedItem.getText2());
            text3.setText(selectedItem.getText3());
            text4.setText(selectedItem.getText4());

            // Load images using Glide or other image loading libraries
            loadImages(selectedItem.getImages());
        }


    }

    private void loadImages(Map<String, String> images) {
        // Load each image using Glide or another image loading library
        loadImageWithGlide(images.get("image1"), imgMain);
        loadImageWithGlide(images.get("image2"), img2);
        loadImageWithGlide(images.get("image3"), img3);
        loadImageWithGlide(images.get("image4"), img4);
        loadImageWithGlide(images.get("image5"), img5);
    }

    private void loadImageWithGlide(String imageUrl, ImageView imageView) {
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(imageView);
        }
    }
}