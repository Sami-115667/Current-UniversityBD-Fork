package com.techtravelcoder.universitybd.user_profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.techtravelcoder.universitybd.R;

import com.techtravelcoder.universitybd.activity.SpecificUserNewsPostDetails;
import com.techtravelcoder.universitybd.activity.UserNewsPostActivity;
import com.techtravelcoder.universitybd.cgpacalculator.CGPADetailsActivity;
import com.techtravelcoder.universitybd.cgpacalculator.SemesterActivity;
import com.techtravelcoder.universitybd.connection.NetworkChangeListener;
import com.techtravelcoder.universitybd.model.BloodModel;
import com.techtravelcoder.universitybd.model.UserModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
public class UserProfileActivity extends AppCompatActivity {


    BloodModel bloodModel;

    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    String userUniversitySpin,userBloodGroupSpin,userDepartmentSpin;
    private AppCompatSpinner universitySpinner,bloodSpinner,deptSpinner ;
    private CardView cardView,calcCg,doPost,cgpaDetails,bloodDonate,businessGrow;
    private Context context;


    StorageReference storageReference;
    private  CircleImageView circleImageView;
    private TextView selectProf;

    private TextView name,gmail,university,department,bloodgroup,hall,roomNumber,mobile ;

    private FirebaseDatabase firebaseDatabase;
    private final int PICK_IMAGE_REQUEST = 220;
    private Uri filePath;
    private int num ;
    private DatabaseReference databaseReference,hallRef;
    private String uid ;
    private Uri[] imageUris = new Uri[2];
    UserModel userModel;
    private List<String> uniName,bloodGroup,uniDept ;
    private TextView updateProfile ;
    private CircleImageView prfPic,prf1,prf2;
    private ImageView backPic;
    private ProgressBar progressbar,progressbar11;
    private String postAutherUid;
    private TextView activities ,writeHere,changeActivity ;
    String userHall,userName,userUniversity,userGmail,userBloodgroup,userRoomNumber,userDepartment,userMobile,img1,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_user_profile);
        cardView=findViewById(R.id.ll_specific_post);
        progressbar=findViewById(R.id.progressBar);
        progressbar11=findViewById(R.id.progressBar1);
        activities=findViewById(R.id.tv_activities_id);
        writeHere=findViewById(R.id.tv_write_here_id);
        changeActivity=findViewById(R.id.tv_change_activities);
        bloodDonate=findViewById(R.id.blood_donner_id);
        businessGrow=findViewById(R.id.grow_business_id);

        calcCg=findViewById(R.id.cgpaCalc);

        //circleImageView=findViewById(R.id.userprofilepic);
        //selectProf=findViewById(R.id.select_prof_id);

        name=findViewById(R.id.user_name_id_tv);
        gmail=findViewById(R.id.user_gmail_id_tv);
        university=findViewById(R.id.user_university_id_tv);
        department=findViewById(R.id.user_dept_id_tv);
        bloodgroup=findViewById(R.id.user_blood_id_tv);
        mobile=findViewById(R.id.user_mobile_id_tv);
        roomNumber=findViewById(R.id.user_room_id_tv);
        hall=findViewById(R.id.user_hall_id_tv);
        updateProfile=findViewById(R.id.user_edit_profile_tv);
        prf1=findViewById(R.id.cv_profile_change_id);
        prf2=findViewById(R.id.cv_bacImage_change_id);
        prfPic=findViewById(R.id.circleImageView);
        backPic=findViewById(R.id.imageView2);
        doPost=findViewById(R.id.share_expert_id);
        cgpaDetails=findViewById(R.id.user_prf_cgpa_details_id);

        uid= FirebaseAuth.getInstance().getUid();

        postAutherUid=getIntent().getStringExtra("postAutherId");
        storageReference = FirebaseStorage.getInstance().getReference();
       // Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();
        firebaseDatabase=FirebaseDatabase.getInstance();

        if(postAutherUid.equals(uid)){
            databaseReference=firebaseDatabase.getReference("User Information").child(uid);
            hallRef=FirebaseDatabase.getInstance().getReference("User Information").child(uid);
            progressbar.setVisibility(View.VISIBLE);
            progressbar11.setVisibility(View.VISIBLE);

            bloodDonate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manageBloodDonate();
                }
            });
            businessGrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manageBusinessInfo();

                }
            });

            fetchUserData();

            cgpaDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(), CGPADetailsActivity.class);
                    startActivity(intent);
                }
            });


            doPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(), UserNewsPostActivity.class);
                    startActivity(intent);
                }
            });



            prf1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num=1;
                    selectImage();


                }
            });
            prf2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    num=2;
                    selectImage();
                }
            });


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserProfileActivity.this, SpecificUserNewsPostDetails.class);
                    intent.putExtra("postAutherId",postAutherUid);
                    startActivity(intent);
                }
            });
            calcCg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserProfileActivity.this, SemesterActivity.class);
                    startActivity(intent);
                }
            });

            updateProfile.setVisibility(View.GONE);
            updateProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    editProfile(userHall,userName,userGmail,userUniversity,userMobile,userBloodgroup,userDepartment,userRoomNumber);


                }
            });

        }
        else {

            prf1.setVisibility(View.GONE);
            gmail.setVisibility(View.GONE);
            mobile.setVisibility(View.GONE);
            prf2.setVisibility(View.GONE);
            writeHere.setVisibility(View.GONE);
            updateProfile.setVisibility(View.GONE);
            calcCg.setVisibility(View.GONE);
            doPost.setVisibility(View.GONE);
            cgpaDetails.setVisibility(View.GONE);
            databaseReference=firebaseDatabase.getReference("User Information").child(postAutherUid);
            hallRef=FirebaseDatabase.getInstance().getReference("User Information").child(postAutherUid);
            progressbar.setVisibility(View.VISIBLE);
            progressbar11.setVisibility(View.VISIBLE);
            fetchUserData();

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserProfileActivity.this, SpecificUserNewsPostDetails.class);
                    intent.putExtra("postAutherId",postAutherUid);

                    startActivity(intent);
                }
            });


        }



    }

    private void manageBusinessInfo() {
        AlertDialog.Builder builder=new AlertDialog.Builder(UserProfileActivity.this);
        final View view=getLayoutInflater().inflate(R.layout.business_info_design,null);

        builder.setView(view);
        AlertDialog alertDialog= builder.create();
        Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.alert_back);
        alertDialog.getWindow().setBackgroundDrawable(drawable);
        alertDialog.show();
    }

    private void bloodDialogue(int num){
        if(num==1){
            AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
            final View view = getLayoutInflater().inflate(R.layout.blood_donate_design, null);

            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.alert_back);
            alertDialog.getWindow().setBackgroundDrawable(drawable);
            alertDialog.show();

            RadioGroup radioGroup = view.findViewById(R.id.blood_radio_group_id);
            RadioButton yes = view.findViewById(R.id.blood_yes_id);
            RadioButton no = view.findViewById(R.id.blood_no_id);
            TextView t1, t2, updateStatus;
            t1 = view.findViewById(R.id.t1_id);
            t2 = view.findViewById(R.id.t2_id);
            EditText basic_info, description;
            basic_info = view.findViewById(R.id.blood_info_id);
            description = view.findViewById(R.id.blood_description_id);
            updateStatus = view.findViewById(R.id.update_status_id);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton checkedRadioButton = view.findViewById(checkedId);
                    String checkedText = checkedRadioButton.getText().toString();

                    if (checkedText.equals("Yes")) {
                        basic_info.setVisibility(View.VISIBLE);
                        description.setVisibility(View.VISIBLE);
                        updateStatus.setVisibility(View.VISIBLE);
                        t1.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                    } else if (checkedText.equals("No")) {
                        updateStatus.setVisibility(View.VISIBLE);
                        basic_info.setVisibility(View.GONE);
                        description.setVisibility(View.GONE);
                        t1.setVisibility(View.GONE);
                        t2.setVisibility(View.GONE);
                    }
                }
            });
            updateStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton checkedRadioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                    String checkedText = checkedRadioButton.getText().toString();

                    if (checkedText.equals("Yes")) {
                        String address = basic_info.getText().toString();
                        String info = description.getText().toString();

                        if ((!address.isEmpty()) && (!info.isEmpty())) {
                            insertDonationStatus(address, info);
                            alertDialog.dismiss();
                        } else {
                            Toasty.info(getApplicationContext(), "Fill up all options", Toasty.LENGTH_SHORT).show();
                        }
                    } else if (checkedText.equals("No")) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("status", false);
                        FirebaseDatabase.getInstance().getReference("User Information")
                                .child(FirebaseAuth.getInstance().getUid())
                                .child("blood")
                                .setValue(map1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toasty.success(getApplicationContext(), "You've successfully updated your Blood Information", Toasty.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                });

                    }
                }
            });
        }
        if(num==2){
            AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
            final View view = getLayoutInflater().inflate(R.layout.blood_donate_design, null);

            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.alert_back);
            alertDialog.getWindow().setBackgroundDrawable(drawable);
            alertDialog.show();

            RadioGroup radioGroup = view.findViewById(R.id.blood_radio_group_id);
            RadioButton yes = view.findViewById(R.id.blood_yes_id);
            RadioButton no = view.findViewById(R.id.blood_no_id);
            TextView t1, t2, updateStatus;
            t1 = view.findViewById(R.id.t1_id);
            t2 = view.findViewById(R.id.t2_id);
            EditText basic_info, description;
            basic_info = view.findViewById(R.id.blood_info_id);
            description = view.findViewById(R.id.blood_description_id);
            updateStatus = view.findViewById(R.id.update_status_id);
            basic_info.setVisibility(View.VISIBLE);
            description.setVisibility(View.VISIBLE);
            updateStatus.setVisibility(View.VISIBLE);
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.VISIBLE);
            yes.setChecked(true);
            basic_info.setText(bloodModel.getAddress());
            description.setText(bloodModel.getDescription());

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton checkedRadioButton = view.findViewById(checkedId);
                    String checkedText = checkedRadioButton.getText().toString();

                    if (checkedText.equals("Yes")) {
                        basic_info.setVisibility(View.VISIBLE);
                        description.setVisibility(View.VISIBLE);
                        updateStatus.setVisibility(View.VISIBLE);
                        t1.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                    } else if (checkedText.equals("No")) {
                        updateStatus.setVisibility(View.VISIBLE);
                        basic_info.setVisibility(View.GONE);
                        description.setVisibility(View.GONE);
                        t1.setVisibility(View.GONE);
                        t2.setVisibility(View.GONE);
                    }
                }
            });
            updateStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton checkedRadioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                    String checkedText = checkedRadioButton.getText().toString();

                    if (checkedText.equals("Yes")) {
                        String address = basic_info.getText().toString();
                        String info = description.getText().toString();

                        if ((!address.isEmpty()) && (!info.isEmpty())) {
                            insertDonationStatus(address, info);
                            alertDialog.dismiss();
                        } else {
                            Toasty.info(getApplicationContext(), "Fill up all options", Toasty.LENGTH_SHORT).show();
                        }
                    } else if (checkedText.equals("No")) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("status", false);
                        FirebaseDatabase.getInstance().getReference("User Information")
                                .child(FirebaseAuth.getInstance().getUid())
                                .child("blood")
                                .setValue(map1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toasty.success(getApplicationContext(), "You've successfully updated your Blood Information", Toasty.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                });

                    }
                }
            });
        }
        if(num==3){
            AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
            final View view = getLayoutInflater().inflate(R.layout.blood_donate_design, null);

            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.alert_back);
            alertDialog.getWindow().setBackgroundDrawable(drawable);
            alertDialog.show();

            RadioGroup radioGroup = view.findViewById(R.id.blood_radio_group_id);
            RadioButton yes = view.findViewById(R.id.blood_yes_id);
            RadioButton no = view.findViewById(R.id.blood_no_id);
            TextView t1, t2, updateStatus;
            t1 = view.findViewById(R.id.t1_id);
            t2 = view.findViewById(R.id.t2_id);
            EditText basic_info, description;
            basic_info = view.findViewById(R.id.blood_info_id);
            description = view.findViewById(R.id.blood_description_id);
            updateStatus = view.findViewById(R.id.update_status_id);

            updateStatus.setVisibility(View.VISIBLE);
            basic_info.setVisibility(View.GONE);
            description.setVisibility(View.GONE);
            t1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            no.setChecked(true);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton checkedRadioButton = view.findViewById(checkedId);
                    String checkedText = checkedRadioButton.getText().toString();

                    if (checkedText.equals("Yes")) {
                        basic_info.setVisibility(View.VISIBLE);
                        description.setVisibility(View.VISIBLE);
                        updateStatus.setVisibility(View.VISIBLE);
                        t1.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                    } else if (checkedText.equals("No")) {
                        updateStatus.setVisibility(View.VISIBLE);
                        basic_info.setVisibility(View.GONE);
                        description.setVisibility(View.GONE);
                        t1.setVisibility(View.GONE);
                        t2.setVisibility(View.GONE);
                    }
                }
            });
            updateStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton checkedRadioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                    String checkedText = checkedRadioButton.getText().toString();

                    if (checkedText.equals("Yes")) {
                        String address = basic_info.getText().toString();
                        String info = description.getText().toString();

                        if ((!address.isEmpty()) && (!info.isEmpty())) {
                            insertDonationStatus(address, info);
                            alertDialog.dismiss();
                        } else {
                            Toasty.info(getApplicationContext(), "Fill up all options", Toasty.LENGTH_SHORT).show();
                        }
                    } else if (checkedText.equals("No")) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("status", false);
                        FirebaseDatabase.getInstance().getReference("User Information")
                                .child(FirebaseAuth.getInstance().getUid())
                                .child("blood")
                                .setValue(map1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toasty.success(getApplicationContext(), "You've successfully updated your Blood Information", Toasty.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                });

                    }
                }
            });

        }

    }
    private void manageBloodDonate() {

        FirebaseDatabase.getInstance().getReference("User Information")
                .child(FirebaseAuth.getInstance().getUid())
                .child("blood")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        bloodModel = snapshot.getValue(BloodModel.class);

                        if (snapshot.exists()) {
                            if (bloodModel != null) {
                                if(bloodModel.getStatus().equals(true)){

                                    bloodDialogue(2);

                                }
                                if(bloodModel.getStatus().equals(false)){

                                    bloodDialogue(3);

                                }

                            }
                        }
                        else {
                            bloodDialogue(1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Database Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                });





    }

    private void insertDonationStatus(String address,String info) {
        Map<String,Object>map=new HashMap<>();
        map.put("address",address);
        map.put("description",info);
        map.put("status",true);

        FirebaseDatabase.getInstance().getReference("User Information").child(FirebaseAuth.getInstance().getUid())
                .child("blood")
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toasty.success(getApplicationContext(),"You ,Successfully Update your Blood Information",Toasty.LENGTH_SHORT).show();

                    }
                });

    }


    private void selectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the Uri of data
            filePath = data.getData();
            imageUris[num - 1] = filePath;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                if (num == 1) {
                    prfPic.setImageBitmap(bitmap);

                    uploadImage(); // Call uploadImage when the first image is selected.
                } else if (num == 2) {
                    backPic.setImageBitmap(bitmap);
                    uploadImage(); // Call uploadImage when the second image is selected.
                }
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        if(num==1){
            progressbar.setVisibility(View.VISIBLE);

        }
        if(num==2){
            progressbar11.setVisibility(View.VISIBLE);

        }

        String uniHallKey = databaseReference.push().getKey();

        Uri imageUri = imageUris[num - 1];

       // Toast.makeText(getApplicationContext(), "Bug fix", Toast.LENGTH_SHORT).show();


        if (imageUri != null) {
            // Construct the path to store the image in Firebase Storage
            String imagePath = "profiles/" + uniHallKey + "/image" + num + ".jpg"; // Use num here

            StorageReference imageRef = FirebaseStorage.getInstance().getReference().child(imagePath);


            imageRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                // Get the download URL of the uploaded image
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Store the image URL in the database
                    if(num==1){
                        userModel.setImage1(uri.toString());
                        hallRef.child("image"+num ).setValue(uri.toString());
                       // Toasty.info(getApplicationContext(),"Uploading Profile Pic...",Toasty.LENGTH_SHORT).show();

                        //  Toast.makeText(this, ""+userModel.getImage1(), Toast.LENGTH_SHORT).show();

                    } else if (num==2) {
                        userModel.setImage2(uri.toString());
                        hallRef.child("image"+num ).setValue(uri.toString());
                        //Toasty.info(getApplicationContext(),"Uploading Cover Photo...",Toasty.LENGTH_SHORT).show();


                    }



                  //  Toast.makeText(getApplicationContext(), "U&", Toast.LENGTH_SHORT).show();
                });
            });
        }
    }




    @Override
    protected void onStart() {
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }



    private void manageThreeSpinner(){
        uniName=new ArrayList<>();
        bloodGroup=new ArrayList<>();
        uniDept=new ArrayList<>();

        uniName.add("Choose your University");
        uniName.add("Not Provide");
        uniName.add("University of Dhaka");
        uniName.add("University of Barisal");
        uniName.add("Chittagong University");
        uniName.add("Jahangirnagar University");
        uniName.add("Rajshahi University");
        uniName.add("Khulna University");
        uniName.add("Islamic University, Bangladesh");
        uniName.add("Comilla University");
        uniName.add("Bangladesh Open University");
        uniName.add("Jagannath University");
        uniName.add("Jatiya Kabi Kazi Nazrul Islam University");
        uniName.add("Begum Rokeya University, Rangpur");
        uniName.add("BUP");
        uniName.add("Rabindra University, Bangladesh");
        uniName.add("Sheikh Hasina University");
        uniName.add("North South University");
        uniName.add("University of Science and Technology Chittagong");
        uniName.add("Independent University, Bangladesh");
        uniName.add("AUST");
        uniName.add("AIUB");
        uniName.add("University of Asia Pacific");
        uniName.add("Asian University of Bangladesh");
        uniName.add("BRAC University");
        uniName.add("Leading University");
        uniName.add("Bangladesh Open University");
        uniName.add("Sylhet International University");
        uniName.add("Daffodil International University");
        uniName.add("Green University of Bangladesh");
        uniName.add("Bangladesh University of Professionals");
        uniName.add("Rabindra University, Bangladesh");
        uniName.add("Sonargaon University");
        uniName.add("Chittagong Independent University");
        uniName.add("Royal University of Dhaka");
        uniName.add("Uttara University");
        uniName.add("Northern University Bangladesh");
        uniName.add("Stamford University Bangladesh");
        uniName.add("BSMRAU");
        uniName.add("Shere Bangla Agricultural University");
        uniName.add("JKKNIU");
        uniName.add("CVASU");
        uniName.add("Sylhet Agricultural University");
        uniName.add("Khulna Agricultural University");
        uniName.add("Hobiganj Agricultural University");
        uniName.add("Kurigram Agricultural University");
        uniName.add("BAU");
        uniName.add("BUET");
        uniName.add("RUET");
        uniName.add("KUET");
        uniName.add("CUET");
        uniName.add("IUT");
        uniName.add("DUET");
        uniName.add("BUTEX");
        uniName.add("SUST");
        uniName.add("HSTU");
        uniName.add("MBSTU");
        uniName.add("PSTU");
        uniName.add("NSTU");
        uniName.add("JUST");
        uniName.add("PUST");
        uniName.add("RSTU");
        uniName.add("BSFMSTU");

        uniDept.add("Choose your Department");
        uniDept.add("Not Provide");
        uniDept.add("Department of Islamic History & Culture");
        uniDept.add("Department of Anthropology");
        uniDept.add("Department of Applied Chemistry & Chemical Engineering");
        uniDept.add("Department of Applied Mathematics");
        uniDept.add("Department of Arabic");
        uniDept.add("Department of Bangla");
        uniDept.add("Department of Urdu");
        uniDept.add("Department of Sanskrit");
        uniDept.add("Department of Pali and Buddhist Studies");
        uniDept.add("Department of History");
        uniDept.add("Department of Philosophy");
        uniDept.add("Department of Islamic Studies");
        uniDept.add("Department of Information Science and Library Management");
        uniDept.add("Department of Linguistics");
        uniDept.add("Department of Music");
        uniDept.add("Department of World Religions and Culture");
        uniDept.add("Department of Dance");
        uniDept.add("Department of Physics");
        uniDept.add("Department of Mathematics");
        uniDept.add("Department of Chemistry");
        uniDept.add("Department of Statistics");
        uniDept.add("Department of Theoretical Physics");
        uniDept.add("Department of Biomedical Physics & Technology");
        uniDept.add("Department of Applied Mathematics");
        uniDept.add("Department of Theoretical and Computational Chemistry");
        uniDept.add("Department of Law");
        uniDept.add("Department of Accounting & Information Systems");
        uniDept.add("Department of Management");
        uniDept.add("Department of Marketing");
        uniDept.add("Department of Finance");
        uniDept.add("Department of Banking and Insurance");
        uniDept.add("Department of Management Information Systems (MIS)");
        uniDept.add("Department of International Business");
        uniDept.add("Department of Tourism and Hospitality Management");
        uniDept.add("Department of Organization Strategy and Leadership");
        uniDept.add("Department of Communication Disorders");
        uniDept.add("Department of Economics");
        uniDept.add("Department of Political Science");
        uniDept.add("Department of International Relations");
        uniDept.add("Department of Sociology");
        uniDept.add("Department of Mass Communication & Journalism");
        uniDept.add("Department of Public Administration");
        uniDept.add("Department of Anthropology");
        uniDept.add("Department of Population Sciences");
        uniDept.add("Department of Peace and Conflict Studies");
        uniDept.add("Department of Women and Gender Studies");
        uniDept.add("Department of Development Studies");
        uniDept.add("Department of Television, Film and Photography");
        uniDept.add("Department of Criminology");
        uniDept.add("Department of Printing and Publication Studies");
        uniDept.add("Department of Japanese Studies");
        uniDept.add("Department of Soil, Water & Environment");
        uniDept.add("Department of Botany");
        uniDept.add("Department of Zoology");
        uniDept.add("Department of Biochemistry and Molecular Biology");
        uniDept.add("Department of Psychology");
        uniDept.add("Department of Microbiology");
        uniDept.add("Department of Fisheries");
        uniDept.add("Department of Clinical Psychology");
        uniDept.add("Department of Genetic Engineering and Biotechnology");
        uniDept.add("Department of Educational and Counselling Psychology");
        uniDept.add("Department of Pharmacy");
        uniDept.add("Department of Disaster Science and Climate Resilience");
        uniDept.add("Department of Geography & Environment");
        uniDept.add("Department of Geology");
        uniDept.add("Department of Oceanography");
        uniDept.add("Department of Meteorology");
        uniDept.add("Department of Computer Science and Engineering");
        uniDept.add("Department of Electrical and Electronic Engineering");
        uniDept.add("Department of Robotics and Mechatronics Engineering");
        uniDept.add("Department of Applied Chemistry & Chemical Engineering");
        uniDept.add("Department of Nuclear Engineering");
        uniDept.add("Department of Civil Engineering");
        uniDept.add("Department of Materials and Metallurgical Engineering");
        uniDept.add("Department of Water Resource Engineering");
        uniDept.add("Department of Mechanical Engineering");
        uniDept.add("Department of Naval Architecture and Marine Engineering");
        uniDept.add("Department of Industrial and Production Engineering");
        uniDept.add("Department of Biomedical Engineering");
        uniDept.add("Department of Architecture");
        uniDept.add("Department of Urban and Regional Planning");
        uniDept.add("Department of Drawing and Painting");
        uniDept.add("Department of Graphic Design");
        uniDept.add("Department of Printmaking");
        uniDept.add("Department of Oriental Art");
        uniDept.add("Department of Ceramic");
        uniDept.add("Department of Sculpture");
        uniDept.add("Department of Craft");
        uniDept.add("Department of History of Art");
        uniDept.add("Department of Fabric Engineering");
        uniDept.add("Department of Yarn Engineering");
        uniDept.add("Department of Apparel Engineering");
        uniDept.add("Department of Textile Fashion & Design");
        uniDept.add("Department of Wet Process Engineering");
        uniDept.add("Department of Dyes & Chemical Engineering");
        uniDept.add("Department of Environmental Science & Engineering");
        uniDept.add("Department of Materials Science & Engineering");
        uniDept.add("Department of Textile Engineering Management");
        uniDept.add("Department of Industrial & Production Engineering");
        uniDept.add("Department of Humanities & Social Sciences");

        uniDept.add("Department of Mathematics & Statistics");
        uniDept.add("Department of Machinery Design & Maintenance");



        bloodGroup.add("Choose your blood group");
        bloodGroup.add("Not Provide");
        bloodGroup.add("A+");bloodGroup.add("A-");bloodGroup.add("O+");bloodGroup.add("O-");
        bloodGroup.add("B+");bloodGroup.add("B-");bloodGroup.add("AB+");bloodGroup.add("AB-");



        ArrayAdapter uniAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniName);
        uniAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        ArrayAdapter bloodAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,bloodGroup);
        ArrayAdapter deptAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniDept);
        bloodAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        deptAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);



        universitySpinner.setAdapter(uniAdapter);
        bloodSpinner.setAdapter(bloodAdapter);
        deptSpinner.setAdapter(deptAdapter);
        universitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userUniversitySpin= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfileActivity.this, "Please Choose a University", Toast.LENGTH_SHORT).show();

            }
        });

        bloodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userBloodGroupSpin= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfileActivity.this, "Please Choose a blood group", Toast.LENGTH_SHORT).show();

            }
        });
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userDepartmentSpin= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfileActivity.this, "Please Choose a Department", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void fetchUserData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    userModel = snapshot.getValue(UserModel.class);
                    if (userModel != null) {


                         userHall = userModel.getUserHall();
                         userName=userModel.getUserName();
                         userGmail=userModel.getUserEmail();
                         userUniversity=userModel.getUserUniversity();
                         userMobile=userModel.getUserPhoneNumber();
                         userBloodgroup=userModel.getUserBloodGroup();
                         userDepartment=userModel.getUserDept();
                         userRoomNumber=userModel.getUserRoom();
                         img1=userModel.getImage1();
                         img2=userModel.getImage2();

                      //  Toast.makeText(getApplicationContext(), ""+img1, Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(getApplicationContext(), ""+img2, Toast.LENGTH_SHORT).show();




                        if(!postAutherUid.equals(uid)){
                            activities.setText("See "+userModel.getUserName()+" Activities");
                            changeActivity.setText("See "+userModel.getUserName()+" Post");
                        }


                        if(userModel.getUserName() != null){
                            name.setText(userModel.getUserName());
                        }
                        if(userModel.getUserUniversity() != null){
                            if(userModel.getUserUniversity().equals("nothing") || userModel.getUserUniversity().equals("Not Provide")){
                                university.setText("University : কোন তথ্য পাওয়া যায়নি");
                            }else {
                                university.setText(userModel.getUserUniversity());
                            }

                        }


                        if(userModel.getUserEmail() != null){
                            gmail.setText(userModel.getUserEmail());
                        }
                        if(userModel.getUserPhoneNumber() != null){
                            if(userModel.getUserPhoneNumber().equals("nothing") ||userModel.getUserPhoneNumber().equals("") ){
                                mobile.setText("Mobile No : কোন তথ্য পাওয়া যায়নি");
                            }else {
                                mobile.setText(userModel.getUserPhoneNumber());
                            }
                        }
                        if(userModel.getUserBloodGroup() != null){
                            if(userModel.getUserBloodGroup().equals("nothing") || userModel.getUserBloodGroup().equals("Not Provide")){
                                bloodgroup.setText("Blood Group : কোন তথ্য পাওয়া যায়নি");
                            }else{
                                bloodgroup.setText("Blood Group : "+userModel.getUserBloodGroup());
                            }
                        }
                        if(userModel.getUserDept() != null){
                            if(userModel.getUserDept().equals("nothing") || userModel.getUserDept().equals("Not Provide")){
                                department.setText("Department : কোন তথ্য পাওয়া যায়নি");
                            }else{
                                department.setText(userModel.getUserDept());
                            }
                        }
                        if(userModel.getUserRoom() != null){
                            if(userModel.getUserRoom().equals("")){
                                roomNumber.setText("Room Number : কোন তথ্য পাওয়া যায়নি");
                            }else{
                                roomNumber.setText("Room Number : "+userModel.getUserRoom());
                            }
                        }else {
                            roomNumber.setText("Room Number : কোন তথ্য পাওয়া যায়নি");
                        }
                        if(userModel.getUserHall() != null){
                            if(userModel.getUserHall().equals("")){
                                hall.setText("Hall : কোন তথ্য পাওয়া যায়নি");
                            }else{
                                hall.setText("Hall Name : "+userModel.getUserHall());
                            }
                        }else {
                            hall.setText("Hall : কোন তথ্য পাওয়া যায়নি");
                        }

                        if(postAutherUid.equals(uid)){
                            updateProfile.setVisibility(View.VISIBLE);
                        }





                        if (img1 != null) {
                            Glide.with(prfPic.getContext()).load(img1).into(prfPic);
                            progressbar.setVisibility(View.GONE);
                           // Toasty.info(getApplicationContext(),"Successfully loading Profile Picture",Toasty.LENGTH_SHORT).show();


                        }
                        if (img2 != null) {
                            Glide.with(backPic.getContext()).load(img2).into(backPic);
                            progressbar11.setVisibility(View.GONE);
                            //Toasty.info(getApplicationContext(),"Successfully loading Cover Photo",Toasty.LENGTH_SHORT).show();
                        }
                        progressbar.setVisibility(View.GONE);
                        progressbar11.setVisibility(View.GONE);




                        //   Toast.makeText(UserProfileActivity.this, ""+userModel.getUserDept(), Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(UserProfileActivity.this, "Something wrong when fetching", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getApplicationContext(),"Something Error ...",Toasty.LENGTH_SHORT).show();

            }
        });
    }
    private void editProfile(String userHall,String userName,String userGmail,String userUniversity,String userMobile,String userBloodgroup,String userDepartment,String userRoomNumber){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.user_details_update_popup, null);
        dialogBuilder.setView(dialogView);

        EditText e_name = dialogView.findViewById(R.id.update_name_id_ed);
        universitySpinner= dialogView.findViewById(R.id.update_uni_id_tv);
        EditText e_hall_name = dialogView.findViewById(R.id.update_hallName_id_tv);
        deptSpinner= dialogView.findViewById(R.id.update_dept_id_tv);
        EditText e_mobile = dialogView.findViewById(R.id.update_mobile_id_tv);
        bloodSpinner= dialogView.findViewById(R.id.update_blood_id_tv);
        EditText e_room_number = dialogView.findViewById(R.id.update_hallNumber_id_tv);
        LottieAnimationView lottieAnimationView=dialogView.findViewById(R.id.pop_up_lottie_id);
        lottieAnimationView.playAnimation();

        AppCompatButton userProfileUpdate=dialogView.findViewById(R.id.update_profile_id_ab);

        e_name.setText(userModel.getUserName());

        if(userModel.getUserHall()!=null){
            if(userModel.getUserHall().equals("")){
                e_hall_name.setHint("add hall room number...");
            }else{
                e_hall_name.setText(userModel.getUserHall());
            }
        }else{
            e_room_number.setHint("add hall room number...");
        }

        if(userModel.getUserPhoneNumber().equals("nothing") || userModel.getUserPhoneNumber().equals("")){
            e_mobile.setHint("add phone number...");
        }else{
            e_mobile.setText(userModel.getUserPhoneNumber());
        }

        if(userModel.getUserRoom()!=null){
            if(userModel.getUserRoom().equals("")){
                e_room_number.setHint("add hall room number...");
            }else{
                e_room_number.setText(userModel.getUserRoom());
            }
        }else{
            e_room_number.setHint("add hall room number...");
        }

        spinProblemFix(dialogView);


        manageThreeSpinner();




        AlertDialog alertDialog = dialogBuilder.create();
        Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.alert_back);
        alertDialog.getWindow().setBackgroundDrawable(drawable);
        alertDialog.show();

        userProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userUniversitySpin.equals("Choose your University")   || userDepartmentSpin.equals("Choose your Department") ||  userBloodGroupSpin.equals("Choose your blood group")
                || userUniversitySpin == null || userDepartmentSpin == null || userBloodGroupSpin == null){
                    Toasty.error(getApplicationContext(), "University ,Department or Blood Group is missing...", Toast.LENGTH_SHORT, true).show();
                    Toasty.info(getApplicationContext(), "Fillup missing information...", Toast.LENGTH_SHORT, true).show();

                }else {

                    alertDialog.dismiss();
                    ProgressDialog progressDialog=new ProgressDialog(UserProfileActivity.this);
                    progressDialog.setTitle("ℹ️ℹ️ Loading...");
                    progressDialog.setMessage("✔✔Profile is Updating !!!");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.alert_back);
                    progressDialog.getWindow().setBackgroundDrawable(drawable);


                    Map<String, Object> map = new HashMap<>();
                    map.put("userName", e_name.getText().toString());
                    map.put("userHall", e_hall_name.getText().toString());
                    map.put("userBloodGroup", userBloodGroupSpin);
                    map.put("userRoom", e_room_number.getText().toString());
                    map.put("userPhoneNumber", e_mobile.getText().toString());
                    map.put("userUniversity", userUniversitySpin);
                    map.put("userDept", userDepartmentSpin);

                    //String a=FirebaseAuth.getInstance().getUid();



                    FirebaseDatabase.getInstance().getReference("User Information").child(FirebaseAuth.getInstance().getUid()).updateChildren(map).
                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    progressDialog.dismiss();

                                }
                            }).
                            addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Toast.makeText(context, "Something wrong...", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();

                                }
                            });


                }


            }
        });



    }

    private void spinProblemFix(View dialogView) {

        TextView e_uni_text=dialogView.findViewById(R.id.uni_text_spin);
        TextView e_dept_text=dialogView.findViewById(R.id.uni_dept_spin);
        TextView e_blood_text=dialogView.findViewById(R.id.uni_blood_spin);


        FrameLayout uni_frm=dialogView.findViewById(R.id.uni_frame_id);
        FrameLayout dept_frm=dialogView.findViewById(R.id.dept_frame_id);
        FrameLayout blood_frm=dialogView.findViewById(R.id.blood_frame_id);

        if(userModel.getUserUniversity().equals("Not Provide")){
            e_uni_text.setHint("add university...");

        }else{
            e_uni_text.setText(userModel.getUserUniversity());

        }

        if(userModel.getUserBloodGroup().equals("Not Provide")){
            e_blood_text.setHint("add blood group...");

        }else{
            e_blood_text.setText(userModel.getUserBloodGroup());

        }

        if(userModel.getUserDept().equals("Not Provide")){
            e_dept_text.setHint("add department...");

        }else{
            e_dept_text.setText(userModel.getUserDept());

        }

        universitySpinner.setVisibility(View.GONE);
        deptSpinner.setVisibility(View.GONE);
        bloodSpinner.setVisibility(View.GONE);


        userUniversitySpin=userModel.getUserUniversity();
        userDepartmentSpin=userModel.getUserDept();
        userBloodGroupSpin=userModel.getUserBloodGroup();

        uni_frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_uni_text.setVisibility(View.GONE);
                universitySpinner.setVisibility(View.VISIBLE);


            }
        });
        dept_frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_dept_text.setVisibility(View.GONE);
                deptSpinner.setVisibility(View.VISIBLE);


            }
        });
        blood_frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_blood_text.setVisibility(View.GONE);
                bloodSpinner.setVisibility(View.VISIBLE);


            }
        });
    }


}























