package com.techtravelcoder.universitybdadmin.newspaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.adapter.NewsAdapter;
import com.techtravelcoder.universitybdadmin.model.NewsModel;

import java.util.ArrayList;

public class CRUDNewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ArrayList<NewsModel>list;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudnews);

        String str=getIntent().getStringExtra("key");
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();

        recyclerView=findViewById(R.id.rvCrudId);
        FirebaseApp.initializeApp(this);
        databaseReference=FirebaseDatabase.getInstance().getReference("News").child(str);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        newsAdapter=new NewsAdapter(this,list);
        recyclerView.setAdapter(newsAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    NewsModel newsModel = dataSnapshot.getValue(NewsModel.class);
                    if(newsModel != null){

                        list.add(0,newsModel);

                    }

                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }

                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}