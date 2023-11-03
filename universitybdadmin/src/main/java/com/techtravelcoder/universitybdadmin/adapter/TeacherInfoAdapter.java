
package com.techtravelcoder.universitybdadmin.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.database.FirebaseDatabase;
import com.techtravelcoder.universitybdadmin.FirstActivity;
import com.techtravelcoder.universitybdadmin.R;
import com.techtravelcoder.universitybdadmin.model.TeacherInfoModel;
import com.techtravelcoder.universitybdadmin.newspaper.CRUDNewsActivity;
import com.techtravelcoder.universitybdadmin.newspaper.NewsCategoryActivity;
import com.techtravelcoder.universitybdadmin.teacherinfo.ShowAndDeleteActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherInfoAdapter extends RecyclerView.Adapter<TeacherInfoAdapter.MyViewHolder> {


    Context context;
    ArrayList<TeacherInfoModel> list2;


    public TeacherInfoAdapter(Context context, ArrayList<TeacherInfoModel> list2) {
        this.context = context;
        this.list2 = list2;
    }


    @NonNull
    @Override
    public TeacherInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sd_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherInfoAdapter.MyViewHolder holder, int position) {
        TeacherInfoModel obj=list2.get(position);
        holder.name.setText(obj.getName());
        holder.unidept.setText(obj.getDept());
        holder.email.setText(obj.getGmail());
        holder.phone.setText(obj.getPhone());
        Glide.with(holder.img.getContext()).load(obj.getImage()).into(holder.img);

        holder.emailService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (obj != null && holder.emailService != null) {
                    String to = obj.getGmail();
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", to, null));

                    try {
                        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    } catch (ActivityNotFoundException e) {

                        Toast.makeText(v.getContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        holder.callService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = obj.getPhone();
                if(!phoneNumber.equals("")){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                    context.startActivity(callIntent);
                }else{
                    Toast.makeText(v.getContext(), "Phone Number is Missing..", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogView = inflater.inflate(R.layout.teacher_info_update_design, null);
                dialogBuilder.setView(dialogView);

                EditText e_name = dialogView.findViewById(R.id.update_name_id);
                EditText e_dept = dialogView.findViewById(R.id.update_dept_id);
                EditText e_gmail = dialogView.findViewById(R.id.update_gmail_id);
                EditText e_mobile = dialogView.findViewById(R.id.update_mobile_id);
                EditText e_image = dialogView.findViewById(R.id.update_iamge_id);
                EditText e_bio =dialogView.findViewById(R.id.update_desc_id);
                AppCompatButton teacherInfoUpdate=dialogView.findViewById(R.id.teacherUpdateId);

                e_name.setText(obj.getName());
                e_dept.setText(obj.getDept());
                e_gmail.setText(obj.getGmail());
                e_image.setText(obj.getImage());
                e_mobile.setText(obj.getPhone());
                e_bio.setText(obj.getDescription());
                AlertDialog alertDialog = dialogBuilder.create();
                //Toast.makeText(context, "Rakib"+obj.getKey(), Toast.LENGTH_SHORT).show();
                alertDialog.show();

                teacherInfoUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map=new HashMap<>();
                        map.put("name",e_name.getText().toString());
                        map.put("dept",e_dept.getText().toString());
                        map.put("gmail",e_gmail.getText().toString());
                        map.put("phone",e_mobile.getText().toString());
                        map.put("image",e_image.getText().toString());
                        map.put("description",e_bio.getText().toString());
                        ShowAndDeleteActivity showAndDeleteActivity= new ShowAndDeleteActivity();

                        FirebaseDatabase.getInstance().getReference().child("TeacherInformation").child(obj.getUniName()).child(obj.getKey())
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

                        Intent intent = new Intent(context, FirstActivity.class);
                        context.startActivity(intent);
                        Activity activity=new Activity();
                        activity.finish();

                        //((Activity)context).finish();




                    }



                });
            }

        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Are you delete this teacher information");
                builder.setMessage("Data can't be undo ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference("TeacherInformation").child(obj.getUniName()).
                                child(obj.getKey()).removeValue();

                        Toast.makeText(context, "Delete Successful...", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, FirstActivity.class);
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
        return list2.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,unidept,phone,email;
        CircleImageView img;
        ImageView emailService,callService;
        AppCompatButton update,delete ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.teacher_name);
            unidept=itemView.findViewById(R.id.teacher_dept);
            email=itemView.findViewById(R.id.teacher_email);
            phone=itemView.findViewById(R.id.teacher_number);
            img=itemView.findViewById(R.id.teacher_image);
            emailService=itemView.findViewById(R.id.teacher_info_email);
            callService=itemView.findViewById(R.id.techer_info_call);
            update=itemView.findViewById(R.id.tiUpdateId);
            delete=itemView.findViewById(R.id.tiDeleteId);
        }
    }
}