package com.techtravelcoder.universitybd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.fragment.StudentDetailsFragment;
import com.techtravelcoder.universitybd.user_profile.UserProfileActivity;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class StudentCommunityActivity extends AppCompatActivity {


    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_community);

        bottomNavigation=findViewById(R.id.meow_id);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        StudentDetailsFragment obj= new StudentDetailsFragment();
        fragmentTransaction.replace(R.id.linear,obj).commit();



        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.community));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.name_teacherinfo));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.email_teacherinfo));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch(model.getId())
                {
                    case 1:
                        Toast.makeText(StudentCommunityActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:

                        FragmentManager fragmentManager=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        StudentDetailsFragment obj= new StudentDetailsFragment();
                        fragmentTransaction.replace(R.id.linear,obj).commit();
                        break;
                    case 3:
                        FragmentManager fragmentManager1=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
                        StudentDetailsFragment obj1= new StudentDetailsFragment();
                        fragmentTransaction1.replace(R.id.linear,obj1).commit();
                        break;
                }
                return null;
            }
        });


    }
}