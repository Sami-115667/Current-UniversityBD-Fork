package com.techtravelcoder.universitybd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.model.CGPADetailsModel;

import java.text.DecimalFormat;

public class CGPADetailsAdapter extends FirebaseRecyclerAdapter<CGPADetailsModel, CGPADetailsAdapter.MyViewHolder> {
    double continiousSumCredit=0.0;
    double continousSumGradePoint=0.0;
    public double ans;

    public CGPADetailsAdapter(@NonNull FirebaseRecyclerOptions<CGPADetailsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull CGPADetailsModel model) {

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
       // Toast.makeText(holder.itemView.getContext(), "Continuous CGPA: " + ans_string, Toast.LENGTH_SHORT).show();



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cgpa_details_design, parent, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView credit, totalGrade, cgpa, semester ,ansStr;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            credit = itemView.findViewById(R.id.credit_id);
            totalGrade = itemView.findViewById(R.id.grade_point_id);
            cgpa = itemView.findViewById(R.id.cgpa_id);
            semester = itemView.findViewById(R.id.cgpaDetailsSemesterId);
            ansStr=itemView.findViewById(R.id.all_avarage);
        }
    }
}
