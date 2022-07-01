package com.example.project1.namecard;


import android.content.Context;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.example.project1.phonebook.ViewHolder;
import com.example.project1.phonebook.phonebook;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NameSelectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<JSONObject> arrayList;
    Context context;
    public NameSelectAdapter(Context context) {
        arrayList = new ArrayList<>();
        this.context = context;
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
        } catch (JSONException e) {e.printStackTrace();}

        //전화번호부 클릭했을 때 반응
        holder.text_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), about.class);
                intent.putExtra("name", holder.text_name.getText().toString());
                intent.putExtra("phonenumber", holder.text_phonenumber.getText().toString());
                context.startActivity(intent);
            }
        });
        holder.text_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), about.class);
                intent.putExtra("name", holder.text_name.getText().toString());
                intent.putExtra("phonenumber", holder.text_phonenumber.getText().toString());
                context.startActivity(intent);
            }
        });
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), holder.getAdapterPosition()+"번 "+
//                        holder.text_name.getText()+"\n"+holder.text_phonenumber.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), about.class);
                intent.putExtra("name", holder.text_name.getText().toString());
                intent.putExtra("phonenumber", holder.text_phonenumber.getText().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // 데이터를 입력
    public void setArrayData(JSONObject jsonData) {
        arrayList.add(jsonData);
    }
}