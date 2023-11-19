package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class UserNewsPostActivity extends AppCompatActivity {
    private EditText title,descripton;
    private TextView category,chooseImage;
    NewsModel newsModel;
    private  ImageView setImage;
    private CardView cardView;
    private AppCompatButton post ;
    private int year,month,day ;
    UserModel userModel;
    private AppCompatSpinner newsCategory ;
    private String uid;
    private ArrayList listNews;
    private String collectName;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,databaseReference1,databaseReference2;
    private TextView date;
    private TextView headline;
    private CircleImageView pic ;
    private TextView nameUser,uniName ;
    private Switch aSwitch;
    private FrameLayout frameLayout;

    private ToggleButton control;
    private TextView italic,underline,noFormat;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    private TextView bold;
    private ImageView mic;
     ImageView imageView;

     Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_news_post);

        cardView=findViewById(R.id.card_id);
        title=findViewById(R.id.ed_title);
        date=findViewById(R.id.ed_date);
        chooseImage=findViewById(R.id.choose_image_id);
        setImage=findViewById(R.id.set_iamge_id);
        descripton=findViewById(R.id.ed_desc);
        newsCategory=findViewById(R.id.categoryNews);
        post=findViewById(R.id.postId);
        headline=findViewById(R.id.tv_merque_id);
        headline.setSelected(true);
        pic=findViewById(R.id.post_user_image);
        nameUser=findViewById(R.id.post_user_name);
        uniName=findViewById(R.id.post_user_university);
        aSwitch=findViewById(R.id.idSwitch);
        frameLayout=findViewById(R.id.frame_user_news);

//        control=findViewById(R.id.control_news_post_id);
//        bold=findViewById(R.id.text_bold_id);
//        italic=findViewById(R.id.italic_id);
//        underline=findViewById(R.id.underline_id);
//        noFormat=findViewById(R.id.no_format_id);

        mic=findViewById(R.id.mic_id);


        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMic();
            }
        });
//        control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(isChecked){
//                    bold.setVisibility(View.VISIBLE);
//                    italic.setVisibility(View.VISIBLE);
//                    underline.setVisibility(View.VISIBLE);
//
//
//                }else {
//                    bold.setVisibility(View.GONE);
//                    italic.setVisibility(View.GONE);
//                    underline.setVisibility(View.GONE);
//
//                }
//            }
//        });
//
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    title.setVisibility(View.GONE);
                    chooseImage.setVisibility(View.GONE);
                    date.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                }else {
                    title.setVisibility(View.VISIBLE);
                    chooseImage.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                    date.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.VISIBLE);

                }
            }
        });
//
//        bold.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int start = descripton.getSelectionStart();
//                int end = descripton.getSelectionEnd();
//
//               Spannable spannable = new SpannableStringBuilder(descripton.getText());
//                    // Apply bold formatting
//                    spannable.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
//                    descripton.setText(spannable);
//                    descripton.setSelection(start,end);
//            }
//        });
//        italic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int start = descripton.getSelectionStart();
//                int end = descripton.getSelectionEnd();
//
//                Spannable spannable = new SpannableStringBuilder(descripton.getText());
//                // Apply bold formatting
//                spannable.setSpan(new StyleSpan(Typeface.ITALIC), start, end, 0);
//                descripton.setText(spannable);
//                descripton.setSelection(start,end);
//            }
//        });
//        underline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int start = descripton.getSelectionStart();
//                int end = descripton.getSelectionEnd();
//
//                Spannable spannable = new SpannableStringBuilder(descripton.getText());
//                // Apply bold formatting
//                spannable.setSpan(new UnderlineSpan(), start, end, 0);
//                descripton.setText(spannable);
//                descripton.setSelection(start,end);
//            }
//        });
//
//        noFormat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int start = descripton.getSelectionStart();
//                int end = descripton.getSelectionEnd();
//
//                Spannable spannable = new SpannableStringBuilder(descripton.getText());
//
//                // Get all spans within the selected text range and remove them
//                Object[] spans = spannable.getSpans(start, end, Object.class);
//                for (Object span : spans) {
//                    spannable.removeSpan(span);
//                }
//
//                descripton.setText(spannable);
//                descripton.setSelection(start, end);
//            }
//        });




        FirebaseDatabase.getInstance().getReference("User Information").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            userModel=snapshot.getValue(UserModel.class);

                            if(userModel.getUserName() != null){
                                nameUser.setText(userModel.getUserName());

                            }
                            if(userModel.getImage1() != null){
                                Glide.with(getApplicationContext()).load(userModel.getImage1()).into(pic);

                            }
                            if(userModel.getUserUniversity() != null){
                                uniName.setText(userModel.getUserUniversity());

                            }

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

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the Uri of data
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                    setImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

        private void callMic(){
        Intent intent
                = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e) {
            Toast
                    .makeText(getApplicationContext(), " " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }







    private void addDatatoFirebase(String category) {

        String s_title = title.getText().toString();
        String s_date = date.getText().toString();
        String s_desc = descripton.getText().toString();
        String s_category = category;
        String s_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String s_userName = userModel.getUserName();
        String s_userUni = userModel.getUserUniversity();
        String s_userPic = userModel.getImage1();





        newsModel = new NewsModel(s_date, s_desc, s_title, s_category, s_uid);




        //TextUtils.isEmpty(s_title) || TextUtils.isEmpty(s_date) || TextUtils.isEmpty(s_img) ||

        if (TextUtils.isEmpty(s_desc)) {
            Toasty.info(getApplicationContext(), "Please Fillup all data", Toasty.LENGTH_SHORT).show();
        } else {
            String a = databaseReference.push().getKey();
            firebaseAuth = FirebaseAuth.getInstance();
            uid = firebaseAuth.getCurrentUser().getUid();
            newsModel.setKey(a);

            if (collectName.equals("choose your category")) {
                Toasty.info(getApplicationContext(), "Must be Select a Category", Toasty.LENGTH_SHORT).show();
            } else {
                uploadImage(a);
                databaseReference.child(a).setValue(newsModel);
                databaseReference1.child(uid).child(a).setValue(newsModel);
                databaseReference2.child(a).setValue(newsModel);

                Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                intent.putExtra("postUserUid", uid);
                startActivity(intent);
                finish();
            }
        }
    }

    private void uploadImage(String a) {
        if (filePath != null) {

                // Get a reference to the Firebase Storage
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference imagesRef = storageRef.child("newsImages/" + a);

                UploadTask uploadTask = imagesRef.putFile(filePath);

                uploadTask.addOnSuccessListener(taskSnapshot -> {

                    imagesRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String downloadUrl = uri.toString();
                        newsModel.setImage(downloadUrl);
                        databaseReference.child(a).setValue(newsModel);
                        databaseReference1.child(uid).child(a).setValue(newsModel);
                        databaseReference2.child(a).setValue(newsModel);

                    });
                }).addOnFailureListener(exception -> {
                    Toast.makeText(getApplicationContext(), "Image upload failed", Toast.LENGTH_SHORT).show();
                });


        }
        else {
            Toast.makeText(getApplicationContext(), "Select a Image", Toast.LENGTH_SHORT).show();
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

                if(!collectName.equals("Choose your category")){

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

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please Choose a Category", Toast.LENGTH_SHORT).show();

            }
        });



    }


}