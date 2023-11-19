package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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

    private RecyclerView recyclerView;
    private TextView postNai ;
    private String uid ;
    private NewsModel newsModel;
    private SpecificUserNewsAdapter specificUserNewsAdapter;
    private ArrayList<NewsModel> list;
    private ArrayList<UserModel>list1;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private CircleImageView pic;
    private TextView nameUser,uniName;
    public String postAutherUid;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_user_news_post_details);





        lottieAnimationView=findViewById(R.id.loadingViewSpecificUser);
        postNai=findViewById(R.id.specific_post_number_check);
        recyclerView=findViewById(R.id.specificUserNewsRecyclerViewId);
        uid=FirebaseAuth.getInstance().getUid();

        postAutherUid=getIntent().getStringExtra("postAutherId");
        //String uid =firebaseAuth.getCurrentUser().getUid();
        lottieAnimationView.playAnimation();

       // Toast.makeText(this, ""+postAutherUid, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();



        databaseReference= FirebaseDatabase.getInstance().getReference("SpecificUserNews").child(postAutherUid);




        list1=new ArrayList<>();

        FirebaseApp.initializeApp(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        specificUserNewsAdapter=new SpecificUserNewsAdapter(this,list,list1);
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
                        newsModel.setPostAutherUid(postAutherUid);
                        newsModel.setUid(uid);
                        newsModel.setKey(a);
                        list.add(0,newsModel);

                    }
                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }
                lottieAnimationView.setVisibility(View.GONE);
                specificUserNewsAdapter.notifyDataSetChanged();

                if(list.isEmpty()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(SpecificUserNewsPostDetails.this);
                    builder.setMessage("ℹ️ কোন পোস্ট পাউয়া যায়নি । পোস্ট এর\n" +
                            "সংখ্যা ০।");
                    builder.setTitle("Post Update..‼️");
                    builder.setCancelable(false);
                    builder.setPositiveButton("✅Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                    Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.alert_back);
                    alertDialog.getWindow().setBackgroundDrawable(drawable);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        FirebaseDatabase.getInstance().getReference("User Information").
                child(postAutherUid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel userModel=snapshot.getValue(UserModel.class);

                            for(DataSnapshot ds : snapshot.getChildren()){
                                list1.add(0,userModel);
                            }


                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something wrong",Toasty.LENGTH_SHORT).show();
                    }
                });










    }



}