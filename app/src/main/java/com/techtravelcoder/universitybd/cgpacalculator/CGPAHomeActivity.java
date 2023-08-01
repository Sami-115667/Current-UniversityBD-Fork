package com.techtravelcoder.universitybd.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techtravelcoder.universitybd.R;

public class CGPAHomeActivity extends AppCompatActivity {

    CardView course,semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpahome);

        course=findViewById(R.id.course_id);
        semester=findViewById(R.id.semester_id);

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CourseActivity.class);
                startActivity(intent);
            }
        });

        semester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}