package com.techtravelcoder.universitybd.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;

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

                String to = model.getEmail();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",to, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");

                try {
                    v.getContext().startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(v.getContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
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
        TextView emailService;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.teacher_name);
            unidept=itemView.findViewById(R.id.teacher_dept);
            email=itemView.findViewById(R.id.teacher_email);
            phone=itemView.findViewById(R.id.teacher_number);
            img=itemView.findViewById(R.id.teacher_image);
            emailService=itemView.findViewById(R.id.teacher_info_email);


        }
    }
}
