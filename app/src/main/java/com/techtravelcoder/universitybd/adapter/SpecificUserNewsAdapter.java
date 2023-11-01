package com.techtravelcoder.universitybd.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.firebase.firestore.auth.User;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.cgpacalculator.SemesterActivity;
import com.techtravelcoder.universitybd.model.CGPADetailsModel;
import com.techtravelcoder.universitybd.model.NewsModel;
import com.techtravelcoder.universitybd.model.UserModel;
import com.techtravelcoder.universitybd.user_profile.UserProfileActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpecificUserNewsAdapter extends RecyclerView.Adapter<SpecificUserNewsAdapter.MyViewHolder> {

    Context context;
    int year,month,day;

    ArrayList<NewsModel>list;
    ArrayList<UserModel>list1;


    public SpecificUserNewsAdapter(Context context, ArrayList<NewsModel> list, ArrayList<UserModel> list1) {
        this.context = context;
        this.list = list;
        this.list1 = list1;
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

        EditText e_title = dialogView.findViewById(R.id.ed_title);
        TextView e_date = dialogView.findViewById(R.id.ed_date);
        EditText e_image = dialogView.findViewById(R.id.ed_image);
        EditText e_description = dialogView.findViewById(R.id.ed_desc);
        //AppCompatButton postUpdate=dialogView.findViewById(R.id.postUpdateId);

        Toast.makeText(context, ""+obj.getCategory(), Toast.LENGTH_SHORT).show();

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
                                Intent intent = new Intent(context, UserProfileActivity.class);
                                context.startActivity(intent);
                                ((Activity) context).finish();
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
        UserModel um =list1.get(position);

        holder.title.setText(obj.getTitle());
        holder.desc.setText(obj.getDescription());
        Glide.with(holder.newsImage.getContext()).load(obj.getImage()).into(holder.newsImage);
        holder.date.setText(obj.getDate());


        holder.nameUser.setText(um.getUserName());
        holder.uniName.setText(um.getUserUniversity());
        Glide.with(holder.pic.getContext()).load(um.getImage1()).into(holder.pic);


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsUpdate(obj);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Are you delete this Post");
                builder.setMessage("Post data can't be undo ");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        FirebaseDatabase.getInstance().getReference("SpecificUserNews").child(obj.getUid()).
                                child(obj.getKey()).removeValue();

                        Toast.makeText(context, "Delete Successful...", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, UserProfileActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish(); // Finish the current activity

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
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc,date ;
        CircleImageView pic;
        TextView nameUser,uniName;
        ImageView newsImage ;
        TextView delete,update;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pic=itemView.findViewById(R.id.sd_user_image);
            nameUser=itemView.findViewById(R.id.sd_user_name);
            uniName=itemView.findViewById(R.id.sd_user_university);


            title=itemView.findViewById(R.id.newsTitle);
            desc=itemView.findViewById(R.id.newsDescription);
            date=itemView.findViewById(R.id.newsDate);
            newsImage=itemView.findViewById(R.id.newsImage);
            update=itemView.findViewById(R.id.updateId1);
            delete=itemView.findViewById(R.id.deleteId);


        }
    }
}
