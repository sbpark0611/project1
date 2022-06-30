package com.example.project1.phonebook;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageview;
    public TextView text_name;
    public TextView text_phonenumber;

    ViewHolder(Context context, View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.imageView);
        text_name = itemView.findViewById(R.id.text_name);
        text_phonenumber = itemView.findViewById(R.id.text_phonenumber);
    }
}