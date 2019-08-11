package com.adyp.jeniuscontact.util;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {


    private int position;
    private OnItemClickListener onItemClickListener;


    public CustomOnItemClickListener(int position, OnItemClickListener onItemClickListener) {
        this.position = position;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onItemCallback(position, view);

    }

    public interface OnItemClickListener {
        void onItemCallback(int position, View view);

    }
}
