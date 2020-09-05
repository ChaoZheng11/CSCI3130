package com.e.project3130.AdminPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.project3130.R;

/**
 * StudentViewHolder.java
 *
 * Author Kinson Chu & Xin Deng
 * Create Date: 2019/3/30
 *Description: View Holder for student object.
 *
 **/
public class StudentViewHolder extends RecyclerView.ViewHolder  {

    public TextView email;
    public TextView name;
    public Button courseListBtn;

    public StudentViewHolder(View view)
    {
        super(view);
        email = view.findViewById(R.id.studentEmail);
        name = view.findViewById(R.id.studentName);
        courseListBtn = view.findViewById(R.id.courseListBtn);

    }




}

//