package com.techtravelcoder.universitybd.cgpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.CGPADetailsAdapter;
import com.techtravelcoder.universitybd.adapter.TeacherInfoAdapter;
import com.techtravelcoder.universitybd.connection.NetworkChangeListener;
import com.techtravelcoder.universitybd.model.CGPADetailsModel;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;

import java.util.ArrayList;

public class CGPADetailsActivity extends AppCompatActivity {

    NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    DatabaseReference mbase;
    CGPADetailsAdapter cgpaDetailsAdapter;
    String str;
    ArrayList<CGPADetailsModel>list;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpadetails);

         lottieAnimationView=findViewById(R.id.loadingViewCg);

         lottieAnimationView.playAnimation();



        firebaseAuth=FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();





        mbase=FirebaseDatabase.getInstance().getReference("CGPA Details").child(uid);

       // Toast.makeText(this, ""+key, Toast.LENGTH_SHORT).show();




       // mbase = FirebaseDatabase.getInstance().getReference("CGPA Details").child(uid);

        recyclerView=findViewById(R.id.cgpaDetailsRecyclerId);
        list=new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cgpaDetailsAdapter= new CGPADetailsAdapter(this,list);
        recyclerView.setAdapter(cgpaDetailsAdapter);
        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String key = dataSnapshot.getKey();
                    firebaseAuth=FirebaseAuth.getInstance();
                    String uid = firebaseAuth.getCurrentUser().getUid();


                    CGPADetailsModel cgpaDetailsModel1 = dataSnapshot.getValue(CGPADetailsModel.class);
                    if(cgpaDetailsModel1 != null){
                        cgpaDetailsModel1.setKey(key);
                        cgpaDetailsModel1.setUid(uid);
                        list.add(cgpaDetailsModel1);

                    }

                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }

                cgpaDetailsAdapter.notifyDataSetChanged();
                lottieAnimationView.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}