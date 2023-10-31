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

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter< NewsAdapter.MyViewHolder> {

    Context context;
     ;
    ArrayList<NewsModel>list;
     public NewsAdapter(Context context,ArrayList<NewsModel>list){
         this.context=context;
         this.list=list;
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
       holder.author.setText(obj.getAuthor());
       Glide.with(holder.newsImage.getContext()).load(obj.getImage()).into(holder.newsImage);
       holder.date.setText(obj.getDate());
      // Toast.makeText(context, ""+obj.getUid(), Toast.LENGTH_SHORT).show();


        //  sliderSupport();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailsNewsActivity.class);
                intent.putExtra("author",obj.getAuthor());
                intent.putExtra("title",obj.getTitle());
                intent.putExtra("desc",obj.getDescription());
                intent.putExtra("date",obj.getDate());
                intent.putExtra("image",obj.getImage());
                intent.putExtra("postAutherUid",obj.getUid());

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
        TextView author,title,desc,date ;
        ImageView newsImage ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            author=itemView.findViewById(R.id.newsAuther);
            title=itemView.findViewById(R.id.newsTitle);
            desc=itemView.findViewById(R.id.newsDescription);
            date=itemView.findViewById(R.id.newsDate);
            newsImage=itemView.findViewById(R.id.newsImage);


        }
    }
}
