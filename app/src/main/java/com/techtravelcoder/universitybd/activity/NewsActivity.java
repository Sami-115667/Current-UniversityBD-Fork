package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.NewsAdapter;
import com.techtravelcoder.universitybd.adapter.TeacherInfoAdapter;
import com.techtravelcoder.universitybd.adapter.TrendingNewsAdapter;
import com.techtravelcoder.universitybd.connection.NetworkChangeListener;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;
import com.techtravelcoder.universitybd.model.TrendingNewsModel;
import com.techtravelcoder.universitybd.model.UserModel;
import com.techtravelcoder.universitybd.service.TeachersInfoService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private NewsAdapter newsAdapter;
    private TrendingNewsAdapter trendingNewsAdapter;
    private DatabaseReference mbase,mbase1;
    private ArrayList<NewsModel>list;
    private ArrayList<NewsModel> filteredList;
    private  ArrayList<TrendingNewsModel>list1;
     private TextView category ;
     EditText editText;

     Toolbar toolbar;

    CardView gen_uni,auto_uni,pri_uni,pub_uni,engi_uni,high_study,uddokta,chakri,frellancing,remoteJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        gen_uni=findViewById(R.id.cd1);
        auto_uni=findViewById(R.id.cd2);
        pri_uni=findViewById(R.id.cd3);
        pub_uni=findViewById(R.id.cd4);
        engi_uni=findViewById(R.id.cd5);
        high_study=findViewById(R.id.cd6);
        uddokta=findViewById(R.id.cd7);
        chakri=findViewById(R.id.cd8);
        frellancing=findViewById(R.id.cd9);
        remoteJob=findViewById(R.id.cd10);
        category=findViewById(R.id.tv_category_id);
        editText=findViewById(R.id.edNewsSearch);


        gen_uni.setOnClickListener(this);
        auto_uni.setOnClickListener(this);
        pri_uni.setOnClickListener(this);
        pub_uni.setOnClickListener(this);
        engi_uni.setOnClickListener(this);
        high_study.setOnClickListener(this);
        uddokta.setOnClickListener(this);
        chakri.setOnClickListener(this);
        frellancing.setOnClickListener(this);
        remoteJob.setOnClickListener(this);
        category.setOnClickListener(this);



        int color= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getColor(R.color.service_bar);
        }
        getWindow().setStatusBarColor(color);

        String postUserId=getIntent().getStringExtra("postUserUid");




        toolbar =  findViewById(R.id.tb_news);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Toast.makeText(this, ""+newsModel.getUid(), Toast.LENGTH_SHORT).show();


        //News Paper
        recyclerView=findViewById(R.id.newsRecyclerViewId);
        FirebaseApp.initializeApp(this);

        mbase = FirebaseDatabase.getInstance().getReference("News").child("All News");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        newsAdapter=new NewsAdapter(this,list);
        recyclerView.setAdapter(newsAdapter );

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    NewsModel newsModel = dataSnapshot.getValue(NewsModel.class);

                    if(newsModel != null){

                        list.add(0,newsModel);


                    }


                }

                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //Trending News Paper

        recyclerView1=findViewById(R.id.trendingNewsRecyclerViewId);
        mbase1=FirebaseDatabase.getInstance().getReference("News").child("Trending News");
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        list1=new ArrayList<>();
        trendingNewsAdapter=new TrendingNewsAdapter(this,list1);
        recyclerView1.setAdapter(trendingNewsAdapter );

       // Toast.makeText(getApplicationContext(), "Rakib", Toast.LENGTH_SHORT).show();
        mbase1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list1.clear();

                TrendingNewsModel trendingNewsModel ;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    trendingNewsModel = dataSnapshot.getValue(TrendingNewsModel.class);
                    if (trendingNewsModel != null) {

                        list1.add(0, trendingNewsModel);
                    }
                }
                trendingNewsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //name image university
      //  Toast.makeText(this, ""+newsModel.getUid(), Toast.LENGTH_SHORT).show();






        filteredList = new ArrayList<>(list);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }

    private void filter(String text){
        List<NewsModel> filterList= new ArrayList<>();
        for(NewsModel obj : list ){
            if(obj.getTitle().toLowerCase().contains(text.toLowerCase())){
                filterList.add(obj);
            }
        }
        newsAdapter.filterList((ArrayList<NewsModel>) filterList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.newspaper,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_news_id){
            Intent intent = new Intent(NewsActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void retriveCommonPart(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        newsAdapter=new NewsAdapter(this,list);
        recyclerView.setAdapter(newsAdapter );



        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Toast.makeText(NewsActivity.this, "Ana", Toast.LENGTH_SHORT).show();

                    NewsModel newsModel = dataSnapshot.getValue(NewsModel.class);
                    if(newsModel != null){

                        list.add(0,newsModel);
                    }

                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }

                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.cd1){
            category.setText("All News");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("All News");
            retriveCommonPart();
        }
        if(v.getId()==R.id.cd2){
            category.setText("স্বায়ত্তশাসিত বিশ্ববিদ্যালয়");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("স্বায়ত্তশাসিত বিশ্ববিদ্যালয়");
            retriveCommonPart();




        }
        if(v.getId()==R.id.cd3){

            category.setText("সরকারি বিশ্ববিদ্যালয়");

            // Toast.makeText(this, "Rakib", Toast.LENGTH_SHORT).show();
            mbase = FirebaseDatabase.getInstance().getReference("News").child("সরকারি বিশ্ববিদ্যালয়");
            retriveCommonPart();


        }
        if(v.getId()==R.id.cd4){
            category.setText("বেসরকারি বিশ্ববিদ্যালয়");
            //Toast.makeText(this, "Rakib", Toast.LENGTH_SHORT).show();
            mbase = FirebaseDatabase.getInstance().getReference("News").child("বেসরকারি বিশ্ববিদ্যালয়");
            retriveCommonPart();

        }
        if(v.getId()==R.id.cd5){
            category.setText("প্রকৌশল ও বিজ্ঞান-প্রযুক্তি");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("প্রকৌশল ও বিজ্ঞান-প্রযুক্তি");
            retriveCommonPart();

        }
        if(v.getId()==R.id.cd6){
            category.setText("উচ্চ শিক্ষা");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("উচ্চ শিক্ষা");
            retriveCommonPart();

        }
        if(v.getId()==R.id.cd7){
            category.setText("উদ্যোক্তা");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("উদ্যোক্তা");
            retriveCommonPart();
        }
        if(v.getId()==R.id.cd8){
            category.setText("চাকরি");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("চাকরি");
            retriveCommonPart();
        }
        if(v.getId()==R.id.cd9){
            category.setText("ফ্রিল্যান্সিং");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("ফ্রিল্যান্সিং");
            retriveCommonPart();
        }
        if(v.getId()==R.id.cd10){
            category.setText("রিমোট জব");
            mbase = FirebaseDatabase.getInstance().getReference("News").child("রিমোট জব");
            retriveCommonPart();
        }
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }



}