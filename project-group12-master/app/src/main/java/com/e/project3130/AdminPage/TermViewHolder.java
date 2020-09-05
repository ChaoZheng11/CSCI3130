package com.e.project3130.AdminPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.project3130.R;

/**
 * Author Jiaxiang Liu & Chao Zheng
 * Create Date: 2019/3/23
 *Description: Sort and list the terms and list information of them.
 *
 **/
public class TermViewHolder extends RecyclerView.ViewHolder {
    public TextView term;
    public Button enter;
    public Button drop;

    public TermViewHolder(View view)
    {
        super(view);
        term = view.findViewById(R.id.Termyear);
        enter = view.findViewById(R.id.enterButton);
        drop = view.findViewById(R.id.dropButton);
    }
}
