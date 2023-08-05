package com.techtravelcoder.universitybd.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.techtravelcoder.universitybd.model.UserModel;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pageSift;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private String key ;
    private EditText name,email,password,reEnterPassword,phone ;
    private AppCompatButton signup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(AuthUtils.isUserAuthenticated(this)){
            setContentView(R.layout.activity_signup);
            Intent intent = new Intent(SignupActivity.this, UserLoginActivity.class);
            startActivity(intent);
            finish();

        }else {

            pageSift=findViewById(R.id.alreadySignUp);
            auth=FirebaseAuth.getInstance();
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference("User Information");

            name=findViewById(R.id.signUpName);
            email=findViewById(R.id.signUpEmail);
            password=findViewById(R.id.signUpPassword);
            reEnterPassword=findViewById(R.id.signUpReenterPassword);
            phone=findViewById(R.id.signUpName);
            signup=findViewById(R.id.signUpButton);




            pageSift.setOnClickListener(this);
            signup.setOnClickListener(this);

        }





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

        // Input validation
        if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userReenterPassword.isEmpty() || userPhoneNumber.isEmpty()) {
            progressDialog.dismiss();
            Toasty.info(this, "All fields are Required...", Toast.LENGTH_SHORT, true).show();
            return;
        }

        if (!userPassword.equals(userReenterPassword)) {
            progressDialog.dismiss();
            Toasty.error(this, "Passwords do not match..", Toast.LENGTH_SHORT, true).show();
            return;
        }

        //progressDialog.show(); // Show the ProgressDialog during registration

        UserModel userModel = new UserModel(userName, userEmail, userPhoneNumber, userPassword);
        Toast.makeText(this, "Problem fix", Toast.LENGTH_SHORT).show();


        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss(); // Dismiss the ProgressDialog once registration is complete

                if (task.isSuccessful()) {
                   // progressDialog.dismiss();
                    Toasty.success(SignupActivity.this, "Registration Successful..", Toast.LENGTH_SHORT, true).show();
                    FirebaseUser user = auth.getCurrentUser();
                    addDataToFireBase(user, userModel);
                    AuthUtils.saveUserAuthentication(SignupActivity.this);
                    Intent intent = new Intent(SignupActivity.this, UserLoginActivity.class);
                    intent.putExtra("userKey", key);
                    startActivity(intent);
                    finish(); // Optional: Close this activity after registration
                } else {
                    Toasty.error(getApplicationContext(), "Error :"+ task.getException().getMessage(), Toast.LENGTH_SHORT, true).show();

                  //  Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addDataToFireBase(FirebaseUser user, UserModel userModel) {
        key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(userModel);
        Toasty.success(this, "Information Added Successfully", Toast.LENGTH_SHORT, true).show();
    }

}