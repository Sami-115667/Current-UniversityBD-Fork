package com.techtravelcoder.universitybd.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.CGPADetailsAdapter;
import com.techtravelcoder.universitybd.adapter.TeacherInfoAdapter;
import com.techtravelcoder.universitybd.model.CGPADetailsModel;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;

public class CGPADetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    CGPADetailsModel cgpaDetailsModel;
    CGPADetailsAdapter cgpaDetailsAdapter;
    String str;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpadetails);

        lottieAnimationView=findViewById(R.id.loadingViewCg);

        lottieAnimationView.playAnimation();



        firebaseAuth=FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference mbase = FirebaseDatabase.getInstance().getReference("CGPA Details").child(uid);

       // mbase = FirebaseDatabase.getInstance().getReference("CGPA Details").child(uid);

        recyclerView=findViewById(R.id.cgpaDetailsRecyclerId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CGPADetailsModel> options = new FirebaseRecyclerOptions.Builder<CGPADetailsModel>()
                .setQuery(mbase, CGPADetailsModel.class)
                .build();


        cgpaDetailsAdapter= new CGPADetailsAdapter(options);
        recyclerView.setAdapter(cgpaDetailsAdapter);

        cgpaDetailsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                // Data retrieval is complete, hide the Lottie animation
                lottieAnimationView.setVisibility(View.GONE);
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        cgpaDetailsAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        cgpaDetailsAdapter.stopListening();



    }
}