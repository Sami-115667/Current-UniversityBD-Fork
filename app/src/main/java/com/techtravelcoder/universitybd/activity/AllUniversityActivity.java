package com.techtravelcoder.universitybd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.AllUniversityAdapter;
import com.techtravelcoder.universitybd.model.UniversityItem;

import java.util.ArrayList;
import java.util.List;

public class AllUniversityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UniversityItem> itemList;
    private AllUniversityAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_university);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int color= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getColor(R.color.yellow);
        }
        getWindow().setStatusBarColor(color);



        Toolbar tool = (Toolbar) findViewById(R.id.all_uni_tolbar);
        setSupportActionBar(tool);

        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        String name_category=getIntent().getStringExtra("name");

        if(!name_category.equals(" ")){
            if (name_category.equals("general")) {
                generalUniversity();
            }

            if (name_category.equals("private")) {
                privateUniversity();
            }
            if (name_category.equals("sat")) {
                satUniversity();
            }
            if (name_category.equals("engineering")) {
                engineeringUniversity();
            }
            if (name_category.equals("agriculture")) {
                agricultureUniversity();
            }
            if (name_category.equals("national")) {
                nationalUniversity();
            }
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.all_uni_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.all_uni_home_id){
            Intent intent = new Intent(AllUniversityActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void nationalUniversity() {
        recyclerView=findViewById(R.id.all_university_rv_id);
        itemList=new ArrayList<>();
        itemList.add(new UniversityItem(R.drawable.image_du,"National University"));


        adapter=new AllUniversityAdapter(getApplicationContext(),itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private void agricultureUniversity() {
        recyclerView=findViewById(R.id.all_university_rv_id);
        itemList=new ArrayList<>();
        itemList.add(new UniversityItem(R.drawable.image_du,"BSMRAU"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"Shere Bangla Agricultural University"));
        itemList.add(new UniversityItem(R.drawable.image_cu,"JKKNIU"));
        itemList.add(new UniversityItem(R.drawable.image_ju,"CVASU"));
        itemList.add(new UniversityItem(R.drawable.image_ru,"Sylhet Agricultural University"));
        itemList.add(new UniversityItem(R.drawable.image_ku,"Khulna Agricultural University"));
        itemList.add(new UniversityItem(R.drawable.image_iu,"Hobiganj Agricultural University"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"Kurigram Agricultural University"));




        adapter=new AllUniversityAdapter(getApplicationContext(),itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private void engineeringUniversity() {
        recyclerView=findViewById(R.id.all_university_rv_id);
        itemList=new ArrayList<>();
        itemList.add(new UniversityItem(R.drawable.image_du,"BUET"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"RUET"));
        itemList.add(new UniversityItem(R.drawable.image_cu,"KUET"));
        itemList.add(new UniversityItem(R.drawable.image_ju,"CUET"));
        itemList.add(new UniversityItem(R.drawable.image_ru,"IUT"));
        itemList.add(new UniversityItem(R.drawable.image_ku,"DUET"));
        itemList.add(new UniversityItem(R.drawable.image_ku,"BUTEX"));

        adapter=new AllUniversityAdapter(getApplicationContext(),itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private void satUniversity() {
        recyclerView=findViewById(R.id.all_university_rv_id);
        itemList=new ArrayList<>();
        itemList.add(new UniversityItem(R.drawable.image_du,"SUST"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"HSTU"));
        itemList.add(new UniversityItem(R.drawable.image_cu,"MBSTU"));
        itemList.add(new UniversityItem(R.drawable.image_ju,"PSTU"));
        itemList.add(new UniversityItem(R.drawable.image_ru,"NSTU"));
        itemList.add(new UniversityItem(R.drawable.image_ku,"JUST"));
        itemList.add(new UniversityItem(R.drawable.image_iu,"PUST"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"RSTU"));
        itemList.add(new UniversityItem(R.drawable.image_komilla,"BSFMSTU"));


        adapter=new AllUniversityAdapter(getApplicationContext(),itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private void privateUniversity() {
        recyclerView=findViewById(R.id.all_university_rv_id);
        itemList=new ArrayList<>();
        itemList.add(new UniversityItem(R.drawable.image_du,"North South University"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"University of Science and Technology Chittagong"));
        itemList.add(new UniversityItem(R.drawable.image_cu,"Independent University, Bangladesh"));
        itemList.add(new UniversityItem(R.drawable.image_ju,"AUST"));
        itemList.add(new UniversityItem(R.drawable.image_ru,"AIUB"));
        itemList.add(new UniversityItem(R.drawable.image_ku,"University of Asia Pacific"));
        itemList.add(new UniversityItem(R.drawable.image_iu,"Asian University of Bangladesh"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"BRAC University"));
        itemList.add(new UniversityItem(R.drawable.image_komilla,"Leading University"));
        itemList.add(new UniversityItem(R.drawable.image_bou,"Bangladesh Open University"));
        itemList.add(new UniversityItem(R.drawable.image_ju,"Sylhet International University"));
        itemList.add(new UniversityItem(R.drawable.image_jkkniu,"Daffodil International University"));
        itemList.add(new UniversityItem(R.drawable.image_begum_rokeya,"Green University of Bangladesh"));
        itemList.add(new UniversityItem(R.drawable.image_bup,"Bangladesh University of Professionals"));
        itemList.add(new UniversityItem(R.drawable.image_rabindra,"Rabindra University, Bangladesh"));
        itemList.add(new UniversityItem(R.drawable.image_seikh_hasina,"Sonargaon University"));
        itemList.add(new UniversityItem(R.drawable.image_seikh_hasina,"Chittagong Independent University"));
        itemList.add(new UniversityItem(R.drawable.image_seikh_hasina,"Royal University of Dhaka"));
        itemList.add(new UniversityItem(R.drawable.image_seikh_hasina,"Uttara University"));
        itemList.add(new UniversityItem(R.drawable.image_seikh_hasina,"Northern University Bangladesh"));
        itemList.add(new UniversityItem(R.drawable.image_seikh_hasina,"Stamford University Bangladesh"));



        adapter=new AllUniversityAdapter(getApplicationContext(),itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    public void generalUniversity(){

        recyclerView=findViewById(R.id.all_university_rv_id);
        itemList=new ArrayList<>();
        itemList.add(new UniversityItem(R.drawable.image_du,"University of Dhaka"));
        itemList.add(new UniversityItem(R.drawable.image_barisal,"University of Barisal"));
        itemList.add(new UniversityItem(R.drawable.image_cu,"Chittagong University"));
        itemList.add(new UniversityItem(R.drawable.image_ju,"Jahangirnagar University"));
        itemList.add(new UniversityItem(R.drawable.image_ru,"Rajshahi University"));
        itemList.add(new UniversityItem(R.drawable.image_ku,"Khulna University"));
        itemList.add(new UniversityItem(R.drawable.image_iu,"Islamic University, Bangladesh"));

        itemList.add(new UniversityItem(R.drawable.image_komilla,"Comilla University"));
        itemList.add(new UniversityItem(R.drawable.image_bou,"Bangladesh Open University"));
        itemList.add(new UniversityItem(R.drawable.image_jagannath,"Jagannath University"));
        itemList.add(new UniversityItem(R.drawable.image_jkkniu,"Jatiya Kabi Kazi Nazrul Islam University"));
        itemList.add(new UniversityItem(R.drawable.image_begum_rokeya,"Begum Rokeya University, Rangpur"));
        itemList.add(new UniversityItem(R.drawable.image_bup,"Begum Rokeya University, Rangpur"));
        itemList.add(new UniversityItem(R.drawable.image_rabindra,"Rabindra University, Bangladesh"));
        itemList.add(new UniversityItem(R.drawable.image_seikh_hasina,"Sheikh Hasina University"));


        adapter=new AllUniversityAdapter(getApplicationContext(),itemList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

    }


}