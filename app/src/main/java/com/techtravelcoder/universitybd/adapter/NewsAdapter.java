package com.techtravelcoder.universitybd.adapter;

import static androidx.core.content.ContextCompat.startActivity;

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

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.DetailsNewsActivity;
import com.techtravelcoder.universitybd.activity.MainActivity;
import com.techtravelcoder.universitybd.activity.ServiceActivity;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsAdapter extends RecyclerView.Adapter< NewsAdapter.MyViewHolder> {

    Context context;

    ArrayList<NewsModel>list;


    public NewsAdapter(Context context, ArrayList<NewsModel> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View v = LayoutInflater.from(context).inflate(R.layout.news_design,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {


       NewsModel obj=list.get(position);

       holder.title.setText(obj.getTitle());
       holder.desc.setText(obj.getDescription());

       Glide.with(holder.newsImage.getContext()).load(obj.getImage()).into(holder.newsImage);

        holder.date.setText(obj.getDate());


      //  Toast.makeText(context, ""+obj.getUid(), Toast.LENGTH_SHORT).show();
        if(obj.getUid() == null){
            holder.name.setText("Admin Post");
            holder.university.setText("From Admin..");
            holder.userPic.setImageResource(R.drawable.admin);
        }


        if(obj.getUid() != null){
            FirebaseDatabase.getInstance().getReference("User Information").
                    child(obj.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                UserModel userModel=snapshot.getValue(UserModel.class);

                                if(userModel.getUserName() != null && userModel.getImage1()!= null && userModel.getUserUniversity()!= null ){
                                    Glide.with(context).load(userModel.getImage1()).into(holder.userPic);
                                    holder.name.setText(userModel.getUserName());
                                    holder.university.setText(userModel.getUserUniversity());
                                }


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }



        //  sliderSupport();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+obj.getKey(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(context, " "+obj.getPostLike(), Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(context, DetailsNewsActivity.class);
                intent.putExtra("author",obj.getAuthor());
                intent.putExtra("title",obj.getTitle());
                intent.putExtra("desc",obj.getDescription());
                intent.putExtra("date",obj.getDate());
                intent.putExtra("image",obj.getImage());
                intent.putExtra("postAutherUid",obj.getUid());
                intent.putExtra("postCategory",obj.getCategory());
                intent.putExtra("postKey",obj.getKey());
                intent.putExtra("postLike",obj.getPostLike());



                context.startActivity(intent);
            }
        });




    }


    public void filterList(ArrayList<NewsModel>filterlist){
        list=filterlist;
        notifyDataSetChanged();
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc,date,name,university ;
        CircleImageView userPic;
        ImageView newsImage ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.news_design_user_name_id);
            userPic=itemView.findViewById(R.id.news_design_user_image_id);
            university=itemView.findViewById(R.id.news_design_user_uni_id);


            title=itemView.findViewById(R.id.newsTitle);
            desc=itemView.findViewById(R.id.newsDescription);
            date=itemView.findViewById(R.id.newsDate);
            newsImage=itemView.findViewById(R.id.newsImage);


        }
    }
}
