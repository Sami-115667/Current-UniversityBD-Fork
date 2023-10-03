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
import com.techtravelcoder.universitybdadmin.FirstActivity;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.model.NewsModel;
import com.techtravelcoder.universitybdadmin.model.TeacherInfoModel;
import com.techtravelcoder.universitybdadmin.newspaper.NewsCategoryActivity;

import java.util.ArrayList;

public class TeacherInfoActivity extends AppCompatActivity {

    private AppCompatSpinner uniCatagorySpinner ;
    TeacherInfoModel teacherInfoModel;
    DatabaseReference databaseReference;
    private EditText name ,depet,gmail,phone,description,image;
    private AppCompatButton post;
    String collectUniName ;
    private ArrayList uniName;
    AppCompatButton seePost;

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
        image=findViewById(R.id.t_iamge_id);
        post=findViewById(R.id.teacherPostId);
        seePost=findViewById(R.id.allTeacherInfoId);


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
        uniName.add("University of Dhaka");uniName.add("University of Barisal"); uniName.add("Chittagong University");uniName.add("Jahangirnagar University");uniName.add("Rajshahi University");uniName.add("Khulna University");uniName.add("Islamic University, Bangladesh");uniName.add("Comilla University");
        uniName.add("Bangladesh Open University");uniName.add("Jagannath University");uniName.add("Jatiya Kabi Kazi Nazrul Islam University");uniName.add("Begum Rokeya University, Rangpur");uniName.add("Rabindra University, Bangladesh");uniName.add("Sheikh Hasina University");uniName.add("BUP");

        uniName.add("National University");
        uniName.add("BUET");uniName.add("RUET");uniName.add("KUET");uniName.add("CUET");uniName.add("IUT");uniName.add("DUET");uniName.add("BUTEX");

        uniName.add("SUST");uniName.add("HSTU");uniName.add("MBSTU");uniName.add("PSTU");uniName.add("NSTU");uniName.add("JUST");uniName.add("PUST");uniName.add("RSTU");uniName.add("BSFMSTU");

        uniName.add("BSMRAU");uniName.add("Shere Bangla Agricultural University");uniName.add("JKKNIU");uniName.add("CVASU");uniName.add("Sylhet Agricultural University");uniName.add("Khulna Agricultural University");uniName.add("BAU");
        uniName.add("Hobiganj Agricultural University");uniName.add("Kurigram Agricultural University");

        uniName.add("North South University");uniName.add("University of Science and Technology Chittagong");uniName.add("Independent University,Bangladesh");uniName.add("AUST");uniName.add("AIUB");uniName.add("University of Asia Pacific");
        uniName.add("Asian University of Bangladesh ");uniName.add("BRAC University");uniName.add("Leading University");
        uniName.add("Bangladesh Open University");uniName.add("Sylhet International University");uniName.add("Daffodil International University");
        uniName.add("Green University of Bangladesh");uniName.add("Bangladesh University of Professionals");uniName.add("Rabindra University,Bangladesh");uniName.add("Sonargaon University");
        uniName.add("Chittagong Independent University");uniName.add("Royal University of Dhaka");uniName.add("Uttara University");uniName.add("Northern University Bangladesh");
        uniName.add("Stamford University Bangladesh");


        ArrayAdapter uniAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniName);

        uniCatagorySpinner.setAdapter(uniAdapter);
        uniCatagorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collectUniName= (String) parent.getItemAtPosition(position).toString().toLowerCase();

                if(!collectUniName.equals("Choose your University")){
                    seePost.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getApplicationContext(), ShowAndDeleteActivity.class);
                            intent.putExtra("key",collectUniName);
                            startActivity(intent);
                        }
                    });
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
        String s_image=image.getText().toString();
        teacherInfoModel=new TeacherInfoModel(s_name,s_dept,s_gmail,s_phone,s_descrip,s_image);

        if(TextUtils.isEmpty(s_name) ||  TextUtils.isEmpty(s_dept) ||TextUtils.isEmpty(s_gmail) ||TextUtils.isEmpty(s_phone)){
            Toast.makeText(this, "Please Fillup all data ", Toast.LENGTH_SHORT).show();
        }else{
                if(!collectUniName.equals("Choose your University")){
                    String a = databaseReference.push().getKey();
                    databaseReference.child(a).setValue(teacherInfoModel);
                    Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                    startActivity(intent);

                    //Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Select a Uiversity", Toast.LENGTH_SHORT).show();
                }

            }



        }




}


