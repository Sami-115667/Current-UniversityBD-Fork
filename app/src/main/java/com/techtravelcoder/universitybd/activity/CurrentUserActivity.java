package com.techtravelcoder.universitybd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.cgpacalculator.CGPADetailsActivity;
import com.techtravelcoder.universitybd.cgpacalculator.SemesterActivity;

public class CurrentUserActivity extends AppCompatActivity {

    private LinearLayout cgCalc,cgDetails,bmiCalc,writePost,postDetails,timeManageMent,mentalHealth,transctionDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user);

        cgCalc=findViewById(R.id.current_user_cg_calc);
        cgDetails=findViewById(R.id.current_user_cg_details);
        bmiCalc=findViewById(R.id.current_user_bmi_calc);
        writePost=findViewById(R.id.current_user_write_post);
        postDetails=findViewById(R.id.current_user_post_details);
        timeManageMent=findViewById(R.id.current_user_time_management);

        cgCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SemesterActivity.class);
                startActivity(intent);
            }
        });
        cgDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CGPADetailsActivity.class);
                startActivity(intent);
            }
        });


        writePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UserNewsPostActivity.class);
                startActivity(intent);
            }
        });

        postDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SpecificUserNewsPostDetails.class);
                Toast.makeText(CurrentUserActivity.this, ""+FirebaseAuth.getInstance().getCurrentUser().getIdToken(true), Toast.LENGTH_SHORT).show();
                intent.putExtra("postAutherId", FirebaseAuth.getInstance().getCurrentUser().getUid());
                startActivity(intent);
            }
        });


    }
}