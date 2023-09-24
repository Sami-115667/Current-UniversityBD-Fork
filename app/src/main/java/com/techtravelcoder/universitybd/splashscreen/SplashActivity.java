package com.techtravelcoder.universitybd.splashscreen;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.TeamMemberActivity;
import com.techtravelcoder.universitybd.loginandsignup.UserLoginActivity;

public class SplashActivity extends AppCompatActivity {
    private static int spalshscreenTime = 20;
    Animation topAnimation,buttomAnimation;
    ImageView splashImage;
    TextView startNameOfsplashscreen,descriptionOfsplashscreen ;
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);        setContentView(R.layout.activity_splash_screen);
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        buttomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        splashImage = findViewById(R.id.imageViewsplashscreen);
        startNameOfsplashscreen = findViewById(R.id.textView1Splashscreen);
        descriptionOfsplashscreen = findViewById(R.id.textView2Splashscreen);
        splashImage.setAnimation(topAnimation);
        startNameOfsplashscreen.setAnimation(buttomAnimation);
        descriptionOfsplashscreen.setAnimation(buttomAnimation);

        int colorPrimary = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorPrimary = getColor(R.color.back);
        }

        getWindow().setStatusBarColor(colorPrimary);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), TeamMemberActivity.class);
                startActivity(intent);
                finish();            }
        },spalshscreenTime);    }
}