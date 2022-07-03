package com.example.project1.gallery;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project1.R;

import java.util.ArrayList;

public class UriImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private ArrayList<Uri> uriList;
    Context gallerycontext;

    public UriImageAdapter(ArrayList<Uri> list,Context context) {
        gallerycontext = context;
        uriList = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.imagepanel, parent, false);

        ImageViewHolder imageviewholder = new ImageViewHolder(context, view);

        return imageviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
//        Drawable drawable = arrayList.get(position);
//
//        holder.imageview.setImageDrawable(drawable);

        Uri uri = uriList.get(position);
//        holder.imageview.setImageURI(uri);
        Glide.with(gallerycontext).load(uri).into(holder.imageview);


        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), holder.getAdapterPosition()+"번 눌림", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), UriBigImage.class);
                intent.putExtra("position",position);

                gallerycontext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));

            }
        });

    }

    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }




    // 데이터를 입력
    public void setUriList(Uri uri) {
        uriList.add(uri);
    }

}