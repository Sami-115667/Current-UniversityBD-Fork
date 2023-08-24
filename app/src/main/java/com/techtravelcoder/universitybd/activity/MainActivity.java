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
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
        sliderSupport();

        int colorPrimary = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorPrimary = getColor(R.color.service_bar);
        }

        getWindow().setStatusBarColor(colorPrimary);



        managementNavigationDrawerItem();
    }






    public  void sliderSupport(){
        imageSlider=findViewById(R.id.image_slider);
        final List<SlideModel> remoteimages=new ArrayList<>();//Slide model is an inbuilt model class made by github library provider
        FirebaseDatabase.getInstance().getReference().child("Slider")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data:snapshot.getChildren())
                            remoteimages.add(new SlideModel(data.child("url").getValue().toString(),data.child("title").getValue().toString(), ScaleTypes.FIT));

                        imageSlider.setImageList(remoteimages,ScaleTypes.FIT);
                        imageSlider.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                //Toast.makeText(MainActivity.this, ""+remoteimages.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
                                String uni_name = remoteimages.get(i).getTitle().toString().toLowerCase();
                                String abc =remoteimages.get(i).getImageUrl();

                                Intent intent= new Intent(MainActivity.this,ServiceActivity.class);
                                intent.putExtra("name", uni_name);
                                intent.putExtra("imageid", abc);
                                startActivity(intent);

                            }

                            @Override
                            public void doubleClick(int i) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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
                if(item.getItemId()==R.id.developer_info){
                    Intent intent = new Intent(MainActivity.this,TeamMemberActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                if(item.getItemId()==R.id.share_Id){
                    String textToShare = "Check out this awesome link:";
                    String linkToShare = "https://www.example.com";

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");

                    String message = textToShare + "\n" + linkToShare;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, message);

                    // Start the sharing activity
                    startActivity(Intent.createChooser(shareIntent, "Share via"));
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


