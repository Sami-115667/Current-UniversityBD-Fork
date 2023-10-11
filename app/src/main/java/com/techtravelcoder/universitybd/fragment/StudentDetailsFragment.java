package com.techtravelcoder.universitybd.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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
    String[] countries = {
            "University of Dhaka",
            "University of Barisal",
            "Chittagong University",
            "Jahangirnagar University",
            "Rajshahi University",
            "Khulna University",
            "Islamic University, Bangladesh",
            "University of Dhaka",
            "Comilla University",
            "Bangladesh Open University",
            "Jagannath University",
            "Jatiya Kabi Kazi Nazrul Islam University",
            "Begum Rokeya University, Rangpur",
            "Begum Rokeya University, Rangpur",
            "Rabindra University, Bangladesh",
            "Sheikh Hasina University"
    };


    public StudentDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        itemlist=new ArrayList<>();
        View view= inflater.inflate(R.layout.fragment_student_details, container, false);
        recyclerView=view.findViewById(R.id.student_details_fragment_rv_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.auto_tv_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, countries);
        autoCompleteTextView.setAdapter(adapter);


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