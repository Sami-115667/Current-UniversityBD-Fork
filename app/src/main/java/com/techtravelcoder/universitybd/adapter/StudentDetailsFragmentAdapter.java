package com.techtravelcoder.universitybd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.UserModel;
import com.techtravelcoder.universitybd.user_profile.UserProfileActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentDetailsFragmentAdapter extends RecyclerView.Adapter<StudentDetailsFragmentAdapter.AnViewHolder> {

    ArrayList<UserModel> list ;
    Context context;

    public StudentDetailsFragmentAdapter(ArrayList<UserModel> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public StudentDetailsFragmentAdapter.AnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_details_design,parent,false);
        return new AnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentDetailsFragmentAdapter.AnViewHolder holder, int position) {

        UserModel obj=list.get(position);
        holder.name.setText(obj.getUserName());
        holder.uni.setText(obj.getUserUniversity());


        if(obj.getImage1() != null){
            Glide.with(holder.userImage.getContext()).load(obj.getImage1()).into(holder.userImage);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, UserProfileActivity.class);
                intent.putExtra("postAutherId",obj.getUserId());
                context.startActivity(intent);




            }
        });


        holder.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, UserProfileActivity.class);
                intent.putExtra("postAutherId",obj.getUserId());
                context.startActivity(intent);

            }
        });

        holder.uni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, UserProfileActivity.class);
                intent.putExtra("postAutherId",obj.getUserId());
                context.startActivity(intent);


            }
        });

        //boolean visible=obj.isVisible();
       // holder.ll.setVisibility(visible ? View.VISIBLE : View.GONE);


    }

    public void searchLists(ArrayList<UserModel> filterlist) {

        list = filterlist;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public class AnViewHolder extends RecyclerView.ViewHolder {
        TextView name,uni ;
        CircleImageView userImage ;
        LinearLayout inu;
        public AnViewHolder(@NonNull View itemView) {
            super(itemView);

            inu=itemView.findViewById(R.id.sd_ll);
            name=itemView.findViewById(R.id.sd_name_id_tv);
            uni=itemView.findViewById(R.id.sd_university_id_tv);
            userImage=itemView.findViewById(R.id.user_image_id);

//            inu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   UserModel userModel=list.get(getAbsoluteAdapterPosition());
//                   userModel.setVisible(!userModel.isVisible());
//                   notifyItemChanged(getAdapterPosition());
//                }
//            });

        }
    }
}
