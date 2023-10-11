package com.techtravelcoder.universitybd.user_profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.SpecificUserNewsPostDetails;
import com.techtravelcoder.universitybd.cgpacalculator.SemesterActivity;
import com.techtravelcoder.universitybd.loginandsignup.SignupActivity;
import com.techtravelcoder.universitybd.model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {
     String userUniversity,userBloodGroup,userDepartment;
    AppCompatSpinner universitySpinner,bloodSpinner,deptSpinner ;
    private CardView cardView,calcCg;
    private  CircleImageView circleImageView;
    private TextView selectProf;
    private static final int SELECT_PICTURE = 200;
    private TextView name,gmail,university,department,bloodgroup,hall,roomNumber,mobile ;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String uid ;
    UserModel userModel;
    private List<String> uniName,bloodGroupList,deptList ;
    private TextView updateProfile ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        cardView=findViewById(R.id.ll_specific_post);
        calcCg=findViewById(R.id.cgpaCalc);
        circleImageView=findViewById(R.id.userprofilepic);
        selectProf=findViewById(R.id.select_prof_id);

        name=findViewById(R.id.user_name_id_tv);
        gmail=findViewById(R.id.user_gmail_id_tv);
        university=findViewById(R.id.user_university_id_tv);
        department=findViewById(R.id.user_dept_id_tv);
        bloodgroup=findViewById(R.id.user_blood_id_tv);
        mobile=findViewById(R.id.user_mobile_id_tv);
        roomNumber=findViewById(R.id.user_room_id_tv);
        hall=findViewById(R.id.user_hall_id_tv);
        updateProfile=findViewById(R.id.user_edit_profile_tv);

        uid= FirebaseAuth.getInstance().getUid();
       // Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("User Information").child(uid);
        fetchUserData();



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this, SpecificUserNewsPostDetails.class);
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

        selectProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

    }

    private void manageThreeSpinner(){
        uniName=new ArrayList<>();
        bloodGroupList=new ArrayList<>();
        deptList=new ArrayList<>();

        uniName.add("University of Dhaka");uniName.add("University of Barisal"); uniName.add("Chittagong University");uniName.add("Jahangirnagar University");uniName.add("Rajshahi University");uniName.add("Khulna University");uniName.add("Islamic University, Bangladesh");uniName.add("University of Dhaka");uniName.add("Comilla University");
        uniName.add("Bangladesh Open University");uniName.add("Jagannath University");uniName.add("Jatiya Kabi Kazi Nazrul Islam University");uniName.add("Begum Rokeya University, Rangpur");uniName.add("Begum Rokeya University, Rangpur");uniName.add("Rabindra University, Bangladesh");uniName.add("Sheikh Hasina University");


        bloodGroupList.add("A+");bloodGroupList.add("A-");bloodGroupList.add("O+");bloodGroupList.add("O-");bloodGroupList.add("B+");
        bloodGroupList.add("B-");bloodGroupList.add("O+");bloodGroupList.add("AB+");bloodGroupList.add("AB-");

        deptList.add("CSE");deptList.add("EEE");deptList.add("ME");

        ArrayAdapter uniAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniName);
        ArrayAdapter bloodAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,bloodGroupList);
        ArrayAdapter deptAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,deptList);


        universitySpinner.setAdapter(uniAdapter);
        bloodSpinner.setAdapter(bloodAdapter);
        deptSpinner.setAdapter(deptAdapter);
        universitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userUniversity= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfileActivity.this, "Please Choose a University", Toast.LENGTH_SHORT).show();

            }
        });

        bloodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userBloodGroup= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfileActivity.this, "Please Choose a blood group", Toast.LENGTH_SHORT).show();

            }
        });
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userDepartment= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfileActivity.this, "Please Choose a Department", Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void editProfile(){
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

        AppCompatButton userProfileUpdate=dialogView.findViewById(R.id.update_profile_id_ab);

         //Toast.makeText(this, "Rakib", Toast.LENGTH_SHORT).show();
        e_name.setText(userModel.getUserName());
        e_hall_name.setText(userModel.getUserHall());
        e_mobile.setText(userModel.getUserPhoneNumber());
        e_room_number.setText(userModel.getUserRoom());
        manageThreeSpinner();


        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        userProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map=new HashMap<>();
                map.put("userName",e_name.getText().toString());
                map.put("userHall",e_hall_name.getText().toString());
                map.put("userBloodGroup",userBloodGroup);
                map.put("userRoom",e_room_number.getText().toString());
                map.put("userPhoneNumber",e_mobile.getText().toString());
                map.put("userUniversity",userUniversity);
                map.put("userDept",userDepartment);

                //String a=FirebaseAuth.getInstance().getUid();


                FirebaseDatabase.getInstance().getReference("User Information").child(FirebaseAuth.getInstance().getUid())
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UserProfileActivity.this, "Successfully Update", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Toast.makeText(context, "Something wrong...", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                            }
                        });
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
                        String userHall = userModel.getUserHall();
                        String userName=userModel.getUserName();
                        String userGmail=userModel.getUserEmail();
                        String userUniversity=userModel.getUserUniversity();
                        String userMobile=userModel.getUserPhoneNumber();
                        String userBloodgroup=userModel.getUserBloodGroup();
                        String userDepartment=userModel.getUserDept();
                        String userRoomNumber=userModel.getUserRoom();
                        name.setText(userModel.getUserName());
                        hall.setText(userModel.getUserHall());
                        gmail.setText(userModel.getUserEmail());
                        university.setText(userModel.getUserUniversity());
                        mobile.setText(userModel.getUserPhoneNumber());
                        bloodgroup.setText(userModel.getUserBloodGroup());
                        department.setText(userModel.getUserDept());
                        roomNumber.setText(userModel.getUserRoom());

                     //   Toast.makeText(UserProfileActivity.this, ""+userModel.getUserDept(), Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(UserProfileActivity.this, "Something wrong when fetching", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    circleImageView.setImageURI(selectedImageUri);
                    selectProf.setText("Edit your Profile Picture");
                }
            }
        }
    }


}