package com.techtravelcoder.universitybd.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.techtravelcoder.universitybd.R;

import java.util.ArrayList;
import java.util.List;

public class SemesterActivity extends AppCompatActivity {

    Button button;
    LinearLayout linearLayout;
    List<String> list;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);
        int color= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getColor(R.color.service_bar);
        }
        getWindow().setStatusBarColor(color);

        button=findViewById(R.id.add_button);
        linearLayout=findViewById(R.id.linerar_id);
        list=new ArrayList<>();
        toolbar=findViewById(R.id.semester_toolbarr);
        toolbar.setLogo(R.drawable.header_design);
        toolbar.setTitle("  UniversityCGPA");
        setSupportActionBar(toolbar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addView();
            }
        });

        list.add("Grade");
        list.add("A+");

        list.add("A");
        list.add("A-");
        list.add("B+");
        list.add("B");
        list.add("B-");
        list.add("C");
        list.add("C-");
    }

    private void addView(){
        View cview = getLayoutInflater().inflate(R.layout.cgpa_design,null,false);
        EditText course=cview.findViewById(R.id.course);
        EditText credit=cview.findViewById(R.id.credit);
        AppCompatSpinner grade=cview.findViewById(R.id.spinner);
        ImageView img= cview.findViewById(R.id.back_button);
        //grade.setBackgroundResource(R.drawable.round_gray);

        ArrayAdapter arrayAdapter= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        grade.setAdapter(arrayAdapter);

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
}