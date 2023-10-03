package com.techtravelcoder.universitybdadmin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.model.NewsModel;
import com.techtravelcoder.universitybdadmin.newspaper.CRUDNewsActivity;
import com.techtravelcoder.universitybdadmin.newspaper.NewsCategoryActivity;
import com.techtravelcoder.universitybdadmin.newspaper.NewsPostActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewViewHolder> {

    Context context;
    //NewsModel obj;
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

        //Update code
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogView = inflater.inflate(R.layout.update_popup, null);
                dialogBuilder.setView(dialogView);

                EditText e_author = dialogView.findViewById(R.id.edt1);
                EditText e_title = dialogView.findViewById(R.id.edt2);
                EditText e_date = dialogView.findViewById(R.id.edt3);
                EditText e_image = dialogView.findViewById(R.id.edt4);
                EditText e_description = dialogView.findViewById(R.id.edt5);
                AppCompatButton postUpdate=dialogView.findViewById(R.id.postUpdateId);

                e_author.setText(obj.getAuthor());
                e_title.setText(obj.getTitle());
                e_date.setText(obj.getDate());
                e_image.setText(obj.getImage());
                e_description.setText(obj.getDescription());
                AlertDialog alertDialog = dialogBuilder.create();
                //Toast.makeText(context, "Rakib"+obj.getKey(), Toast.LENGTH_SHORT).show();
                alertDialog.show();
                
                postUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object>map=new HashMap<>();
                        map.put("author",e_author.getText().toString());
                        map.put("title",e_title.getText().toString());
                        map.put("date",e_date.getText().toString());
                        map.put("image",e_image.getText().toString());
                        map.put("description",e_description.getText().toString());
                        CRUDNewsActivity crudNewsActivity= new CRUDNewsActivity();

                        FirebaseDatabase.getInstance().getReference().child("News").
                                child(obj.getCatagory())
                                .child(obj.getKey())
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                     //   Toast.makeText(context, "Successfully Update", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                       // Toast.makeText(context, "Something wrong...", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();

                                    }
                                });

                        FirebaseDatabase.getInstance().getReference().child("News").
                                child("All News")
                                .child(obj.getKey())
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                         Toast.makeText(context, "Successfully Update", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Something wrong...", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                });
                        Intent intent = new Intent(context, NewsCategoryActivity.class);
                        context.startActivity(intent);



                    }



                });
            }

        });

        //Delete Code
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Are you delete this teacher information");
                builder.setMessage("Data can't be undo ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                          FirebaseDatabase.getInstance().getReference("News").child(obj.getCatagory()).
                                  child(obj.getKey()).removeValue();

                        FirebaseDatabase.getInstance().getReference("News").child("All News").
                                child(obj.getKey()).removeValue();
                        Toast.makeText(context, "Delete Successful...", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, NewsCategoryActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });




    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {
        TextView author,title,desc,date ;
        ImageView newsImage ;
        AppCompatButton update,delete ;
        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            author=itemView.findViewById(R.id.newsAuther);
            title=itemView.findViewById(R.id.newsTitle);
            desc=itemView.findViewById(R.id.newsDescription);
            date=itemView.findViewById(R.id.newsDate);
            newsImage=itemView.findViewById(R.id.newsImage);
            update=itemView.findViewById(R.id.updateId);
            delete=itemView.findViewById(R.id.deleteId);
        }
    }
}
