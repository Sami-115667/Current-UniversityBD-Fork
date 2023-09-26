package com.techtravelcoder.universitybdadmin.teacherinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.model.NewsModel;
import com.techtravelcoder.universitybdadmin.model.TeacherInfoModel;
import com.techtravelcoder.universitybdadmin.newspaper.NewsCategoryActivity;

import java.util.ArrayList;

public class TeacherInfoActivity extends AppCompatActivity {

    private AppCompatSpinner uniCatagorySpinner ;
    TeacherInfoModel teacherInfoModel;
    DatabaseReference databaseReference;
    private EditText name ,depet,gmail,phone,description;
    private AppCompatButton post;
    String collectUniName ;
    private ArrayList uniName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        uniCatagorySpinner=findViewById(R.id.departmentId);
        name=findViewById(R.id.t_name_id);
        depet=findViewById(R.id.t_dept_id);
        gmail=findViewById(R.id.t_gmail_id);
        phone=findViewById(R.id.t_mobile_id);
        description=findViewById(R.id.t_desc_id);
        post=findViewById(R.id.teacherPostId);
        manageSpinner();
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 addDatatoFirebase();
            }
        });


    }

    private void manageSpinner() {
        uniName=new ArrayList<>();
        uniName.add("Choose your University");
        uniName.add("University of Dhaka");uniName.add("University of Barisal"); uniName.add("Chittagong University");uniName.add("Jahangirnagar University");uniName.add("Rajshahi University");uniName.add("Khulna University");uniName.add("Islamic University, Bangladesh");uniName.add("University of Dhaka");uniName.add("Comilla University");
        uniName.add("Bangladesh Open University");uniName.add("Jagannath University");uniName.add("Jatiya Kabi Kazi Nazrul Islam University");uniName.add("Begum Rokeya University, Rangpur");uniName.add("Begum Rokeya University, Rangpur");uniName.add("Rabindra University, Bangladesh");uniName.add("Sheikh Hasina University");


        ArrayAdapter uniAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniName);

        uniCatagorySpinner.setAdapter(uniAdapter);
        uniCatagorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collectUniName= (String) parent.getItemAtPosition(position);
                if(!collectUniName.equals("Choose your University")){
                    databaseReference= FirebaseDatabase.getInstance().getReference("TeacherInformation").child(collectUniName);
                    Toast.makeText(TeacherInfoActivity.this, ""+collectUniName, Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please Choose a University", Toast.LENGTH_SHORT).show();

            }
        });



    }
    private void addDatatoFirebase() {

        String s_name = name.getText().toString();
        String s_dept = depet.getText().toString();
        String s_gmail = gmail.getText().toString();
        String s_phone = phone.getText().toString();
        String s_descrip = description.getText().toString();
        teacherInfoModel=new TeacherInfoModel(s_name,s_dept,s_gmail,s_phone,s_descrip);

        if(TextUtils.isEmpty(s_name) ||  TextUtils.isEmpty(s_dept) ||TextUtils.isEmpty(s_gmail) ||TextUtils.isEmpty(s_phone)){
            Toast.makeText(this, "Please Fillup all data ", Toast.LENGTH_SHORT).show();
        }else{
                if(!collectUniName.equals("Choose your University")){
                    String a = databaseReference.push().getKey();
                    databaseReference.child(a).setValue(teacherInfoModel);
                    Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show();

                    //Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Select a Uiversity", Toast.LENGTH_SHORT).show();
                }

            }



        }




}


