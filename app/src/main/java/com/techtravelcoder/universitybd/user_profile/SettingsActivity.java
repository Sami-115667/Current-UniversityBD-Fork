package com.techtravelcoder.universitybd.user_profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.navigation.NavigationView;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.MainActivity;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingsActivity extends AppCompatActivity {

    int defaultColor;
    LinearLayout navColor;
    LinearLayout navHeaderColor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        navColor = findViewById(R.id.navigation_color_change_id);
        navHeaderColor=findViewById(R.id.navigation_headerColor_id);

        navColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

                // Add any data you want to pass to MainActivity
                intent.putExtra("someKey", "1");

                // Start MainActivity
                startActivity(intent);
                finish();
            }
        });
        navHeaderColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

                // Add any data you want to pass to MainActivity
                intent.putExtra("someKey", "2");

                // Start MainActivity
                startActivity(intent);
            }
        });

    }


}
