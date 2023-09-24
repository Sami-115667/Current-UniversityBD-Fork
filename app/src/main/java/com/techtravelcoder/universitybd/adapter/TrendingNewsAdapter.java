package com.techtravelcoder.universitybd.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.DetailsNewsActivity;
import com.techtravelcoder.universitybd.activity.NewsActivity;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.TrendingNewsModel;

import java.util.ArrayList;

public class TrendingNewsAdapter extends RecyclerView.Adapter<TrendingNewsAdapter.MyViewHolder> {

    Context context;
    ArrayList<TrendingNewsModel> list1;

    public TrendingNewsAdapter(Context context, ArrayList<TrendingNewsModel> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public TrendingNewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.trending_news_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingNewsAdapter.MyViewHolder holder, int position) {
        TrendingNewsModel obj = list1.get(position);

        holder.tvTitle.setText(obj.getTitle());
        holder.tvDate.setText(obj.getDate());
        Glide.with(holder.tnImage.getContext()).load(obj.getImage()).into(holder.tnImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailsNewsActivity.class);
                intent.putExtra("author",obj.getAuthor());
                intent.putExtra("title",obj.getTitle());
                intent.putExtra("desc",obj.getDescription());
                intent.putExtra("date",obj.getDate());
                intent.putExtra("image",obj.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView tnImage;
        private TextView tvTitle,tvDate ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tnImage=itemView.findViewById(R.id.trendingNesImageId);
            tvTitle=itemView.findViewById(R.id.tnTitleId);
            tvDate=itemView.findViewById(R.id.tnDateId);


        }
    }
}
