package com.techtravelcoder.universitybd.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.loginandsignup.UserLoginActivity;

public class TeamMemberActivity extends AppCompatActivity {

    AppCompatButton appCompatButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_member);

        appCompatButton=findViewById(R.id.start);

        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivity(intent);
            }
        });


    }
}