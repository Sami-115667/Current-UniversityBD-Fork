package com.techtravelcoder.universitybd.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.techtravelcoder.universitybd.R;

public class CGPAHomeActivity extends AppCompatActivity {

    CardView semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpahome);

        semester=findViewById(R.id.semester_id);


        semester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SemesterActivity.class);
                startActivity(intent);

            }
        });
    }
}