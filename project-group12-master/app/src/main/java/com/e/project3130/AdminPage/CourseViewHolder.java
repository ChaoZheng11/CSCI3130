package com.e.project3130.AdminPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.project3130.R;

/**
 * Author Ziwei Wang & Guoyu
 * Create Date: 2019/3/9
 *Description: Sort and list the courses and list information of the courses.
 *
 **/
public class CourseViewHolder extends RecyclerView.ViewHolder  {

    public TextView title;
    public TextView code;
    public TextView time;
    public TextView week;
    public TextView grade;
    public Button drop;

    public Button detailsButton;

    public CourseViewHolder(View view)
    {
        super(view);
        title = view.findViewById(R.id.courseTitle);
        code = view.findViewById(R.id.courseCode);
        time = view.findViewById(R.id.courseTime);
        week = view.findViewById(R.id.courseWeek);
        drop = view.findViewById(R.id.dropcourse);
        grade = view.findViewById(R.id.studentGrade);
        detailsButton = view.findViewById(R.id.goDetails);
    }




}

