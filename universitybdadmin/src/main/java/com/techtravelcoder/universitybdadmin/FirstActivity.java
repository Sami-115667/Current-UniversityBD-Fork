package com.techtravelcoder.universitybdadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techtravelcoder.universitybdadmin.newspaper.NewsCategoryActivity;
import com.techtravelcoder.universitybdadmin.newspaper.NewsPostActivity;

public class FirstActivity extends AppCompatActivity {

    AppCompatButton news,teacherInfo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        news=findViewById(R.id.newspaperId);
        teacherInfo=findViewById(R.id.techerInformationId);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), NewsCategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}