package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class UserNewsPostActivity extends AppCompatActivity {
    private EditText name,title,image,descripton;
    private TextView category;
    private AppCompatButton post ;
    private int year,month,day ;
    UserModel userModel;
    private AppCompatSpinner newsCategory ;
    private String uid;
    private ArrayList listNews;
    private String collectName;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,databaseReference1,databaseReference2;
    private NewsModel newsModel;
    private TextView date;
    private TextView headline;
    private CircleImageView pic ;
    private TextView nameUser,uniName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_news_post);
        name=findViewById(R.id.ed_name);
        title=findViewById(R.id.ed_title);
        date=findViewById(R.id.ed_date);
        image=findViewById(R.id.ed_image);
        descripton=findViewById(R.id.ed_desc);
        newsCategory=findViewById(R.id.categoryNews);
        post=findViewById(R.id.postId);
        headline=findViewById(R.id.tv_merque_id);
        headline.setSelected(true);
        pic=findViewById(R.id.post_user_image);
        nameUser=findViewById(R.id.post_user_name);
        uniName=findViewById(R.id.post_user_university);


        FirebaseDatabase.getInstance().getReference("User Information").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            userModel=snapshot.getValue(UserModel.class);
                            Glide.with(getApplicationContext()).load(userModel.getImage1()).into(pic);
                            nameUser.setText(userModel.getUserName());
                            uniName.setText(userModel.getUserUniversity());

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toasty.error(getApplicationContext(),"Something wrong",Toasty.LENGTH_SHORT).show();
                    }
                });


        final Calendar calendar=Calendar.getInstance();
        manageSpinner();


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(UserNewsPostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        date.setText(sdf.format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



    }





    public static boolean isImageUrlValid(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return false;
        }
        else if(!Patterns.WEB_URL.matcher(imageUrl).matches()) {
            return false;
        }

        // Check if URL ends with a valid image extension
//        String[] allowedExtensions = { "jpg", "jpeg", "png", "gif","webp",".tiff","tif","ico","svg"
//        ,"raw","arw","nef","rw2","dng"};
//        for (String extension : allowedExtensions) {
//            if (imageUrl.toLowerCase().endsWith("." + extension)) {
//                return true;
//            }
//        }

        return true;
    }
    private void addDatatoFirebase(String category) {

        String s_name = name.getText().toString();
        String s_title = title.getText().toString();
        String s_date = date.getText().toString();
        String s_img = image.getText().toString();
        String s_desc = descripton.getText().toString();
        String s_category=category;
        Boolean isValid = isImageUrlValid(s_img);
        String s_uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        String s_userName=userModel.getUserName();
        String s_userUni=userModel.getUserUniversity();
        String s_userPic=userModel.getImage1();
        Toast.makeText(this, ""+s_userName+" ", Toast.LENGTH_SHORT).show();



        newsModel=new NewsModel(s_name,s_date,s_desc,s_img,s_title,s_category,s_uid,s_userName,s_userUni,s_userPic);

        if(TextUtils.isEmpty(s_name) ||  TextUtils.isEmpty(s_title) ||TextUtils.isEmpty(s_date) ||TextUtils.isEmpty(s_img) ||TextUtils.isEmpty(s_desc) ){
            Toast.makeText(this, "Please Fillup all data ", Toast.LENGTH_SHORT).show();
        }else{

            if(isValid ){
                String a = databaseReference.push().getKey();
                firebaseAuth=FirebaseAuth.getInstance();
                uid=firebaseAuth.getCurrentUser().getUid();


                //  Toast.makeText(this, ""+collectName, Toast.LENGTH_SHORT).show();

                if(collectName.equals("choose your category")){
                    Toast.makeText(this, "Must be select a category", Toast.LENGTH_SHORT).show();

                }else {
                    databaseReference.child(a).setValue(newsModel);
                    databaseReference1.child(uid).child(a).setValue(newsModel);
                    databaseReference2.child(a).setValue(newsModel);


                    Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                    startActivity(intent);
                }
            }else{
                Toast.makeText(this, "your image link is wrong", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Please add a valid image link ", Toast.LENGTH_SHORT).show();
            }

        }




    }

    private void manageSpinner() {
        listNews=new ArrayList<>();
        listNews.add("Choose your Category");
        listNews.add("উচ্চ শিক্ষা");listNews.add("উদ্যোক্তা"); listNews.add("চাকরি");listNews.add("ফ্রিল্যান্সিং");listNews.add("রিমোট জব");

        ArrayAdapter uniAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listNews);

        newsCategory.setAdapter(uniAdapter);
        newsCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collectName= (String) parent.getItemAtPosition(position).toString().toLowerCase();

                if(!collectName.equals("choose your category")){

                     databaseReference= FirebaseDatabase.getInstance().getReference("News").child(collectName);

                     databaseReference1=FirebaseDatabase.getInstance().getReference("SpecificUserNews");
                     databaseReference2=FirebaseDatabase.getInstance().getReference("News").child("All News");

                    post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addDatatoFirebase(collectName);

                        }
                    });

                    // Toast.makeText(UserNewsPostActivity.this, ""+collectName, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UserNewsPostActivity.this, "Please Choose a Category", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please Choose a Category", Toast.LENGTH_SHORT).show();

            }
        });



    }


}