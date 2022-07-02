package com.example.project1.namecard;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

public class NamecardViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageview;
    public TextView textView;
    public NamecardViewHolder(Context context, View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.Namecard_Image);
        textView = itemView.findViewById(R.id.Namecard_Text);
    }
}