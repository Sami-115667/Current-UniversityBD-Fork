package com.techtravelcoder.universitybd.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.StudentDetailsFragmentAdapter;
import com.techtravelcoder.universitybd.connection.NetworkChangeListener;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;

import java.util.ArrayList;
import java.util.List;


public class StudentDetailsFragment extends Fragment {

    NetworkChangeListener networkChangeListener =new NetworkChangeListener();
    private RecyclerView recyclerView;
    String userUniversity,userDept,userBlood;

    FirebaseDatabase firebaseDatabase;
    UserModel userModel;
    DatabaseReference databaseReference;
    ArrayList<UserModel> itemlist ;
    private LottieAnimationView lottieAnimationView;
    StudentDetailsFragmentAdapter studentDetailsFragmentAdapter;
    String[] countries = {
            "University of Dhaka",
            "University of Barisal",
            "Chittagong University",
            "Jahangirnagar University",
            "Rajshahi University",
            "Khulna University",
            "Islamic University, Bangladesh",
            "Comilla University",
            "Bangladesh Open University",
            "Jagannath University",
            "Jatiya Kabi Kazi Nazrul Islam University",
            "Begum Rokeya University, Rangpur",
            "BUP",
            "Rabindra University, Bangladesh",
            "Sheikh Hasina University",
            "North South University",
            "University of Science and Technology Chittagong",
            "Independent University, Bangladesh",
            "AUST",
            "AIUB",
            "University of Asia Pacific",
            "Asian University of Bangladesh",
            "BRAC University",
            "Leading University",
            "Bangladesh Open University",
            "Sylhet International University",
            "Daffodil International University",
            "Green University of Bangladesh",
            "Bangladesh University of Professionals",
            "Rabindra University, Bangladesh",
            "Sonargaon University",
            "Chittagong Independent University",
            "Royal University of Dhaka",
            "Uttara University",
            "Northern University Bangladesh",
            "Stamford University Bangladesh",
            "BSMRAU",
            "Shere Bangla Agricultural University",
            "JKKNIU",
            "CVASU",
            "Sylhet Agricultural University",
            "Khulna Agricultural University",
            "Hobiganj Agricultural University",
            "Kurigram Agricultural University",
            "BAU",
            "BUET",
            "RUET",
            "KUET",
            "CUET",
            "IUT",
            "DUET",
            "BUTEX",
            "SUST",
            "HSTU",
            "MBSTU",
            "PSTU",
            "NSTU",
            "JUST",
            "PUST",
            "RSTU",
            "BSFMSTU"
    };



    public StudentDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_student_details, container, false);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.service_bar));
        }

        // Configure the Toolbar
        lottieAnimationView=view.findViewById(R.id.loadingViewStudentDetailsLottie);
        Toolbar tool = view.findViewById(R.id.all_community_tolbar);
        TextView search=view.findViewById(R.id.community_search);

        lottieAnimationView.playAnimation();

        ((AppCompatActivity) requireActivity()).setSupportActionBar(tool);

        // Set navigation action
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        itemlist=new ArrayList<>();
        recyclerView=view.findViewById(R.id.student_details_fragment_rv_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.auto_tv_id);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, countries);
//        autoCompleteTextView.setAdapter(adapter);


        databaseReference=FirebaseDatabase.getInstance().getReference("User Information");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemlist.clear();

                //list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    //Toast.makeText(CRUDNewsActivity.this, ""+catagory, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(CRUDNewsActivity.this, ""+key, Toast.LENGTH_SHORT).show();

                    userModel = dataSnapshot.getValue(UserModel.class);
                    if(userModel != null){

                        itemlist.add(userModel);

                    }

                    //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                }

                lottieAnimationView.setVisibility(View.GONE);
                studentDetailsFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        studentDetailsFragmentAdapter=new StudentDetailsFragmentAdapter(itemlist);
        recyclerView.setAdapter(studentDetailsFragmentAdapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communityAlertDialogue();
            }
        });




        return view;
    }

    private void communityAlertDialogue() {
        AlertDialog.Builder obj = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.community_allert_design, null);
        obj.setView(dialogView);
        obj.setTitle("Search by Category");
        obj.setMessage("সব কম্বিনেশন এই Search করতে পারবেন, আপনার প্রয়োজন অনুযায়ী Category Select করুন, ১ টা, ২ টা বা ৩ টা।");

        AppCompatSpinner e_uni = dialogView.findViewById(R.id.edit_uni_id);
        AppCompatSpinner deptList = dialogView.findViewById(R.id.edit_dept_id);
        AppCompatSpinner e_blood = dialogView.findViewById(R.id.edit_blood_id);
        TextView searchText = dialogView.findViewById(R.id.search_id);

        List<String> uniName,uniDept,uniBlood;

        uniName=new ArrayList<>();
        uniName.add("Choose a University");
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

        ArrayAdapter uniAdapter= new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniName);
        e_uni.setAdapter(uniAdapter);
        e_uni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userUniversity= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Please Choose a Category", Toast.LENGTH_SHORT).show();

            }
        });


        uniDept = new ArrayList<>();
        uniDept.add("Choose a Department");
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



        ArrayAdapter deptAdapter= new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, uniDept);
        deptList.setAdapter(deptAdapter);
        deptList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userDept= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Please Choose a Department", Toast.LENGTH_SHORT).show();

            }
        });

        uniBlood=new ArrayList<>();


        uniBlood.add("Choose a Blood Group");
        uniBlood.add("A+");uniBlood.add("A-");uniBlood.add("O+");uniBlood.add("O-");
        uniBlood.add("B+");uniBlood.add("B-");uniBlood.add("AB+");uniBlood.add("AB-");

        ArrayAdapter bloodAdapter= new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,uniBlood);
        e_blood.setAdapter(bloodAdapter);
        e_blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userBlood= (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Please Choose a Department", Toast.LENGTH_SHORT).show();

            }
        });







        // e_uni.setText("Khulna University");

       // Toast.makeText(getContext(), ""+university, Toast.LENGTH_SHORT).show();




        AlertDialog alertDialog = obj.create();
        alertDialog.show();
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String uni=e_uni.getText().toString();


                Toast.makeText(getContext(), ""+userUniversity, Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), ""+userDept, Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), ""+userBlood, Toast.LENGTH_SHORT).show();

                itemlist=new ArrayList<>();

                if(!userUniversity.equals("Choose a University") && userDept.equals("Choose a Department") && userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Search").child("Uni").child(userUniversity);

                }
                else if(userUniversity.equals("Choose a University") && !userDept.equals("Choose a Department") && userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Search").child("Dept").child(userDept);

                }
                else if(userUniversity.equals("Choose a University") && userDept.equals("Choose a Department") && !userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Search").child("Blood").child(userBlood);

                }

                else if(!userUniversity.equals("Choose a University") && !userDept.equals("Choose a Department") && userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Search").child("UniDept").child(userUniversity).child(userDept);

                }
                else if(!userUniversity.equals("Choose a University") && userDept.equals("Choose a Department") && !userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Search").child("UniBlood").child(userUniversity).child(userBlood);

                }
                else if(userUniversity.equals("Choose a University") && !userDept.equals("Choose a Department") && !userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Search").child("DeptBlood").child(userDept).child(userBlood);

                }

                else if(!userUniversity.equals("Choose a University") && !userDept.equals("Choose a Department") && !userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("Search").child("UniDeptBlood").child(userUniversity).child(userDept).child(userBlood);

                }
                else if(userUniversity.equals("Choose a University") && userDept.equals("Choose a Department") && userBlood.equals("Choose a Blood Group")){
                    databaseReference=FirebaseDatabase.getInstance().getReference("User Information");

                }





                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        itemlist.clear();

                        //list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                            //Toast.makeText(CRUDNewsActivity.this, ""+catagory, Toast.LENGTH_SHORT).show();
                            // Toast.makeText(CRUDNewsActivity.this, ""+key, Toast.LENGTH_SHORT).show();

                            userModel = dataSnapshot.getValue(UserModel.class);
                            if(userModel != null){

                                itemlist.add(userModel);

                            }

                            //Toast.makeText(NewsActivity.this, ""+newsModel.getAuthor(), Toast.LENGTH_SHORT).show();


                        }

                        studentDetailsFragmentAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                studentDetailsFragmentAdapter=new StudentDetailsFragmentAdapter(itemlist);
                recyclerView.setAdapter(studentDetailsFragmentAdapter);
                alertDialog.dismiss();





            }
        });


    }

    @Override
    public void onStart() {
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getContext().registerReceiver(networkChangeListener,intentFilter);
        super.onStart();
    }

    @Override
    public void onStop() {
        getContext().unregisterReceiver(networkChangeListener);
        super.onStop();
    }

}

