package com.e.project3130.AdminPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.project3130.R;

/**
 * Author Jiaxiang Liu & Chao Zheng
 * Create Date: 2019/3/27
 *Description: Sort and list the terms and list number of students registered.
 *
 **/
public class RegisterHolder extends RecyclerView.ViewHolder {
    public TextView term;
    public Button enter;

    public RegisterHolder(View view)
    {
        super(view);
        term = view.findViewById(R.id.Termyear);
        enter = view.findViewById(R.id.enterButton);
    }
}
