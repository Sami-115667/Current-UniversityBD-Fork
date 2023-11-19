package com.techtravelcoder.universitybd.activity;

import static android.graphics.Typeface.BOLD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;
import com.techtravelcoder.universitybd.user_profile.UserProfileActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsNewsActivity extends AppCompatActivity {


    TextView author,title,desc,date ;
    ImageView image ;
    CircleImageView userPic;
    NewsModel newsModel;
    TextView universityName,name;
    private TextView like ;
    int cnt =0;
    private String c_post_key,c_post_category,c_uid,currentPostNember;
    private String c_userpic,c_useruniversity,c_author,c_title,c_desc,c_date,c_image,c_username;
    String c_number;
    private ImageView likeImage ;
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
        like=findViewById(R.id.details_news_like_id);
        likeImage=findViewById(R.id.details_news_like_img);

        author.setMovementMethod(LinkMovementMethod.getInstance());
        desc.setMovementMethod(LinkMovementMethod.getInstance());




         c_number =getIntent().getStringExtra("numberCheck");
         c_userpic=getIntent().getStringExtra("userPicture");
         c_useruniversity=getIntent().getStringExtra("university");
         c_username=getIntent().getStringExtra("name");
        c_author=getIntent().getStringExtra("author");
        c_title=getIntent().getStringExtra("title");

        c_desc=getIntent().getStringExtra("desc");

        c_date=getIntent().getStringExtra("date");
        c_image=getIntent().getStringExtra("image");
        c_post_key=getIntent().getStringExtra("postKey");
        c_post_category=getIntent().getStringExtra("postCategory");
        //Toast.makeText(getApplicationContext(), ""+c_number, Toast.LENGTH_SHORT).show();


        if(c_number.equals("1")){
           // Toast.makeText(getApplicationContext(), "Rakib", Toast.LENGTH_SHORT).show();

            likeImage.setVisibility(View.GONE);
            like.setVisibility(View.GONE);

            commingFromSpecificUserNewsAdapter();
        }
        else {
            commingNewsSpecificUserNewsAdapter();
        }



    }

    private void commingFromSpecificUserNewsAdapter() {

        author.setVisibility(View.GONE);

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
        if(c_username != null){
            name.setText(c_username);
        }
        if(c_useruniversity != null){
            universityName.setText(c_useruniversity);
        }
        if(c_userpic != null){
            Glide.with(getApplicationContext()).load(c_userpic).into(userPic);

        }


    }

    private void commingNewsSpecificUserNewsAdapter(){
        c_uid = getIntent().getStringExtra("postAutherUid");

        Toast.makeText(getApplicationContext(), "" + c_uid, Toast.LENGTH_SHORT).show();

        currentPostNember = getIntent().getStringExtra("postLike");
        if (currentPostNember == null) {
            currentPostNember = String.valueOf(0);
        }

        userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c_uid != null) {
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("postAutherId", c_uid);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailsNewsActivity.this);
                    builder.setTitle("This is Admin Post");
                    builder.setMessage("Admin has no Profile..");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }


                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c_uid != null) {
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("postAutherId", c_uid);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailsNewsActivity.this);
                    builder.setTitle("This is Admin Post");
                    builder.setMessage("Admin has no Profile..");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }


                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });
        universityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c_uid != null) {
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("postAutherId", c_uid);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailsNewsActivity.this);
                    builder.setTitle("This is Admin Post");
                    builder.setMessage("Admin has no Profile..");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }


                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });

        FirebaseDatabase.getInstance().getReference("News")
                .child("All News").child(c_post_key).child("likes").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.child(FirebaseAuth.getInstance().getUid()).exists()) {
                            likeImage.setImageResource(R.drawable.baseline_favorite_24);
                            like.setText(String.valueOf(Integer.parseInt(currentPostNember)) + " Loves");


                        } else {
                            like.setText(String.valueOf(Integer.parseInt(currentPostNember)) + " Loves");
                            likeImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    manageLike();
                                }
                            });

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        FirebaseDatabase.getInstance().getReference("News")
                .child(c_post_category).child(c_post_key).child("likes").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.child(FirebaseAuth.getInstance().getUid()).exists()) {
                            likeImage.setImageResource(R.drawable.baseline_favorite_24);
                            like.setText(String.valueOf(Integer.parseInt(currentPostNember)) + " Loves");


                        } else {
                            like.setText(String.valueOf(Integer.parseInt(currentPostNember)) + " Loves");
                            likeImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    manageLike();
                                }
                            });

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


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

        newsModel = new NewsModel();

        //Toast.makeText(getApplicationContext(), ""+c_uid, Toast.LENGTH_SHORT).show();

        if (c_uid != null) {
            author.setVisibility(View.GONE);
            FirebaseDatabase.getInstance().getReference("User Information").
                    child(c_uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                UserModel userModel = snapshot.getValue(UserModel.class);
                                if (userModel.getUserName() != null && userModel.getImage1() != null && userModel.getUserUniversity() != null) {
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

    public  void manageLike(){
       // Toast.makeText(this, ""+c_post_category, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, ""+c_post_key, Toast.LENGTH_SHORT).show();

        if(c_post_category.equals("Trending News")){
            FirebaseDatabase.getInstance().getReference("News").child(c_post_category).child(c_post_key)
                    .child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            FirebaseDatabase.getInstance().getReference("News").child(c_post_category).child(c_post_key)
                                    .child("postLike").setValue(String.valueOf(String.valueOf(Integer.parseInt(currentPostNember)+1))).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            like.setText(String.valueOf(Integer.parseInt(currentPostNember)+1)+" Loves");
                                            likeImage.setImageResource(R.drawable.baseline_favorite_24);

                                        }
                                    });
                        }
                    });

        }else{
            FirebaseDatabase.getInstance().getReference("News").child(c_post_category).child(c_post_key)
                    .child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            FirebaseDatabase.getInstance().getReference("News").child(c_post_category).child(c_post_key)
                                    .child("postLike").setValue(String.valueOf(String.valueOf(Integer.parseInt(currentPostNember)+1))).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            like.setText(String.valueOf(Integer.parseInt(currentPostNember)+1)+" Loves");
                                            likeImage.setImageResource(R.drawable.baseline_favorite_24);

                                        }
                                    });
                        }
                    });

            FirebaseDatabase.getInstance().getReference("News").child("All News").child(c_post_key)
                    .child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            FirebaseDatabase.getInstance().getReference("News").child("All News").child(c_post_key)
                                    .child("postLike").setValue(String.valueOf(String.valueOf(Integer.parseInt(currentPostNember)+1))).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            like.setText(String.valueOf(Integer.parseInt(currentPostNember)+1)+" Loves");
                                            likeImage.setImageResource(R.drawable.baseline_favorite_24);

                                        }
                                    });
                        }
                    });


        }







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}