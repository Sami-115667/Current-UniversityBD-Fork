package com.techtravelcoder.universitybd.adapter;

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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentDetailsFragmentAdapter extends RecyclerView.Adapter<StudentDetailsFragmentAdapter.AnViewHolder> {

    ArrayList<UserModel> list ;

    public StudentDetailsFragmentAdapter(ArrayList<UserModel> list) {
        this.list = list;
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
        holder.dept.setText(obj.getUserDept());
        holder.uni.setText(obj.getUserUniversity());
        holder.bloodGroup.setText(obj.getUserBloodGroup());
        holder.hallName.setText(obj.getUserHall());
        holder.roomNumber.setText(obj.getUserRoom());

        if(obj.getImage1() != null){
            Glide.with(holder.userImage.getContext()).load(obj.getImage1()).into(holder.userImage);
        }

        boolean visible=obj.isVisible();
        holder.ll.setVisibility(visible ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public class AnViewHolder extends RecyclerView.ViewHolder {
        TextView name,uni,dept,bloodGroup,hallName,roomNumber ;
        CircleImageView userImage ;
        LinearLayout ll,inu;
        public AnViewHolder(@NonNull View itemView) {
            super(itemView);

            inu=itemView.findViewById(R.id.sd_ll);
            ll=itemView.findViewById(R.id.student_ll);
            name=itemView.findViewById(R.id.sd_name_id_tv);
            uni=itemView.findViewById(R.id.sd_university_id_tv);
            dept=itemView.findViewById(R.id.sd_dept_id_tv);
            bloodGroup=itemView.findViewById(R.id.sd_blood_id_tv);
            hallName=itemView.findViewById(R.id.sd_hall_id_tv);
            roomNumber=itemView.findViewById(R.id.sd_room_id_tv);
            userImage=itemView.findViewById(R.id.user_image_id);

            inu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   UserModel userModel=list.get(getAbsoluteAdapterPosition());
                   userModel.setVisible(!userModel.isVisible());
                   notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
