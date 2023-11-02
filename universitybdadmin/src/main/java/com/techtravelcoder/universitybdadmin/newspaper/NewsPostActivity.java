package com.techtravelcoder.universitybdadmin.newspaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.model.NewsModel;

public class NewsPostActivity extends AppCompatActivity {

    EditText title,desc,img,date;
    EditText author;

    NewsModel newsModel;
    String type;
    AppCompatButton post ,udsNews;
    FirebaseDatabase firebaseDatabase,firebaseDatabase1;
    DatabaseReference databaseReference,databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_post);

        author=findViewById(R.id.ed1);
        title=findViewById(R.id.ed2);
        date=findViewById(R.id.ed3);
        img=findViewById(R.id.ed4);
        desc=findViewById(R.id.ed5);
        udsNews=findViewById(R.id.udsId);
        post=findViewById(R.id.postId);

        type = getIntent().getStringExtra("key");

      //  Toast.makeText(this, "Rakib:"+type, Toast.LENGTH_SHORT).show();
        udsNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CRUDNewsActivity.class);
                intent.putExtra("key",type);
                startActivity(intent);
            }
        });




        if(!type.equals("Trending News")){

            databaseReference=FirebaseDatabase.getInstance().getReference("News").child(type);
            databaseReference1=FirebaseDatabase.getInstance().getReference("News").child("All News");
        }
        else {
            databaseReference=FirebaseDatabase.getInstance().getReference("News").child(type);

        }



        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDatatoFirebase();


            }
        });


    }

    private void addDatatoFirebase() {

        String s_author = author.getText().toString();
        String s_title = title.getText().toString();
        String s_date = date.getText().toString();
        String s_img = img.getText().toString();
        String s_desc = desc.getText().toString();

        newsModel=new NewsModel(s_author,s_date,s_desc,s_img,s_title);

        String a = databaseReference.push().getKey();
        newsModel.setKey(a);
        newsModel.setCatagory(type);

        if(TextUtils.isEmpty(s_author) ||  TextUtils.isEmpty(s_title) ||TextUtils.isEmpty(s_date) ||TextUtils.isEmpty(s_img) ||TextUtils.isEmpty(s_desc) ){
            Toast.makeText(this, "Please Fillup all data ", Toast.LENGTH_SHORT).show();
        }else{
            if(!type.equals("Trending News")){


                databaseReference.child(a).setValue(newsModel);
                databaseReference1.child(a).setValue(newsModel);
            }else{

                databaseReference.child(a).setValue(newsModel);

                //Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
            }


            Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), NewsCategoryActivity.class);
            startActivity(intent);
        }




    }
}
