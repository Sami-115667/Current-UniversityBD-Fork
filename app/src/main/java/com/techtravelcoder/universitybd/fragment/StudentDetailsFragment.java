package com.techtravelcoder.universitybd.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.StudentDetailsFragmentAdapter;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;

import java.util.ArrayList;


public class StudentDetailsFragment extends Fragment {

    private RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    UserModel userModel;
    DatabaseReference databaseReference;
    ArrayList<UserModel> itemlist ;
    StudentDetailsFragmentAdapter studentDetailsFragmentAdapter;

    public StudentDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        itemlist=new ArrayList<>();
        View view= inflater.inflate(R.layout.fragment_student_details, container, false);
        recyclerView=view.findViewById(R.id.student_details_fragment_rv_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference=FirebaseDatabase.getInstance().getReference("User Information");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    //Toast.makeText(CRUDNewsActivity.this, ""+catagory, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(CRUDNewsActivity.this, ""+key, Toast.LENGTH_SHORT).show();

                    userModel = dataSnapshot.getValue(UserModel.class);
                    if(userModel != null){

                        itemlist.add(userModel);

                    }

                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }

                studentDetailsFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        studentDetailsFragmentAdapter=new StudentDetailsFragmentAdapter(itemlist);
        recyclerView.setAdapter(studentDetailsFragmentAdapter);





        return view;
    }
}