package com.e.project3130.StudentPage.TimeTable;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.e.project3130.R;


/**
 * Author Ziwei Wang & Guoyu
 * Create Date: 2019/2/26
 *Description: Student View the course for specific term. and view the spot left
 *
 **/
public class CourseListHolder extends RecyclerView.ViewHolder {

    public TextView code;
    public TextView title;
    public TextView spot;

    public CourseListHolder(View view){
        super(view);
        code = view.findViewById(R.id.code_in_course_list);
        title = view.findViewById(R.id.title_in_course_list);
        spot = view.findViewById(R.id.max_spot_in_course_list);

    }
}
