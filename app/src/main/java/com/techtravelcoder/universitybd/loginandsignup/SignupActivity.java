package com.techtravelcoder.universitybd.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.MainActivity;
import com.techtravelcoder.universitybd.cgpacalculator.SemesterActivity;
import com.techtravelcoder.universitybd.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pageSift;
    private FirebaseDatabase firebaseDatabase,firebaseDatabase1;
    private DatabaseReference databaseReference,databaseReference1,databaseReference2,databaseReference3,databaseReference4,databaseReference5,databaseReference6,databaseReference7;
    private FirebaseAuth auth;
    private String uid ;
    private EditText name,email,password,reEnterPassword,phone ;
    private AppCompatButton signup;
    private ProgressDialog progressDialog;
    private AppCompatSpinner universitySpinner,bloodSpinner,deptSpinner ;
    private List<String>uniName,bloodGroup,uniDept ;
    private String userUniversity,userBloodGroup,userDept ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


            pageSift=findViewById(R.id.alreadySignUp);
            auth=FirebaseAuth.getInstance();
            firebaseDatabase=FirebaseDatabase.getInstance();
            firebaseDatabase1=FirebaseDatabase.getInstance();

            databaseReference=firebaseDatabase.getReference("User Information");
            databaseReference1=firebaseDatabase1.getReference("Search").child("Uni");
            databaseReference2 =FirebaseDatabase.getInstance().getReference("Search").child("Dept");
            databaseReference3=FirebaseDatabase.getInstance().getReference("Search").child("Blood");



            databaseReference4=FirebaseDatabase.getInstance().getReference("Search").child("UniBlood");
            databaseReference5=FirebaseDatabase.getInstance().getReference("Search").child("UniDept");

            databaseReference6=FirebaseDatabase.getInstance().getReference("Search").child("UniDeptBlood");
            databaseReference7=FirebaseDatabase.getInstance().getReference("Search").child("DeptBlood");




        name=findViewById(R.id.signUpName);
            email=findViewById(R.id.signUpEmail);
            password=findViewById(R.id.signUpPassword);
            reEnterPassword=findViewById(R.id.signUpReenterPassword);
            phone=findViewById(R.id.signUpPhone);
            signup=findViewById(R.id.signUpButton);
            universitySpinner=findViewById(R.id.signUpUniversity);
            bloodSpinner=findViewById(R.id.signUpBlood);
            deptSpinner=findViewById(R.id.signUpUniversityDept);
        int colorPrimary = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorPrimary = getColor(R.color.back);
        }

        getWindow().setStatusBarColor(colorPrimary);


            manageSpinner();

            pageSift.setOnClickListener(this);
            signup.setOnClickListener(this);


      //  Toast.makeText(this, "Fix it", Toast.LENGTH_SHORT).show();



    }

    private void manageSpinner() {
        uniName=new ArrayList<>();
        bloodGroup=new ArrayList<>();
        uniDept=new ArrayList<>();


        uniName.add("Choose your University");
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
        bloodGroup.add("A+");bloodGroup.add("A-");bloodGroup.add("O+");bloodGroup.add("O-");
        bloodGroup.add("B+");bloodGroup.add("B-");bloodGroup.add("AB+");bloodGroup.add("AB-");

        ArrayAdapter uniAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniName);
        ArrayAdapter bloodAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,bloodGroup);
        ArrayAdapter uniDeptAdapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniDept);


        universitySpinner.setAdapter(uniAdapter);
        bloodSpinner.setAdapter(bloodAdapter);
        deptSpinner.setAdapter(uniDeptAdapter);
        universitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userUniversity= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SignupActivity.this, "Please Choose a University", Toast.LENGTH_SHORT).show();

            }
        });

        bloodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userBloodGroup= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SignupActivity.this, "Please Choose a blood group", Toast.LENGTH_SHORT).show();

            }
        });

        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userDept= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SignupActivity.this, "Please Choose a Department", Toast.LENGTH_SHORT).show();

            }
        });





    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.alreadySignUp){
            Intent intent = new Intent(SignupActivity.this,UserLoginActivity.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.signUpButton){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Registration is Processing....");
            progressDialog.setTitle("Please Wait");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            registerNewUser();
        }
    }

    private void registerNewUser() {
        String userName = name.getText().toString().trim();
        String userPassword = password.getText().toString();
        String userEmail = email.getText().toString().trim();
        String userReenterPassword = reEnterPassword.getText().toString();
        String userPhoneNumber = phone.getText().toString().trim();



        String regex = "^01[3-9]\\d{8}$";



        // Input validation
        if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userReenterPassword.isEmpty() || userPhoneNumber.isEmpty() || userUniversity.equals("Choose your University") || userBloodGroup.equals("Choose your blood group") || userDept.equals("Choose your Department")) {
            progressDialog.dismiss();
            Toasty.info(this, "All fields are Required...", Toast.LENGTH_SHORT, true).show();
            return;
        }
        if (!userPhoneNumber.matches(regex)) {
            progressDialog.dismiss();
            phone.setError("Invalid phone number");
            phone.requestFocus();
            return;
        }

        if (!userPassword.equals(userReenterPassword)) {
            progressDialog.dismiss();
            Toasty.error(this, "Passwords do not match..", Toast.LENGTH_SHORT, true).show();
            return;
        }

        //progressDialog.show(); // Show the ProgressDialog during registration

        UserModel userModel = new UserModel(userName, userPassword,userEmail, userPhoneNumber,userUniversity,userBloodGroup,userDept);


        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss(); // Dismiss the ProgressDialog once registration is complete

                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();

                    if (user != null) {
                        user.sendEmailVerification().addOnCompleteListener(emailTask -> {
                            if (emailTask.isSuccessful()) {
                                Toasty.success(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT, true).show();
                                Toasty.info(SignupActivity.this, "Verification email sent. Please check your email.", Toast.LENGTH_SHORT, true).show();


                                addDataToFireBase(userModel);

                                auth.signOut();
                                Intent intent = new Intent(SignupActivity.this, UserLoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toasty.error(SignupActivity.this, "Error sending verification email.", Toast.LENGTH_SHORT, true).show();
                            }
                        });
                    }
                } else {
                    Toasty.error(getApplicationContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    private void addDataToFireBase( UserModel userModel) {
        uid=FirebaseAuth.getInstance().getUid();
        databaseReference.child(uid).setValue(userModel);
        databaseReference1.child(userUniversity).child(uid).setValue(userModel);
        databaseReference2.child(userDept).child(uid).setValue(userModel);
        databaseReference3.child(userBloodGroup).child(uid).setValue(userModel);
        databaseReference4.child(userUniversity).child(userBloodGroup).child(uid).setValue(userModel);
        databaseReference5.child(userUniversity).child(userDept).child(uid).setValue(userModel);
        databaseReference6.child(userUniversity).child(userDept).child(userBloodGroup).child(uid).setValue(userModel);
        databaseReference7.child(userDept).child(userBloodGroup).child(uid).setValue(userModel);



        Toasty.success(this, "Information Added Successfully", Toast.LENGTH_SHORT, true).show();
    }

}