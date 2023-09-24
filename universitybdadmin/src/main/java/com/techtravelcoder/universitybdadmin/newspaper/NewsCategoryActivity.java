package com.techtravelcoder.universitybdadmin.newspaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techtravelcoder.universitybdadmin.R;

public class NewsCategoryActivity extends AppCompatActivity {

    AppCompatButton autoUni,pubUni,PriUni,engiUni,highSikkha,uddokta,chakri,freelancing,remoteJob,trending,udsNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);
        autoUni=findViewById(R.id.autoUniId);
        pubUni=findViewById(R.id.publicUniId);
        PriUni=findViewById(R.id.privateUniId);
        engiUni=findViewById(R.id.engiUniId);
        highSikkha=findViewById(R.id.highId);
        uddokta=findViewById(R.id.uddoktaId);
        chakri=findViewById(R.id.chakriId);
        freelancing=findViewById(R.id.freelancingId);
        remoteJob=findViewById(R.id.remoteJobId);
        trending=findViewById(R.id.trendingId);



        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","Trending News");
                startActivity(intent);
            }
        });

        autoUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","স্বায়ত্তশাসিত বিশ্ববিদ্যালয়");
                startActivity(intent);
            }
        });

        pubUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","সরকারি বিশ্ববিদ্যালয়");
                startActivity(intent);
            }
        });

         PriUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","বেসরকারি বিশ্ববিদ্যালয়");
                startActivity(intent);
            }
        });

        engiUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","প্রকৌশল ও বিজ্ঞান-প্রযুক্তি");
                startActivity(intent);
            }
        });
        highSikkha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","উচ্চ শিক্ষা");
                startActivity(intent);
            }
        });
        uddokta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","উদ্যোক্তা");
                startActivity(intent);
            }
        });
        chakri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","চাকরি");
                startActivity(intent);
            }
        });
        freelancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","ফ্রিল্যান্সিং");
                startActivity(intent);
            }
        });
        remoteJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewsPostActivity.class);
                intent.putExtra("key","রিমোট জব");
                startActivity(intent);
            }
        });


    }
}