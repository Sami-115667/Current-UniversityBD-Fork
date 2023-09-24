package com.techtravelcoder.universitybd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.cgpacalculator.SemesterActivity;
import com.techtravelcoder.universitybd.service.DocumentaryService;
import com.techtravelcoder.universitybd.service.TeachersInfoService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener  {

    ImageView imageView;
    TextView textView1,textView2;
    Spinner specefic_spinner,common_spinner;
    Toolbar toolbar;
    String name ;
    CardView documentary ,teachers_information ,about_hall,students_community ,pdf_books,current_news,cgpa_calculator ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);
        int color= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getColor(R.color.service_bar);
        }
        getWindow().setStatusBarColor(color);

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView=findViewById(R.id.service_image_id);
        textView1=findViewById(R.id.service_text_id);
        toolbar=findViewById(R.id.service_tolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


       // common_spinner=findViewById(R.id.common_spinner_id);

        name = getIntent().getStringExtra("name");
        int image = getIntent().getIntExtra("image",0);
        String iamge_url = getIntent().getStringExtra("imageid");

        //Toast.makeText(this, ""+image, Toast.LENGTH_SHORT).show();

        if(name.equals("null")){

        }
        if(!name.equals(" ")){
            if(name.equals("university of dhaka")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("University Of Dhaka");
            }
            if(name.equals("university of barisal")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("University of Barisal");
            }
            if(name.equals("chittagong university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Chittagong University");
            }
            if(name.equals("jahangirnagar university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Jahangirnag University");
            }
            if(name.equals("rajshahi university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Rajshahi University");
            }
            if(name.equals("khulna university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Khulna University");
            }
            if(name.equals("islamic university, bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Islamic University,Bangladesh");
            }
            if(name.equals("bangladesh open university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangladesh Open University");
            }
            if(name.equals("jagannath university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Jagannath University");
            }
            if(name.equals("jatiya kabi kazi nazrul islam university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Jatiya kabi kazi Nazrul islam University");
            }
            if(name.equals("begum rokeya university, rangpur")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Begum Rokeya University,Rangpur");
            }
            if(name.equals("bangladesh university of professionals")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangladesh University of Professionals");
            }
            if(name.equals("rabindra university, bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Rabindra University Bangladesh");
            }
            if(name.equals("sheikh hasina university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Sheikh Hasina University");
            }
            if(name.equals("buet")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangladesh University of Engineering & Technology");
            }
            if(name.equals("ruet")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Rajshahi University of Engineering & Technology");
            }
            if(name.equals("kuet")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Khulna University of Engineering & Technology");
            }
            if(name.equals("cuet")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Chittagong University of Engineering & Technology");
            }
            if(name.equals("duet")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Dhaka University of Engineering & Technology");
            }
            if(name.equals("iut")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Islamic University of Technology");
            }
            if(name.equals("butex")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangladesh University of Textiles");
            }
            if(name.equals("comilla university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Comilla University");
            }

            if(name.equals("sust")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Shahjalal University of Science & Technology");
            }
            if(name.equals("mbstu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Mawlana Bhashani Science & Technology University");
            }
            if(name.equals("nstu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Noakhali Science & Technology University");
            }
            if(name.equals("just")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Jessore University of Science & Technology");
            }
            if(name.equals("pust")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Pabna University of Science and Technology");
            }
            if(name.equals("rstu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Rangamati Science and Technology University");
            }
            if(name.equals("bsfmstu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangamata Sheikh Fojilatunnesa Mujib Science and Technology University");
            }
            if(name.equals("hstu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Hajee Mohammad Danesh Science & Technology University");
            }
            if(name.equals("pstu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Patuakhali Science and Technology University");
            }
            if(name.equals("north south university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("North South University");
            }
            if(name.equals("independent university, bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Independent University Bangladesh");
            }
            if(name.equals("aust")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("AUST");
            }
            if(name.equals("aiub")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("AIUB");
            }
            if(name.equals("university of asia pacific")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("University of Asia Pacific");
            }
            if(name.equals("asian university of bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Asian University of Bangladesh");
            }
            if(name.equals("brac university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("BRAC University");
            }
            if(name.equals("leading university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Leading University");
            }
            if(name.equals("bangladesh open university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangladesh Open University");
            }
            if(name.equals("sylhet international university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Sylhet International University");
            }
            if(name.equals("daffodil international university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Daffodil International University");
            }
            if(name.equals("green university of bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Green University of Bangladesh");
            }
            if(name.equals("bangladesh university of professionals")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangladesh University Of Professionals");
            }
            if(name.equals("rabindra university, bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Rabindra University");
            }
            if(name.equals("sonargaon university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Sonargoan University");
            }
            if(name.equals("chittagong independent university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Chittagong Independent University");
            }
            if(name.equals("royal university of dhaka")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Royal University Of Dhaka");
            }
            if(name.equals("uttara university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Uttara University");
            }
            if(name.equals("northern university bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Northern University Bangladesh");
            }
            if(name.equals("stamford university bangladesh")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Stamford University Bangladesh");
            }
            if(name.equals("national university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("National University");
            }
            if(name.equals("university of science and technology chittagong")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("University of Science and Technology CHittagong");
            }
            if(name.equals("shere bangla agricultural university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Shere Bangla Agricultural University");
            }
            if(name.equals("bsmrau")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("BSMRAU");
            }
            if(name.equals("jkkniu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("JKKNIU");
            }
            if(name.equals("cvasu")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("CVASU");
            }
            if(name.equals("sylhet agricultural university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Sylhet Agricultural University");
            }
            if(name.equals("khulna agricultural university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Khulna Agricultural University");
            }
            if(name.equals("hobiganj agricultural university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Habiganj Agricultural University");
            }
            if(name.equals("kurigram agricultural university")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Kurigram Agricultural University");
            }
            if(name.equals("bau")){
                if(image==0){
                    Glide.with(this).load(iamge_url).into(imageView);
                }else{
                    imageView.setImageResource(image);

                }
                textView1.setText("Bangladesh Agricultural University");
            }


        }


        manageSpeceficAndCommonService();



       // manageSpinner();







    }




    public void manageSpeceficAndCommonService(){
        documentary=findViewById(R.id.documentary_service_id);
        teachers_information=findViewById(R.id.teachers_info_service_id);
        about_hall=findViewById(R.id.hall_service_id);
        students_community=findViewById(R.id.students_community_service_id);
        pdf_books=findViewById(R.id.pdf_books_service_id);
        current_news=findViewById(R.id.current_news_id);
        cgpa_calculator=findViewById(R.id.cgpa_calculator_service_id);

        documentary.setOnClickListener(this);
        teachers_information.setOnClickListener(this);
        about_hall.setOnClickListener(this);
        students_community.setOnClickListener(this);
        pdf_books.setOnClickListener(this);
        current_news.setOnClickListener(this);
        cgpa_calculator.setOnClickListener(this);






    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.service, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.all_uni_service_home_id){
            Intent intent = new Intent(ServiceActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }














    public void manageSpinner(){

        String[] specefic_service_name={"Hints...","information","Hall News","Teachers Information","Transportation Service"};
        String[] common_service_name={"Hints...","PDF Books","Students Community","News","CGPA Calculator"};
       // specefic_spinner=findViewById(R.id.specefic_service);
        //common_spinner=findViewById(R.id.common_service);


        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,specefic_service_name);
        adapter.setDropDownViewResource(com.denzcoskun.imageslider.R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<String>adapter1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,common_service_name);
        adapter1.setDropDownViewResource(com.denzcoskun.imageslider.R.layout.support_simple_spinner_dropdown_item);
        specefic_spinner.setAdapter(adapter);
        common_spinner.setAdapter(adapter1);


        specefic_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        common_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.documentary_service_id){
            if(name.equals("university of dhaka")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/du-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }
            if(name.equals("university of barisal")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/barisal-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }
            if(name.equals("chittagong university")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/chittagong.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }
            if(name.equals("jahangirnagar university")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/jahangir-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }
            if(name.equals("khulna university")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/khulna-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }if(name.equals("rajshahi university")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/rajshahi-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }if(name.equals("islamic university, bangladesh")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/islamic-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }

            if(name.equals("islamic university, bangladesh")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/islamic-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }


            if(name.equals("comilla university")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/comilla-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }

            if(name.equals("jagannath university")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/jagannath-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }

            if(name.equals("bangladesh university of professionals")){
                Intent intent= new Intent(ServiceActivity.this, DocumentaryService.class);
                String url="file:///android_asset/Information_general/bup-info.html";
                intent.putExtra("key",url);
                startActivity(intent);
            }



        }

        if(v.getId()==R.id.teachers_info_service_id){
             Intent intent= new Intent(getApplicationContext(), TeachersInfoService.class);
             intent.putExtra("key",name);
             startActivity(intent);
        }


        if(v.getId()==R.id.hall_service_id){

        }



        if(v.getId()==R.id.cgpa_calculator_service_id){
            Intent intent=new Intent(getApplicationContext(), SemesterActivity.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.current_news_id){
            Intent intent=new Intent(getApplicationContext(),NewsActivity.class);
            startActivity(intent);
        }
    }
}