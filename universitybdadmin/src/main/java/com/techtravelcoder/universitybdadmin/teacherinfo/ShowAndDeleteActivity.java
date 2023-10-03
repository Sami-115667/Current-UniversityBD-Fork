package com.techtravelcoder.universitybdadmin.teacherinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.adapter.TeacherInfoAdapter;
import com.techtravelcoder.universitybdadmin.model.TeacherInfoModel;

import java.util.ArrayList;

public class ShowAndDeleteActivity extends AppCompatActivity {

    androidx.recyclerview.widget.RecyclerView recyclerView;
    TeacherInfoAdapter teacherInfoAdapter;
    DatabaseReference mbase;
    TeacherInfoModel teacherInfoModel;
    ArrayList<TeacherInfoModel>list;
    private LottieAnimationView lottieAnimationView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_and_delete);



        list=new ArrayList<>();

        name=getIntent().getStringExtra("key");//University name

        mbase = FirebaseDatabase.getInstance().getReference("TeacherInformation").child(name);
        recyclerView=findViewById(R.id.teacherInfoServiceRecyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherInfoAdapter=new TeacherInfoAdapter(this,list);

        recyclerView.setAdapter(teacherInfoAdapter);
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String uniName=name;
                    String key=dataSnapshot.getKey();

                    // Toast.makeText(getApplicationContext(), ""+name, Toast.LENGTH_SHORT).show();

                    TeacherInfoModel teacherInfoModel = dataSnapshot.getValue(TeacherInfoModel.class);
                    if(teacherInfoModel != null){
                         teacherInfoModel.setKey(key);
                         teacherInfoModel.setUniName(uniName);
                        list.add(0,teacherInfoModel);

                    }
                }

                teacherInfoAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}