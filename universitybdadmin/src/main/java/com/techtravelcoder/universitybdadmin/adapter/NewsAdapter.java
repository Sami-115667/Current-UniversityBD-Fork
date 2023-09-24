package com.techtravelcoder.universitybdadmin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.model.NewsModel;
import com.techtravelcoder.universitybdadmin.newspaper.CRUDNewsActivity;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewViewHolder> {

    Context context;
    ArrayList<NewsModel>list;

    public NewsAdapter(Context context, ArrayList<NewsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewsAdapter.NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_design,parent,false);

        return new NewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewViewHolder holder, int position) {
        NewsModel obj=list.get(position);
        holder.title.setText(obj.getTitle());
        holder.desc.setText(obj.getDescription());
        holder.author.setText(obj.getAuthor());
        Glide.with(holder.newsImage.getContext()).load(obj.getImage()).into(holder.newsImage);
        holder.date.setText(obj.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {
        TextView author,title,desc,date ;
        ImageView newsImage ;
        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            author=itemView.findViewById(R.id.newsAuther);
            title=itemView.findViewById(R.id.newsTitle);
            desc=itemView.findViewById(R.id.newsDescription);
            date=itemView.findViewById(R.id.newsDate);
            newsImage=itemView.findViewById(R.id.newsImage);
        }
    }
}
