package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.loginandsignup.UserLoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout; //drawerlayouuut
    NavigationView navigationView;//navigation view
    ActionBarDrawerToggle actionBarDrawerToggle; // instead of action bar we use toolbar //ActionBarDrawerToggle
    ImageSlider imageSlider;
    private CardView general ,prv,engineering ,agriculture,national,sat;
    FirebaseAuth auth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        general=findViewById(R.id.general_id);
        prv=findViewById(R.id.private_id);
        sat=findViewById(R.id.sat_id);
        engineering=findViewById(R.id.engineering_id);
        agriculture=findViewById(R.id.agriculture_id);
        national=findViewById(R.id.national_id);


        general.setOnClickListener(this);
        prv.setOnClickListener(this);
        sat.setOnClickListener(this);
        engineering.setOnClickListener(this);
        agriculture.setOnClickListener(this);
        national.setOnClickListener(this);

        int colorPrimary = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorPrimary = getColor(R.color.primary);
        }

        getWindow().setStatusBarColor(colorPrimary);



        managementNavigationDrawerItem();
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder alertObj= new AlertDialog.Builder(MainActivity.this);
        alertObj.setTitle("Confirm Exit...!");
        alertObj.setMessage("Do you want to Exit this Application ?");

        alertObj.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertObj.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
            }
        });
        AlertDialog dialog = alertObj.create();
        alertObj.show();



    }

    public void managementNavigationDrawerItem(){
        drawerLayout = findViewById(R.id.draw_layout);
        navigationView=findViewById(R.id.nav_view);
        androidx.appcompat.widget.Toolbar toolbar =findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.home_Id){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.university_Id){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.general_menu_id){
                     Intent intent=new Intent(MainActivity.this,AllUniversityActivity.class);
                    intent.putExtra("name","general");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.private_menu_id){
                     Intent intent=new Intent(MainActivity.this,AllUniversityActivity.class);
                    intent.putExtra("name","private");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.engineering_menu_id){
                    Intent intent=new Intent(MainActivity.this,AllUniversityActivity.class);
                    intent.putExtra("name","engineering");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.sat_menu_id){
                    Intent intent=new Intent(MainActivity.this,AllUniversityActivity.class);
                    intent.putExtra("name","sat");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.agriculture_menu_id){
                    Intent intent=new Intent(MainActivity.this,AllUniversityActivity.class);
                    intent.putExtra("name","agriculture");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.national_menu_id){
                    Intent intent=new Intent(MainActivity.this,AllUniversityActivity.class);
                    intent.putExtra("name","national");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId()==R.id.log_out_id){
                    auth=FirebaseAuth.getInstance();
                    auth.signOut();
                    Intent intent= new Intent(getApplicationContext(), UserLoginActivity.class);
                    startActivity(intent);

                }




                return true;
            }
        });

    }



    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.general_id){

            Intent intent= new Intent(MainActivity.this,AllUniversityActivity.class);
            intent.putExtra("name","general");
            startActivity(intent);
        }
        if (v.getId() == R.id.private_id){

            Intent intent= new Intent(MainActivity.this,AllUniversityActivity.class);
            intent.putExtra("name","private");
            startActivity(intent);
        }

        if (v.getId() == R.id.sat_id){

            Intent intent= new Intent(MainActivity.this,AllUniversityActivity.class);
            intent.putExtra("name","sat");
            startActivity(intent);
        }

        if (v.getId() == R.id.engineering_id){

            Intent intent= new Intent(MainActivity.this,AllUniversityActivity.class);
            intent.putExtra("name","engineering");
            startActivity(intent);
        }

        if (v.getId() == R.id.agriculture_id){

            Intent intent= new Intent(MainActivity.this,AllUniversityActivity.class);
            intent.putExtra("name","agriculture");
            startActivity(intent);
        }

        if (v.getId() == R.id.national_id){

            Intent intent= new Intent(MainActivity.this,AllUniversityActivity.class);
            intent.putExtra("name","national");
            startActivity(intent);
        }



    }










}


