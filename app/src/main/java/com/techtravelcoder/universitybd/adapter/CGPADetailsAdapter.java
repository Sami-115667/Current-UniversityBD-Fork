package com.techtravelcoder.universitybd.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.activity.NewsActivity;
import com.techtravelcoder.universitybd.activity.ServiceActivity;
import com.techtravelcoder.universitybd.cgpacalculator.CGPADetailsActivity;
import com.techtravelcoder.universitybd.model.CGPADetailsModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CGPADetailsAdapter extends RecyclerView.Adapter<CGPADetailsAdapter.MyViewHolder> {
    double continiousSumCredit=0.0;
    double continousSumGradePoint=0.0;
    public double ans;
    Context context;
    private List<CGPADetailsModel> item;

    public CGPADetailsAdapter(Context context, ArrayList<CGPADetailsModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public CGPADetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cgpa_details_design, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CGPADetailsAdapter.MyViewHolder holder, int position) {
        CGPADetailsModel model= (item.get(position));
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String cre= decimalFormat.format(model.getTotalCredit());

        double creditSum =model.getTotalCredit();
        double gradePointSum=model.getTotalGradePoint();



        continiousSumCredit+=creditSum;
        continousSumGradePoint+=gradePointSum;

        ans=continousSumGradePoint/continiousSumCredit;


        String ans_string=decimalFormat.format(ans);

        holder.credit.setText(cre);
        holder.semester.setText(model.getCurrentSemester());
        holder.totalGrade.setText(decimalFormat.format(model.getTotalGradePoint()));
        holder.cgpa.setText(decimalFormat.format(model.getCurrentCGPA()));
        holder.ansStr.setText(ans_string);
        holder.cgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Are you delete this teacher information");
                builder.setMessage("Data can't be undo ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference("CGPA Details").child(model.getUid()).
                                child(model.getKey()).removeValue();

                        Toast.makeText(context, "Delete Successful...", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, CGPADetailsActivity.class);
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
        return item.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView credit, totalGrade, cgpa, semester ,ansStr;
        AppCompatButton cgDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            credit = itemView.findViewById(R.id.credit_id);
            totalGrade = itemView.findViewById(R.id.grade_point_id);
            cgpa = itemView.findViewById(R.id.cgpa_id);
            semester = itemView.findViewById(R.id.cgpaDetailsSemesterId);
            ansStr=itemView.findViewById(R.id.all_avarage);
            cgDelete=itemView.findViewById(R.id.cg_delete_tv);
        }
    }

}
