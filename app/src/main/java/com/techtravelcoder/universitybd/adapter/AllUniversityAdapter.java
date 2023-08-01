package com.techtravelcoder.universitybd.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.ServiceActivity;
import com.techtravelcoder.universitybd.model.UniversityItem;

import java.util.List;

public class AllUniversityAdapter extends RecyclerView.Adapter<AllUniversityAdapter.MyViewHolder> {

    private Context context ;
    private List<UniversityItem>item;

    public AllUniversityAdapter(Context context, List<UniversityItem>item) {
        this.context = context;
        this.item =  item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.all_university_layout_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
         holder.image.setImageResource(item.get(position).getUniversity_image());
         holder.name.setText(item.get(position).getUniversity_name());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String universityName = item.get(position).getUniversity_name().toLowerCase();
                int universityImage = item.get(position).getUniversity_image();
                Intent intent = new Intent(context, ServiceActivity.class);
                intent.putExtra("name", universityName);
                intent.putExtra("image", universityImage);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
              //  Toast.makeText(context, universityName, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.university_image);
            name=itemView.findViewById(R.id.university_name);

        }
    }
}
