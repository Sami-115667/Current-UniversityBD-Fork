package com.techtravelcoder.universitybd.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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
import com.google.firebase.database.FirebaseDatabase;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.SpecificUserNewsPostDetails;
import com.techtravelcoder.universitybd.activity.UserNewsPostActivity;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.user_profile.UserProfileActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SpecificUserNewsAdapter extends RecyclerView.Adapter<SpecificUserNewsAdapter.MyViewHolder> {

    Context context;
    int year,month,day;

    ArrayList<NewsModel>list;
    public SpecificUserNewsAdapter(Context context,ArrayList<NewsModel>list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public SpecificUserNewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.specific_news_design,parent,false);
        return new MyViewHolder(view);
    }

    private void newsUpdate(NewsModel obj){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.update_news_pop_up, null);
        dialogBuilder.setView(dialogView);

        EditText e_author = dialogView.findViewById(R.id.ed_name);
        EditText e_title = dialogView.findViewById(R.id.ed_title);
        TextView e_date = dialogView.findViewById(R.id.ed_date);
        EditText e_image = dialogView.findViewById(R.id.ed_image);
        EditText e_description = dialogView.findViewById(R.id.ed_desc);
        //AppCompatButton postUpdate=dialogView.findViewById(R.id.postUpdateId);

        Toast.makeText(context, ""+obj.getCategory(), Toast.LENGTH_SHORT).show();

        e_author.setText(obj.getAuthor());
        e_title.setText(obj.getTitle());
        e_date.setText(obj.getDate());
        e_image.setText(obj.getImage());
        e_description.setText(obj.getDescription());
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        final Calendar calendar=Calendar.getInstance();
        e_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        e_date.setText(sdf.format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        AppCompatButton postUpdate=dialogView.findViewById(R.id.post_Id);
        postUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object>map=new HashMap<>();
                map.put("author",e_author.getText().toString());
                map.put("title",e_title.getText().toString());
                map.put("date",e_date.getText().toString());
                map.put("image",e_image.getText().toString());
                map.put("description",e_description.getText().toString());
                ///CRUDNewsActivity crudNewsActivity= new CRUDNewsActivity();

                FirebaseDatabase.getInstance().getReference().child("SpecificUserNews").
                        child(obj.getUid())
                        .child(obj.getKey())
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //Toast.makeText(context, "Successfully Update1", Toast.LENGTH_SHORT).show();
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
                                // Toast.makeText(context, "Successfully Update2", Toast.LENGTH_SHORT).show();
                                //alertDialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Toast.makeText(context, "Something wrong...", Toast.LENGTH_SHORT).show();
                                //alertDialog.dismiss();
                            }
                        });

                FirebaseDatabase.getInstance().getReference().child("News").
                        child(obj.getCategory())
                        .child(obj.getKey())
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Successfully Update", Toast.LENGTH_SHORT).show();

                                alertDialog.dismiss();
                                Intent intent=new Intent(context, UserProfileActivity.class);
                                context.startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Something wrong...", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                            }
                        });

            }



        });
    }

    @Override
    public void onBindViewHolder(@NonNull SpecificUserNewsAdapter.MyViewHolder holder, int position) {
        NewsModel obj=list.get(position);
        holder.title.setText(obj.getTitle());
        holder.desc.setText(obj.getDescription());
        holder.author.setText(obj.getAuthor());
        Glide.with(holder.newsImage.getContext()).load(obj.getImage()).into(holder.newsImage);
        holder.date.setText(obj.getDate());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsUpdate(obj);

            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView author,title,desc,date ;
        ImageView newsImage ;
        AppCompatButton update,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            author=itemView.findViewById(R.id.newsAuther);
            title=itemView.findViewById(R.id.newsTitle);
            desc=itemView.findViewById(R.id.newsDescription);
            date=itemView.findViewById(R.id.newsDate);
            newsImage=itemView.findViewById(R.id.newsImage);
            update=itemView.findViewById(R.id.updateId1);
            delete=itemView.findViewById(R.id.deleteId);


        }
    }
}
