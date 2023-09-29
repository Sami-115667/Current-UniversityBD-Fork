package com.techtravelcoder.universitybd.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybd.R;

public class TeacherDetailsActivity extends AppCompatActivity {
    TextView name,dept,gmail,phone,description;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        name=findViewById(R.id.dt_name_id);
        dept=findViewById(R.id.dt_dept_id);
        gmail=findViewById(R.id.dt_gmail_id);
        phone=findViewById(R.id.dt_phone_id);
        description=findViewById(R.id.dt_bio_id);
        img=findViewById(R.id.dt_image_id);

        String c_name=getIntent().getStringExtra("name");
        String c_dept=getIntent().getStringExtra("title");
        String c_phone=getIntent().getStringExtra("phone");
        String c_gmail=getIntent().getStringExtra("gmail");
        String c_bio=getIntent().getStringExtra("bio");
        String c_image=getIntent().getStringExtra("image");




        if (c_name != null) {
            name.setText(c_name);
        }

        if (c_dept != null) {
            dept.setText(c_dept);
        }

        if (c_phone != null) {
            phone.setText(c_phone);
        }

        if (c_gmail != null) {
            gmail.setText(c_gmail);
        }

        if (c_image != null) {
            Glide.with(getApplicationContext()).load(c_image).into(img);
        }
        if(c_bio != null){
            description.setText(c_bio);
        }


    }
}