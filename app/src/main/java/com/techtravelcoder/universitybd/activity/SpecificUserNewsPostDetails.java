package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
    TextView postNai ;
    String uid ;
    NewsModel newsModel;
    SpecificUserNewsAdapter specificUserNewsAdapter;
    ArrayList<NewsModel> list;
    ArrayList<UserModel>list1;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    CircleImageView pic;
    TextView nameUser,uniName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_user_news_post_details);





        postNai=findViewById(R.id.specific_post_number_check);
        recyclerView=findViewById(R.id.specificUserNewsRecyclerViewId);
        uid=FirebaseAuth.getInstance().getUid();
        //String uid =firebaseAuth.getCurrentUser().getUid();


        list1=new ArrayList<>();

        FirebaseApp.initializeApp(this);
        databaseReference= FirebaseDatabase.getInstance().getReference("SpecificUserNews").child(uid);
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

                        newsModel.setUid(uid);
                        newsModel.setKey(a);
                        list.add(0,newsModel);

                    }
                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }
                specificUserNewsAdapter.notifyDataSetChanged();

                if(list.isEmpty()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(SpecificUserNewsPostDetails.this);
                    builder.setMessage("কোন পোস্ট করা হয়নি। পোস্ট এর\n" +
                            "সংখ্যা ০।");
                    builder.setTitle("Post Update..");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        FirebaseDatabase.getInstance().getReference("User Information").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
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