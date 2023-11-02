package com.techtravelcoder.universitybd.adapter;

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

import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.HallServiceModel;
import com.techtravelcoder.universitybd.service.HallDetailsActivity;

import java.util.ArrayList;
import java.util.Map;

public class HallServiceAdapter extends RecyclerView.Adapter<HallServiceAdapter.MyHallServiceViewHolder> {
    Context context;
    ArrayList<HallServiceModel>list;



    public HallServiceAdapter(Context context, ArrayList<HallServiceModel> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public HallServiceAdapter.MyHallServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hall_service_design,parent,false);

        return new MyHallServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HallServiceAdapter.MyHallServiceViewHolder holder, int position) {
        HallServiceModel hallServiceModel=list.get(position);

        holder.hallName.setText(hallServiceModel.getCollectUniName());
        holder.uniName.setText(hallServiceModel.getHallName());
//        holder.text11.setText(hallServiceModel.getText1());
//        holder.text22.setText(hallServiceModel.getText2());
//        holder.text33.setText(hallServiceModel.getText3());
//        holder.text44.setText(hallServiceModel.getText4());

        Map<String, String> images = hallServiceModel.getImages();
        Toast.makeText(context, ""+hallServiceModel.getImages(), Toast.LENGTH_SHORT).show();
        String imageKey = "image" + 1;
        String imageUrl = images.get("image1");
        Glide.with(context).load(imageUrl).into(getImageViewByIndex(holder, 1));


        holder.detailsId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected item
                HallServiceModel selectedItem = list.get(position);

                // Create an intent to open the HallDetailsActivity
                Intent intent = new Intent(context, HallDetailsActivity.class);

                // Pass the selected item as an extra
                intent.putExtra("selectedItem", selectedItem);

                // Start the activity
                context.startActivity(intent);
            }
        });










//        for (int i = 1; i <= 5; i++) {
//            String imageKey = "image" + i;
//            String imageUrl = images.get(imageKey);
//
//            if (imageUrl != null) {
//                // Use a library like Picasso to load and display the image
//                Toast.makeText(context, ""+imageUrl, Toast.LENGTH_SHORT).show();
//                Glide.with(context).load(imageUrl).into(getImageViewByIndex(holder, i));
//               // Glide.with(holder.imgMain.getContext()).load(hallServiceModel.getImages()).into(getImageViewByIndex(holder, i));
//              //  Picasso.get().load(imageUrl).into(getImageViewByIndex(holder, i));
//            }
//        }

        //holder.Glide.with(holder.hallImage.getContext()).load(hallServiceModel.getImg()).into(holder.hallImage);

    }

    private ImageView getImageViewByIndex(@NonNull HallServiceAdapter.MyHallServiceViewHolder holder, int index) {
        switch (index) {
            case 1:
                return holder.imgMain;
            case 2:
                return holder.img22;
            case 3:
                return holder.img33;
            case 4:
                return holder.img44;
            case 5:
                return holder.img55;
            default:
                throw new IllegalArgumentException("Invalid index");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHallServiceViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMain,img22,img33,img44,img55;
        TextView hallName,uniName ,text11,text22,text33,text44;
        TextView detailsId;

        public MyHallServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMain=itemView.findViewById(R.id.hall_image_id);
            img22=itemView.findViewById(R.id.img_2);
            img33=itemView.findViewById(R.id.img_3);
            img44=itemView.findViewById(R.id.img_4);
            img55=itemView.findViewById(R.id.img_5);

            text11=itemView.findViewById(R.id.text1_id);
            text22=itemView.findViewById(R.id.text2_id);
            text33=itemView.findViewById(R.id.text3_id);
            text44=itemView.findViewById(R.id.text4_id);
            detailsId=itemView.findViewById(R.id.details_id_hall);



            hallName=itemView.findViewById(R.id.hall_text_id);
            uniName=itemView.findViewById(R.id.hall_uni_id);
        }
    }
}
