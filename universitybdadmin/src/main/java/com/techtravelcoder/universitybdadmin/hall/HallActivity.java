package com.techtravelcoder.universitybdadmin.hall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.model.HallModel;
import com.techtravelcoder.universitybdadmin.teacherinfo.ShowAndDeleteActivity;
import com.techtravelcoder.universitybdadmin.teacherinfo.TeacherInfoActivity;

import java.io.IOException;
import java.util.ArrayList;

public class HallActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img1,img2,img3,img4,img5 ;
    private EditText ed0,ed1,ed2,ed3,ed4 ;
    private AppCompatSpinner uniCatagorySpinner;
    private AppCompatButton post;
    private DatabaseReference hallRef;
    private ArrayList<String> uniName;
    private String collectUniName;
    private DatabaseReference databaseReference;
    private Uri[] imageUris = new Uri[5];
    private StorageReference imageRef;
    private Uri filePath;
    private int num;
    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);

        img1=findViewById(R.id.hall_img1);
        img2=findViewById(R.id.hall_img2);
        img3=findViewById(R.id.hall_img3);
        img4=findViewById(R.id.hall_img4);
        img5=findViewById(R.id.hall_img5);

        ed0=findViewById(R.id.hall_ed0);
        ed1=findViewById(R.id.hall_ed1);
        ed2=findViewById(R.id.hall_ed2);
        ed3=findViewById(R.id.hall_ed3);
        ed4=findViewById(R.id.hall_ed4);
        post=findViewById(R.id.hallPostId);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        post.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        uniCatagorySpinner=findViewById(R.id.hall_university_id);

        manageSpinner();


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

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please Choose a University", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.hall_img1){
            selectImage();
            num=1;
        }
        if(v.getId()==R.id.hall_img2){
            selectImage();
            num=2;
        }
        if(v.getId()==R.id.hall_img3){
            selectImage();
            num=3;
        }
        if(v.getId()==R.id.hall_img4){
            selectImage();
            num=4;
        }
        if(v.getId()==R.id.hall_img5){
            selectImage();
            num=5;
        }
        if(v.getId()==R.id.hallPostId){
            uploadImagesAndText();
        }
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

        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            imageUris[num - 1] = filePath;
            try {

                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                if(num==1){
                    img1.setImageBitmap(bitmap);
                }
                if(num==2){
                    img2.setImageBitmap(bitmap);
                }
                if(num==3){
                    img3.setImageBitmap(bitmap);
                }
                if(num==4){
                    img4.setImageBitmap(bitmap);
                }
                if(num==5){
                    img5.setImageBitmap(bitmap);
                }
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }



    private void uploadImagesAndText() {
        String hallName = ed0.getText().toString();
        String text1 = ed1.getText().toString();
        String text2 = ed2.getText().toString();
        String text3 = ed3.getText().toString();
        String text4 = ed4.getText().toString();

        HallModel hallModel = new HallModel(text1, text2, text3, text4, collectUniName, hallName);

        if (collectUniName.equals("Choose your University")) {
            Toast.makeText(getApplicationContext(), "Please Choose a University", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a unique key for the hall entry in the database
        String uniHallKey = databaseReference.push().getKey();

        // Reference to the specific hall in the database
        hallRef = databaseReference.child("halls").child(collectUniName).child(uniHallKey);

        for (int i = 1; i <=5; i++) {
            // Upload each image separately
            uploadImage(uniHallKey, i);
        }

        hallRef.setValue(hallModel);

        // Display a success message or perform any other necessary actions
        Toast.makeText(getApplicationContext(), "Data Uploaded Successfully",Toast.LENGTH_SHORT).show();
    }

    private void uploadImage(String uniHallKey, int imageNumber) {
        Uri imageUri = imageUris[imageNumber - 1];
        if (imageUri != null) {
            // Construct the path to store the image in Firebase Storage
            String imagePath = "halls/" + uniHallKey + "/image" + imageNumber + ".jpg";
            StorageReference imageRef = FirebaseStorage.getInstance().getReference().child(imagePath);

            imageRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                // Get the download URL of the uploaded image
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Store the image URL in the database
                    hallRef.child("images").child("image" + imageNumber).setValue(uri.toString());
                });
            });
        }
    }

}

