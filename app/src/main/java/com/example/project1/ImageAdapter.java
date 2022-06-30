package com.example.project1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private ArrayList<Drawable> arrayList;

    public ImageAdapter() {
        arrayList = new ArrayList<Drawable>();
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
        Drawable drawable = arrayList.get(position);

        holder.imageview.setImageDrawable(drawable);

        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), holder.getAdapterPosition()+"번 눌림", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // 데이터를 입력
    public void setArrayData(Drawable drawable) {
        arrayList.add(drawable);
    }
}