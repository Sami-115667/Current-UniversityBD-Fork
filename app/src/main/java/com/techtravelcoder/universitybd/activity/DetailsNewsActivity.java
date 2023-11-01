package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsNewsActivity extends AppCompatActivity {


    TextView author,title,desc,date ;
    ImageView image ;
    CircleImageView userPic;
    NewsModel newsModel;
    TextView universityName,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);

        image=findViewById(R.id.detailsNewsImageId);
        author=findViewById(R.id.detailsNewsAuthorId);
        title=findViewById(R.id.detailsNewsTitleId);
        desc=findViewById(R.id.detailsNewsDescId);
        date=findViewById(R.id.detailsNewsDateId);

        userPic=findViewById(R.id.cv_user_image_id);
        universityName=findViewById(R.id.cv_uni_id);
        name=findViewById(R.id.cv_name_id);


        String c_author=getIntent().getStringExtra("author");
        String c_title=getIntent().getStringExtra("title");
        String c_desc=getIntent().getStringExtra("desc");
        String c_date=getIntent().getStringExtra("date");
        String c_image=getIntent().getStringExtra("image");
        String c_uid=getIntent().getStringExtra("postAutherUid");

        Glide.with(getApplicationContext()).load(c_image).into(image);

        if (c_author != null) {
            author.setText(c_author);
        }

        if (c_title != null) {
            title.setText(c_title);
        }

        if (c_desc != null) {
            desc.setText(c_desc);
        }

        if (c_date != null) {
            date.setText(c_date);
        }

        if (c_image != null) {
            Glide.with(getApplicationContext()).load(c_image).into(image);
        }

        newsModel=new NewsModel();

    Toast.makeText(getApplicationContext(), ""+c_uid, Toast.LENGTH_SHORT).show();

    if(c_uid != null){
        author.setVisibility(View.GONE);
        FirebaseDatabase.getInstance().getReference("User Information").
                child(c_uid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel userModel=snapshot.getValue(UserModel.class);
                            if(userModel.getUserName() != null && userModel.getImage1()!= null && userModel.getUserUniversity()!= null ){
                                Glide.with(getApplicationContext()).load(userModel.getImage1()).into(userPic);
                                name.setText(userModel.getUserName());
                                universityName.setText(userModel.getUserUniversity());
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}