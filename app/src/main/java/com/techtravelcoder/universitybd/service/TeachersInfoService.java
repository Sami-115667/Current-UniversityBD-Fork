package com.techtravelcoder.universitybd.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.AllUniversityActivity;
import com.techtravelcoder.universitybd.activity.MainActivity;
import com.techtravelcoder.universitybd.adapter.TeacherInfoAdapter;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;



public class TeachersInfoService extends AppCompatActivity {

     String name ;
     EditText editText;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    TeacherInfoAdapter teacherInfoAdapter;
    DatabaseReference mbase;
    Toolbar toolbar;
    TeacherInfoModel teacherInfoModel;
    private LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_info_service);
        editText=findViewById(R.id.search_edittext);
        lottieAnimationView=findViewById(R.id.loadingView);

        toolbar=findViewById(R.id.teacher_info_tollbar);
        setSupportActionBar(toolbar);






        int colorPrimary = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorPrimary = getColor(R.color.service_bar);
        }
        getWindow().setStatusBarColor(colorPrimary);


        lottieAnimationView.playAnimation();



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        name=getIntent().getStringExtra("key");//University name
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
        mbase = FirebaseDatabase.getInstance().getReference("Teacher Data").child("Teacher Information").child(name);

        recyclerView=findViewById(R.id.teacherInfoServiceRecyclerId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        TeacherInfoModel obj = new TeacherInfoModel(TeachersInfoService.this);
        FirebaseRecyclerOptions<TeacherInfoModel> options = new FirebaseRecyclerOptions.Builder<TeacherInfoModel>()
                .setQuery(mbase, TeacherInfoModel.class)
                .build();


       // TeachersInfoService teachersInfoService = new TeachersInfoService();

        teacherInfoAdapter= new TeacherInfoAdapter(options);
        recyclerView.setAdapter(teacherInfoAdapter);

        teacherInfoAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                // Data retrieval is complete, hide the Lottie animation
                lottieAnimationView.setVisibility(View.GONE);
            }
        });








    }






    @Override
    protected void onStart()
    {
        super.onStart();
        teacherInfoAdapter.startListening();
    }

    //Here on stop method create a problem , when we use intent the app is crushing again and again , when we back to our our app
    //after completing a implicit intent

//    @Override
//    protected void onStop()
//    {
//        super.onStop();
//        teacherInfoAdapter.stopListening();
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teacher_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_teacher_info){
            Intent intent = new Intent(TeachersInfoService.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void performSearch(String query) {
        if (query.isEmpty()) {
            // Show all data when the search query is empty
            FirebaseRecyclerOptions<TeacherInfoModel> options = new FirebaseRecyclerOptions.Builder<TeacherInfoModel>()
                    .setQuery(mbase, TeacherInfoModel.class)
                    .build();
            TeachersInfoService teachersInfoService = new TeachersInfoService();
            teacherInfoAdapter= new TeacherInfoAdapter(options);
            teacherInfoAdapter.startListening();
            recyclerView.setAdapter(teacherInfoAdapter);
        } else {
            try {

                FirebaseRecyclerOptions<TeacherInfoModel> options = new FirebaseRecyclerOptions.Builder<TeacherInfoModel>()
                        .setQuery(mbase.orderByChild("department").startAt(query).endAt(query + "\uf8ff"), TeacherInfoModel.class)
                        .build();
                TeachersInfoService teachersInfoService = new TeachersInfoService();
                teacherInfoAdapter= new TeacherInfoAdapter(options);
                teacherInfoAdapter.startListening();
                recyclerView.setAdapter(teacherInfoAdapter);
            } catch (Exception e) {
                // Handle any exceptions that occur during the database query
                Toast.makeText(this, "Error occurred while searching", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }







}