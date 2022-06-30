package com.example.project1.gallery;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageview;

    ImageViewHolder(Context context, View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.imagepanel_imageview);
    }
}