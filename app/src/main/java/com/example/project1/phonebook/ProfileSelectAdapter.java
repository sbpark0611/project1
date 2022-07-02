package com.example.project1.phonebook;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.example.project1.gallery.ImageViewHolder;
import com.example.project1.namecard.about;

import java.util.ArrayList;

public class ProfileSelectAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private ArrayList<Drawable> arrayList;
    Context context;
    String profilenumber;
    String name;
    String phonenumber;
    String explanation;
    String caller;

    public ProfileSelectAdapter(Context context, String received_profilenumber, String name, String phonenumber, String explanation, String caller) {
        this.context = context;
        profilenumber = received_profilenumber;
        this.name = name;
        this.phonenumber = phonenumber;
        this.caller = caller;
        this.explanation = explanation;
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
                if(caller.equals("editdetail")){
                    Intent intent = new Intent(v.getContext(), EditDetail.class);
                    intent.putExtra("drawable_number", Integer.toString(holder.getAdapterPosition() + 1));
                    intent.putExtra("name", name);
                    intent.putExtra("phonenumber", phonenumber);
                    intent.putExtra("explanation", explanation);
                    intent.putExtra("profilenumber", profilenumber);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }
                else {
                    Intent intent = new Intent(v.getContext(), phonebook.class);
                    intent.putExtra("drawable_number", Integer.toString(holder.getAdapterPosition() + 1));
                    intent.putExtra("profile_number", profilenumber);
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }
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