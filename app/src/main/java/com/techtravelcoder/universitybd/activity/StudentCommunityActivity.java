package com.techtravelcoder.universitybd.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.fragment.StudentDetailsFragment;
import com.techtravelcoder.universitybd.user_profile.UserProfileActivity;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class StudentCommunityActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_community);

        int color= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getColor(R.color.service_bar);
        }
        getWindow().setStatusBarColor(color);


        bottomNavigationView=findViewById(R.id.bottom_nav_id);
        bottomNavigationView.setSelectedItemId(R.id.menu_connect_id);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.linear, new StudentDetailsFragment()).commit();




    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;

        int itemId = item.getItemId();
        if (itemId == R.id.menu_connect_id) {
            selectedFragment = new StudentDetailsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.linear, selectedFragment).commit();

        }
        if (itemId == R.id.menu_notification_id) {
            Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
        }
        if (itemId == R.id.menu_serch_id) {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        }



//        if (selectedFragment != null ) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.linear, selectedFragment).commit();
//        }
        return true;
    };
}