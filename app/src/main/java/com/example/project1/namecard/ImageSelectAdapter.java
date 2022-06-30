package com.example.project1.namecard;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.example.project1.gallery.BigImage;
import com.example.project1.gallery.ImageViewHolder;

import java.util.ArrayList;

public class ImageSelectAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private ArrayList<Drawable> arrayList;
    Context context;
    public ImageSelectAdapter() {
        this.context = context;
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
//                Toast.makeText(v.getContext(), holder.getAdapterPosition()+"번 눌림", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), about.class);
                intent.putExtra("drawable_number", Integer.toString(holder.getAdapterPosition()+1));
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));


            }
        });

    }

    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
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