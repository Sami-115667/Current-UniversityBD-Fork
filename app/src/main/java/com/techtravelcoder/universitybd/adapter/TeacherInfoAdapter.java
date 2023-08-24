package com.techtravelcoder.universitybd.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.intent.ImplicitIntentActivity;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;
import com.techtravelcoder.universitybd.service.TeachersInfoService;

import de.hdodenhof.circleimageview.CircleImageView;


public class TeacherInfoAdapter extends FirebaseRecyclerAdapter<TeacherInfoModel,TeacherInfoAdapter.MyViewHolder> {

    FirebaseRecyclerOptions<TeacherInfoModel> options;


    public TeacherInfoAdapter(@NonNull FirebaseRecyclerOptions<TeacherInfoModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull TeacherInfoModel model) {

        holder.name.setText(model.getName());
        holder.unidept.setText(model.getDepartment());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhone());
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);

        holder.emailService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model != null && holder.emailService != null) {
                    String to = model.getEmail();
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", to, null));

                    try {
                        v.getContext().startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    } catch (ActivityNotFoundException e) {
                        // Handle the case where no activity can handle the intent
                        Toast.makeText(v.getContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        // Handle other exceptions
                        e.printStackTrace();
                    }
                }
            }
        });
        holder.callService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = model.getPhone();
                if(!phoneNumber.equals("")){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                    v.getContext().startActivity(callIntent);
                }else{
                    Toast.makeText(v.getContext(), "Phone Number is Missing..", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_info_design,parent,false);
        return new MyViewHolder(view);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,unidept,phone,email;
        CircleImageView img;
        ImageView emailService,callService;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.teacher_name);
            unidept=itemView.findViewById(R.id.teacher_dept);
            email=itemView.findViewById(R.id.teacher_email);
            phone=itemView.findViewById(R.id.teacher_number);
            img=itemView.findViewById(R.id.teacher_image);
            emailService=itemView.findViewById(R.id.teacher_info_email);
            callService=itemView.findViewById(R.id.techer_info_call);


        }
    }

}
