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
import com.example.project1.namecard.ImageSelection;
import com.example.project1.namecard.about;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<JSONObject> arrayList;
    private ArrayList<Drawable> drawableArrayList;
    Context context;

    public Adapter(Context context) {
        this.context = context;
        arrayList = new ArrayList<>();
        drawableArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.itempanel, parent, false);

        ViewHolder viewholder = new ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JSONObject jsonData = arrayList.get(position);
        try {
            holder.text_name.setText(jsonData.getString("name"));
            holder.text_phonenumber.setText(jsonData.getString("phonenumber"));
            holder.imageview.setImageDrawable(drawableArrayList.get(position));
        } catch (JSONException e) {e.printStackTrace();}

        //전화번호부 클릭했을 때 반응
        holder.text_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedPhonebook.class);
                intent.putExtra("name", holder.text_name.getText());
                intent.putExtra("phonenumber", holder.text_phonenumber.getText());
                intent.putExtra("profilenumber", Integer.toString(holder.getAdapterPosition()));
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });
        holder.text_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedPhonebook.class);
                intent.putExtra("name", holder.text_name.getText());
                intent.putExtra("phonenumber", holder.text_phonenumber.getText());
                intent.putExtra("profilenumber", Integer.toString(holder.getAdapterPosition()));
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedPhonebook.class);
                intent.putExtra("name", holder.text_name.getText());
                intent.putExtra("phonenumber", holder.text_phonenumber.getText());
                intent.putExtra("profilenumber", Integer.toString(holder.getAdapterPosition()));
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // 데이터를 입력
    public void setArrayData(JSONObject jsonData, Drawable drawable) {
        arrayList.add(jsonData);
        drawableArrayList.add(drawable);
    }
}