package com.techtravelcoder.universitybd.cgpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.MainActivity;
import com.techtravelcoder.universitybd.model.CGPAModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SemesterActivity extends AppCompatActivity {

    AppCompatButton button ,cgpaStr;

    LinearLayout linearLayout;
    List<String> list;
    List<String> semesterList;
    Toolbar toolbar;
    Double ans=0.0;

    String semester,key;
    AppCompatSpinner appCompatSpinner;
    Double sum=0.0 ,credit_sum=0.0 ;
    FirebaseAuth firebaseAuth;
    List<View>addView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);
        int color= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getColor(R.color.service_bar);
        }
        getWindow().setStatusBarColor(color);



        addView=new ArrayList<>();
        button=findViewById(R.id.add_button);
        cgpaStr=findViewById(R.id.calculate_cgpa);
        linearLayout=findViewById(R.id.linerar_id);
        list=new ArrayList<>();
        semesterList=new ArrayList<>();
        toolbar=findViewById(R.id.semester_toolbarr);
        toolbar.setLogo(R.drawable.header_design);
        firebaseAuth = FirebaseAuth.getInstance();
        toolbar.setTitle(" UniversityCGPA");


        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addView();
            }
        });


        appCompatSpinner=findViewById(R.id.semester_spinner);
        semesterList.add("Choose Semester");
        semesterList.add("1st Semester");semesterList.add("2nd Semester");semesterList.add("3th Semester");semesterList.add("4th Semester");semesterList.add("5th Semester");semesterList.add("6th Semester");semesterList.add("7th Semester");semesterList.add("8th Semester");
        semesterList.add("9th Semester");semesterList.add("10th Semester");semesterList.add("11th Semester");semesterList.add("12th Semester");

        ArrayAdapter semesterAdapter= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,semesterList);

        appCompatSpinner.setAdapter(semesterAdapter);

        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semester= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SemesterActivity.this, "Please Choose a Semester", Toast.LENGTH_SHORT).show();

            }
        });


        cgpaStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateCGPA();
            }
        });

       //It creates bug
       // Toast.makeText(SemesterActivity.this, ""+semester, Toast.LENGTH_SHORT).show();



        list.add("Grade");
        list.add("A+ (4.00)");
        list.add("A (3.75)");
        list.add("A- (3.5)");
        list.add("B+ (3.25)");
        list.add("B (3.00)");
        list.add("B- (2.75)");
        list.add("C+ (2.50)");
        list.add("C (2.25)");
        list.add("D (2.00)");
        list.add("F (0.00)");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cg_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cg_menu_id){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.result_id){
            Intent intent = new Intent(getApplicationContext(), CGPADetailsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addView(){

        //grade.setBackgroundResource(R.drawable.round_gray);
        View cview = getLayoutInflater().inflate(R.layout.cgpa_design,null,false);
        EditText course=cview.findViewById(R.id.course);
        EditText credit=cview.findViewById(R.id.credit);
        AppCompatSpinner grade=cview.findViewById(R.id.spinner);
        ImageView img= cview.findViewById(R.id.back_button);


        ArrayAdapter arrayAdapter= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        grade.setAdapter(arrayAdapter);
        addView.add(cview);





        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(cview);
            }
        });

        linearLayout.addView(cview);



    }

    private void remove(View v){

        linearLayout.removeView(v);

    }



    private void CalculateCGPA(){

        sum=0.0;
        credit_sum=0.0;



        HashMap<String, Double> gradePoint = new HashMap<>();
        gradePoint.put("A+ (4.00)", 4.00);
        gradePoint.put("A (3.75)", 3.75);
        gradePoint.put("A- (3.5)", 3.5);
        gradePoint.put("B+ (3.25)", 3.25);
        gradePoint.put("B (3.00)", 3.00);
        gradePoint.put("B- (2.75)", 2.75);
        gradePoint.put("C+ (2.50)", 2.50);
        gradePoint.put("C (2.25)", 2.25);
        gradePoint.put("D (2.00)", 2.00);
        gradePoint.put("F (0.00)", 0.00);

        if(semester.equals("Choose Semester")){

            Toasty.info(this, "Please Select a Semester", Toast.LENGTH_SHORT, true).show();
            return;
        }



        // Loop through all the dynamically added views
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childView = linearLayout.getChildAt(i);


            if (childView != null) {
               // Toast.makeText(this, ""+linearLayout.getChildCount(), Toast.LENGTH_SHORT).show();
                EditText creditEditText = childView.findViewById(R.id.credit);
                AppCompatSpinner gradeSpinner = childView.findViewById(R.id.spinner);
                EditText courseText=childView.findViewById(R.id.course);


                // Get the values from the views
                String course =courseText.getText().toString();
                String credit = creditEditText.getText().toString();
                String grade = gradeSpinner.getSelectedItem().toString();


                try{
                    if (credit.isEmpty() ||course.isEmpty() ) {
                        Toasty.info(this, "Please enter credit and course properly ", Toast.LENGTH_SHORT, true).show();
                        return;
                    }

                    Double creditVal=Double.parseDouble(credit);
                    Double gradeVal = gradePoint.get(grade);
                    if (gradeVal != null) {
                        credit_sum+=creditVal;
                        sum+= (creditVal * gradeVal);
                    } else {
                        Toasty.info(this, "Please select a valid grade...", Toast.LENGTH_SHORT, true).show();
                        return;
                    }

                }
                catch (Exception e){
                    Toasty.error(this, "Your Input type is wrong...", Toast.LENGTH_SHORT, true).show();
                    return;
                }




            }

        }
        ans=sum/credit_sum;



        if(sum==0.0 && credit_sum==0.0 ){
            Toasty.info(this, "Please ADD Course Properly.\nReminder : Credit must be grater than zero", Toast.LENGTH_SHORT, true).show();

        }else{

            Toasty.success(this, "Congratulations,You have Successfully Calculated your CGPA..", Toast.LENGTH_SHORT, true).show();


            addDataToFirebase(sum,credit_sum,ans);

            showCustomAlertDialog(sum, credit_sum, ans);

        }



    }

    private void showCustomAlertDialog(double sum, double creditSum, double cgpa) {



        // Create a new AlertDialog Builder
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Inflate the custom layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialogue_cgpa, null);
        TextView sumTextView = dialogView.findViewById(R.id.sum_text_view);
        TextView creditSumTextView = dialogView.findViewById(R.id.credit_sum_text_view);
        TextView cgpaTextView = dialogView.findViewById(R.id.cgpa_text_view);
        AppCompatButton okButton=dialogView.findViewById(R.id.ok_button);
        TextView semesterSet=dialogView.findViewById(R.id.set_semester);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String cgpa1 = decimalFormat.format(cgpa);
        String creditSum1 = decimalFormat.format(creditSum);
        String sum1 = decimalFormat.format(sum);


        // Set the text values for the TextViews in the custom layout
        sumTextView.setText("Total Grade Point : " + sum1);
        creditSumTextView.setText("Total Credit : " + creditSum1);
        cgpaTextView.setText("Current CGPA: " + cgpa1);
        semesterSet.setText(semester);


        // Set the custom layout to the AlertDialog Builder
        alertDialogBuilder.setView(dialogView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(View dView : addView){
                    linearLayout.removeView(dView);
                }
                addView.clear();
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
    }


    private void addDataToFirebase(double sum, double creditSum, double cgpa) {
        CGPAModel cgmodel = new CGPAModel(semester, sum, creditSum, cgpa);
        String uid = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("CGPA Details").child(uid);

        // Use addListenerForSingleValueEvent to add data only once
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                key = databaseRef.push().getKey();
                databaseRef.child(key).setValue(cgmodel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error if necessary
            }
        });
    }


}