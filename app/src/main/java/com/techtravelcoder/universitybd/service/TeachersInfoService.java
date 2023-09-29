package com.techtravelcoder.universitybd.service;

import static java.util.Locale.filter;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.AllUniversityActivity;
import com.techtravelcoder.universitybd.activity.MainActivity;
import com.techtravelcoder.universitybd.adapter.TeacherInfoAdapter;
import com.techtravelcoder.universitybd.adapter.TrendingNewsAdapter;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;

import java.util.ArrayList;
import java.util.List;


public class TeachersInfoService extends AppCompatActivity {

    String name ;
    EditText editText;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    TeacherInfoAdapter teacherInfoAdapter;
    DatabaseReference mbase;
    ArrayList<TeacherInfoModel>list;
    ArrayList<TeacherInfoModel>filteredList;
    Toolbar toolbar;
    private LottieAnimationView lottieAnimationView;
    private Boolean isloaded=false;


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


        list=new ArrayList<>();
        name=getIntent().getStringExtra("key");//University name
        mbase = FirebaseDatabase.getInstance().getReference("TeacherInformation").child(name);
        recyclerView=findViewById(R.id.teacherInfoServiceRecyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherInfoAdapter=new TeacherInfoAdapter(this,list);
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();

        recyclerView.setAdapter(teacherInfoAdapter);
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                   // Toast.makeText(getApplicationContext(), ""+name, Toast.LENGTH_SHORT).show();

                    TeacherInfoModel teacherInfoModel = dataSnapshot.getValue(TeacherInfoModel.class);
                    if(teacherInfoModel != null){


                        list.add(teacherInfoModel);

                    }
                }

                teacherInfoAdapter.notifyDataSetChanged();
                isloaded=true;
                if(isloaded){
                    lottieAnimationView.cancelAnimation();
                    lottieAnimationView.setVisibility(View.GONE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    private void filter (String text){
        List<TeacherInfoModel> filterList= new ArrayList<>();
        for(TeacherInfoModel obj : list ){
            if(obj.getDept().toLowerCase().contains(text.toLowerCase())){
                filterList.add(obj);
            }
        }
        teacherInfoAdapter.filterList((ArrayList<TeacherInfoModel>) filterList);
    }





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






}