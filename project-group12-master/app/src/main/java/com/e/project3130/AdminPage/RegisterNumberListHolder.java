package com.e.project3130.AdminPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.e.project3130.R;

/**
 * RegisterNumberListHolder.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/25
 *
 * Description: an activity shows courses and part of details
 * as a list to display nubmer of student registered.
 **/
public class RegisterNumberListHolder extends RecyclerView.ViewHolder  {

    public TextView code;
    public TextView title;
    public TextView number;

    public RegisterNumberListHolder(View view)
    {
        super(view);
        title = view.findViewById(R.id.courseTitle);
        code = view.findViewById(R.id.courseCode);
        number = view.findViewById(R.id.numberstudents);


    }




}
