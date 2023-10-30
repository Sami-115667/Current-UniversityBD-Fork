package com.techtravelcoder.universitybd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybd.R;

public class DetailsNewsActivity extends AppCompatActivity {


    TextView author,title,desc,date ;
    ImageView image ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);

        image=findViewById(R.id.detailsNewsImageId);
        author=findViewById(R.id.detailsNewsAuthorId);
        title=findViewById(R.id.detailsNewsTitleId);
        desc=findViewById(R.id.detailsNewsDescId);
        date=findViewById(R.id.detailsNewsDateId);

        String c_author=getIntent().getStringExtra("author");
        String c_title=getIntent().getStringExtra("title");
        String c_desc=getIntent().getStringExtra("desc");
        String c_date=getIntent().getStringExtra("date");
        String c_image=getIntent().getStringExtra("image");

        Glide.with(getApplicationContext()).load(c_image).into(image);

        if (c_author != null) {
            author.setText(c_author);
        }

        if (c_title != null) {
            title.setText(c_title);
        }

        if (c_desc != null) {
            desc.setText(c_desc);
        }

        if (c_date != null) {
            date.setText(c_date);
        }

        if (c_image != null) {
            Glide.with(getApplicationContext()).load(c_image).into(image);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}