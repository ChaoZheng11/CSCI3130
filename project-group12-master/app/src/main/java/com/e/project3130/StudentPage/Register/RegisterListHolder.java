package com.e.project3130.StudentPage.Register;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.e.project3130.R;

/**
 * Author: Ziwei
 * Data: 2019/3/9
 * Description:Holder course title and course code for recycleView
 */

public class RegisterListHolder extends RecyclerView.ViewHolder{
    private View view;

    public RegisterListHolder(View v)
    {
        super(v);
        view = v;
        //title = view.findViewById(R.id.courseName);
        //code = view.findViewById(R.id.courseCRN);
    }

    public void setTitle(String title){
        TextView textView = view.findViewById(R.id.courseName);
        textView.setText(title);
    }


    public void setCode(String code){
        TextView textView = view.findViewById(R.id.courseCRN);
        textView.setText(code);
    }



}
