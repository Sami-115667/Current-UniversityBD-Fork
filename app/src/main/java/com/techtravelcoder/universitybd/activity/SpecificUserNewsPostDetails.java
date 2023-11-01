package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.SpecificUserNewsAdapter;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class SpecificUserNewsPostDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    String uid ;
    NewsModel newsModel;
    SpecificUserNewsAdapter specificUserNewsAdapter;
    ArrayList<NewsModel> list;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_user_news_post_details);




        recyclerView=findViewById(R.id.specificUserNewsRecyclerViewId);
        uid=FirebaseAuth.getInstance().getUid();
        //String uid =firebaseAuth.getCurrentUser().getUid();



        Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();
        FirebaseApp.initializeApp(this);
        databaseReference= FirebaseDatabase.getInstance().getReference("SpecificUserNews").child(uid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        specificUserNewsAdapter=new SpecificUserNewsAdapter(this,list);
        recyclerView.setAdapter(specificUserNewsAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String a = dataSnapshot.getKey();
                    firebaseAuth=FirebaseAuth.getInstance();
                    uid=firebaseAuth.getCurrentUser().getUid();


                    //Toast.makeText(CRUDNewsActivity.this, ""+catagory, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(CRUDNewsActivity.this, ""+key, Toast.LENGTH_SHORT).show();


                    newsModel = dataSnapshot.getValue(NewsModel.class);
                    if(newsModel != null){

                        newsModel.setUid(uid);
                        newsModel.setKey(a);
                        list.add(0,newsModel);

                    }

                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }

                specificUserNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

//    private void loadUserHeaderInformation(UserModel userModel) {
//        View headerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.specific_news_design, null, false);
//
//        pic = headerView.findViewById(R.id.sd_user_image);
//        nameUser = headerView.findViewById(R.id.sd_user_name);
//        uniName = headerView.findViewById(R.id.sd_user_university);
//
//        Glide.with(getApplicationContext()).load(userModel.getImage1()).into(pic);
//        nameUser.setText(userModel.getUserName());
//        uniName.setText(userModel.getUserUniversity());
//    }

//            FirebaseDatabase.getInstance().getReference("User Information").
//    child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            if(snapshot.exists()){
//
//                UserModel userModel=snapshot.getValue(UserModel.class);
//                loadUserHeaderInformation(userModel);
//
//            }
//        }
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//            Toasty.error(getApplicationContext(),"Something wrong",Toasty.LENGTH_SHORT).show();
//        }
//    });

}