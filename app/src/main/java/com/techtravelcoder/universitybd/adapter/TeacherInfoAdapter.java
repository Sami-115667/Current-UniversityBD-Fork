package com.techtravelcoder.universitybd.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.TeacherInfoModel;
import com.techtravelcoder.universitybd.service.TeacherDetailsActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class TeacherInfoAdapter extends RecyclerView.Adapter<TeacherInfoAdapter.MyViewHolder> {

     Context context;
     ArrayList<TeacherInfoModel>list2;

    public TeacherInfoAdapter(Context context, ArrayList<TeacherInfoModel> list) {
        this.context = context;
        this.list2 = list;
    }

    @NonNull
    @Override
    public TeacherInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.teacher_info_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherInfoAdapter.MyViewHolder holder, int position) {
        TeacherInfoModel obj=list2.get(position);
        holder.name.setText(obj.getName());
        holder.unidept.setText(obj.getDept());
        holder.email.setText(obj.getGmail());
        holder.phone.setText(obj.getPhone());
        Glide.with(holder.img.getContext()).load(obj.getImage()).into(holder.img);

        holder.emailService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (obj != null && holder.emailService != null) {
                    String to = obj.getGmail();
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", to, null));

                    try {
                        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    } catch (ActivityNotFoundException e) {

                        Toast.makeText(v.getContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        holder.callService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = obj.getPhone();
                if(!phoneNumber.equals("")){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                    context.startActivity(callIntent);
                }else{
                    Toast.makeText(v.getContext(), "Phone Number is Missing..", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, TeacherDetailsActivity.class);
                intent.putExtra("name",obj.getName());
                intent.putExtra("phone",obj.getPhone());
                intent.putExtra("dept",obj.getDept());
                intent.putExtra("image",obj.getImage());
                intent.putExtra("bio",obj.getDescription());
                intent.putExtra("gmail",obj.getGmail());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public void filterList(ArrayList<TeacherInfoModel> filterlist){
        list2=filterlist;
        notifyDataSetChanged();
    }

    //Innner class of Adapter class
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
