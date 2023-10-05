package com.techtravelcoder.universitybd.user_profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.SpecificUserNewsPostDetails;
import com.techtravelcoder.universitybd.cgpacalculator.SemesterActivity;

public class UserProfileActivity extends AppCompatActivity {

    private CardView cardView,calcCg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        cardView=findViewById(R.id.ll_specific_post);
        calcCg=findViewById(R.id.cgpaCalc);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this, SpecificUserNewsPostDetails.class);
                startActivity(intent);
            }
        });
        calcCg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this, SemesterActivity.class);
                startActivity(intent);
            }
        });

    }
}