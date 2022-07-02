package com.example.project1.namecard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

import java.util.ArrayList;

public class NamecardAdapter extends RecyclerView.Adapter<NamecardViewHolder> {
    private ArrayList<Drawable> imageArrayList;
    private ArrayList<String> textArrayList;

    Context context;
    public NamecardAdapter(Context context) {
        this.context = context;
        imageArrayList = new ArrayList<Drawable>();
        textArrayList = new ArrayList<String>();
    }

    @NonNull
    @Override
    public NamecardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.namecardpanel, parent, false);

        NamecardViewHolder namecardViewHolder = new NamecardViewHolder(context, view);

        return namecardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NamecardViewHolder holder, int position) {
        Drawable drawable = imageArrayList.get(position);

        holder.imageview.setImageDrawable(drawable);

        String string = textArrayList.get(position);

        holder.textView.setText(string);
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //about에다가 선정한 이미지 보내주기
//                Intent intent = new Intent(v.getContext(), about.class);
//                intent.putExtra("drawable_number", Integer.toString(holder.getAdapterPosition()+1));
//                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });

    }

    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    // 데이터를 입력
    public void setImageArrayData(Drawable drawable) {
        imageArrayList.add(drawable);
    }
    public void setTextArrayData(String string) {
        textArrayList.add(string);
    }
}