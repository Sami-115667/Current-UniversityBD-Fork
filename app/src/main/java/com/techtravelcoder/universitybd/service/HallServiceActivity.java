package com.techtravelcoder.universitybd.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.HallServiceAdapter;

import com.techtravelcoder.universitybd.connection.NetworkChangeListener;
import com.techtravelcoder.universitybd.model.HallServiceModel;


import java.util.ArrayList;

public class HallServiceActivity extends AppCompatActivity {

    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    RecyclerView hallRecyclerView;
    HallServiceAdapter hallServiceAdapter;
    ArrayList<HallServiceModel> list ;
    DatabaseReference databaseReference;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_service);

        lottieAnimationView=findViewById(R.id.loadingViewHallLottie);
        lottieAnimationView.playAnimation();



        String uniName =getIntent().getStringExtra("name");
        Toast.makeText(this, ""+uniName, Toast.LENGTH_SHORT).show();


        hallRecyclerView=findViewById(R.id.hall_service_rv_id);
        list=new ArrayList<>();
        hallServiceAdapter=new HallServiceAdapter(this,list);

        databaseReference= FirebaseDatabase.getInstance().getReference("halls").child(uniName);
        hallRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hallRecyclerView.setAdapter(hallServiceAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    //Toast.makeText(getApplicationContext(), "ryr", Toast.LENGTH_SHORT).show();

                    HallServiceModel hallServiceModel = snapshot1.getValue(HallServiceModel.class);
                   // Toast.makeText(getApplicationContext(), "rrr", Toast.LENGTH_SHORT).show();

                    if(hallServiceModel != null){

                        list.add(hallServiceModel);

                    }
                }


                lottieAnimationView.setVisibility(View.GONE);
                hallServiceAdapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });






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