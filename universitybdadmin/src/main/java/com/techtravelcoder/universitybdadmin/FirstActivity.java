package com.techtravelcoder.universitybdadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techtravelcoder.universitybdadmin.hall.HallActivity;
import com.techtravelcoder.universitybdadmin.newspaper.NewsCategoryActivity;
import com.techtravelcoder.universitybdadmin.newspaper.NewsPostActivity;
import com.techtravelcoder.universitybdadmin.teacherinfo.TeacherInfoActivity;

public class FirstActivity extends AppCompatActivity {

    AppCompatButton news,teacherInfo,hall ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        news=findViewById(R.id.newspaperId);
        teacherInfo=findViewById(R.id.techerInformationId);
        hall=findViewById(R.id.hall_admin_id);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), NewsCategoryActivity.class);
                startActivity(intent);
            }
        });

        teacherInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), TeacherInfoActivity.class);
                startActivity(intent);
            }
        });

        hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), HallActivity.class);
                startActivity(intent);
            }
        });
    }
}