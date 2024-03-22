package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.loginandsignup.UserLoginActivity;
import com.techtravelcoder.universitybd.model.UserModel;
import com.techtravelcoder.universitybd.user_profile.SettingsActivity;
import com.techtravelcoder.universitybd.user_profile.UserProfileActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout; //drawerlayouuut
    LinearLayout contentView;
    static final double END_SCALE=0.7;
    private NavigationView navigationView;//navigation view
    ActionBarDrawerToggle actionBarDrawerToggle; // instead of action bar we use toolbar //ActionBarDrawerToggle
    ImageSlider imageSlider;
    private LinearLayout general ,prv,engineering ,agriculture,national,sat;

    private int defaultColor;
    CardView news ;
    TextView headline ;


    FirebaseAuth auth;
    FirebaseDatabase database;
    TextView profile,userName ;
    CircleImageView userPic;
    int headerDefaultColor ;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_DEFAULT_COLOR = "defaultColor";

    private static final String PREFS_NAME_HEADER = "MyPrefsHeader";
    private static final String KEY_DEFAULT_COLOR_HEADER = "defaultColorHeader";
    View headerView;
    CardView userLayout,searchFriends;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);

        searchFriends=findViewById(R.id.search_friends_id);
        general=findViewById(R.id.general_id);
        prv=findViewById(R.id.private_id);
        sat=findViewById(R.id.sat_id);
        engineering=findViewById(R.id.engineering_id);
        agriculture=findViewById(R.id.agriculture_id);
        national=findViewById(R.id.national_id);
        contentView=findViewById(R.id.content);
        news=findViewById(R.id.cv_news_click_id);
        headline=findViewById(R.id.marque_id);
        headline.setSelected(true);

        userPic=headerView.findViewById(R.id.header_user_iamge);
        userName=headerView.findViewById(R.id.header_user_name);
        userLayout=headerView.findViewById(R.id.header_ll);

        toolbar=findViewById(R.id.tolbar);




        general.setOnClickListener(this);
        prv.setOnClickListener(this);
        sat.setOnClickListener(this);
        engineering.setOnClickListener(this);
        agriculture.setOnClickListener(this);
        national.setOnClickListener(this);
        searchFriends.setOnClickListener(this);


        defaultColor = getStoredColor();
        navigationView.setBackgroundColor(defaultColor);

        headerDefaultColor=getStoredColorHeader();
        userLayout.setCardBackgroundColor(headerDefaultColor);
        toolbar.setBackgroundColor(headerDefaultColor);




        String s= getIntent().getStringExtra("someKey");

        if(s !=null){

            if(s.equals("1")){
                navColorChange(1);
            }
            else if(s.equals("2")){
             //   Toast.makeText(this, "Rakib", Toast.LENGTH_SHORT).show();
                navColorChange(2);
            }

        }





        FirebaseDatabase.getInstance().getReference("User Information").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel userModel=snapshot.getValue(UserModel.class);

                            if(userModel.getUserName() != null){
                                userName.setText(userModel.getUserName());
                            }
                            if(userModel.getImage1() != null){
                                Glide.with(getApplicationContext()).load(userModel.getImage1()).into(userPic);

                            }
                            //Toast.makeText(getApplicationContext(), ""+userModel.getUserName(), Toast.LENGTH_SHORT).show();


                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something wrong",Toasty.LENGTH_SHORT).show();
                    }
                });




        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),NewsActivity.class);
                startActivity(intent);
            }
        });

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, UserProfileActivity.class);
                intent.putExtra("postAutherId",FirebaseAuth.getInstance().getUid());
                startActivity(intent);
            }
        });

        sliderSupport();


        getWindow().setStatusBarColor(headerDefaultColor);

        managementNavigationDrawerItem();

    }

    private void navColorChange(int num) {
        if(num==1){
            AmbilWarnaDialog dialog1 = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    // color is the color selected by the user.
                        saveColor(color);
                        defaultColor = color;
                        navigationView.setBackgroundColor(defaultColor);



                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                    // cancel was selected by the user
                }
            });
            dialog1.show();
        }
        if(num==2){
            AmbilWarnaDialog dialog2 = new AmbilWarnaDialog(this, headerDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    saveColorHeader(color);
                    headerDefaultColor = color;
                    userLayout.setCardBackgroundColor(headerDefaultColor);
                    toolbar.setBackgroundColor(headerDefaultColor);
                    getWindow().setStatusBarColor(headerDefaultColor);



                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                    // cancel was selected by the user
                }
            });
            dialog2.show();

        }

    }

    private void saveColor(int color) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(KEY_DEFAULT_COLOR, color);
        editor.apply();
    }

    private int getStoredColor() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_DEFAULT_COLOR, ContextCompat.getColor(this, R.color.back));
    }

    private void saveColorHeader(int color) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME_HEADER, Context.MODE_PRIVATE).edit();
        editor.putInt(KEY_DEFAULT_COLOR_HEADER, color);
        editor.apply();
    }

    private int getStoredColorHeader() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME_HEADER, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_DEFAULT_COLOR_HEADER, ContextCompat.getColor(this, R.color.allert_back_upper));
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
                                Intent intent=new Intent(getApplicationContext(), ServiceActivity.class);
                                intent.putExtra("name",remoteimages.get(i).getTitle().toString().toLowerCase());
                                intent.putExtra("imageid",remoteimages.get(i).getImageUrl()) ;
                                Toast.makeText(MainActivity.this, ""+remoteimages.get(i).getTitle(), Toast.LENGTH_SHORT).show();
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
        alertObj.setTitle("Confirm Exit...ℹ️");
        alertObj.setMessage("ℹ️ Do you want to Exit this Application ❓❓");

        alertObj.setPositiveButton("✅Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertObj.setNegativeButton("❌No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
            }
        });
        AlertDialog dialog = alertObj.create();
        dialog.show();

        Drawable drawable=ContextCompat.getDrawable(getApplicationContext(),R.drawable.alert_back);
        dialog.getWindow().setBackgroundDrawable(drawable);



    }

    private void setMenuCategoryColor(MenuItem menuItem){
        SpannableString s = new SpannableString(menuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length(), 0);
        menuItem.setTitle(s);
    }

    public void managementNavigationDrawerItem(){

        // Get the menu reference

        drawerLayout = findViewById(R.id.draw_layout);
        androidx.appcompat.widget.Toolbar toolbar =findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);

        Menu menu = navigationView.getMenu();

        MenuItem menuItem = menu.findItem(R.id.uniform_id);
        setMenuCategoryColor(menuItem);

        MenuItem menuItem1 = menu.findItem(R.id.communicate_id);
        setMenuCategoryColor(menuItem1);

        MenuItem menuItem2 = menu.findItem(R.id.others_id);
        setMenuCategoryColor(menuItem2);




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
                    Intent intent=new Intent(MainActivity.this,CurrentUserActivity.class);
                    startActivity(intent);
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
                    logOutConfirm();

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
                if(item.getItemId()==R.id.newsPostId){
                    Intent intent= new Intent(MainActivity.this,UserNewsPostActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.settings_Id){
                    Intent intent= new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                }



                return true;
            }
        });





        //  animateNavigationDrawer();

    }

    private void logOutConfirm() {

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("ℹ️ Logout Confirmation..!");
        builder.setMessage("⚠️ Do you want to logout this application ❓❓");

        builder.setPositiveButton("✅Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                auth=FirebaseAuth.getInstance();
                auth.signOut();
                Intent intent= new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("❌No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
        Drawable drawable=ContextCompat.getDrawable(getApplicationContext(),R.drawable.alert_back);
        dialog.getWindow().setBackgroundDrawable(drawable);




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
        if(v.getId()==R.id.search_friends_id){
            Intent intent= new Intent(MainActivity.this,StudentCommunityActivity.class);
            startActivity(intent);
        }



    }



}


