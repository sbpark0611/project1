package com.example.project1.namecard;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.example.project1.gallery.BigImage;

import java.util.ArrayList;

public class NamecardAdapter extends RecyclerView.Adapter<NamecardViewHolder> {
    private ArrayList<Drawable> imageArrayList;
    private ArrayList<String> imageNumberArrayList;
    private ArrayList<String> textArrayList;
    private ArrayList<Integer> fontSizeArrayList;
    private ArrayList<Float> transXArrayList;
    private ArrayList<Float> transYArrayList;
    private ArrayList<Boolean> capsArrayList;
    private ArrayList<Integer> textColorArrayList;
    private ArrayList<Integer> bgColorArrayList;


    Context context;
    public NamecardAdapter(Context context) {
        this.context = context;
        imageArrayList = new ArrayList<Drawable>();
        imageNumberArrayList = new ArrayList<String>();
        textArrayList = new ArrayList<String>();
        fontSizeArrayList = new ArrayList<>();
        transXArrayList = new ArrayList<>();
        transYArrayList = new ArrayList<>();
        capsArrayList = new ArrayList<>();
        textColorArrayList = new ArrayList<>();
        bgColorArrayList = new ArrayList<>();
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull NamecardViewHolder holder, int position) {
        Drawable drawable = imageArrayList.get(position);
        holder.imageview.setImageDrawable(drawable);

        String drawablenumber = imageNumberArrayList.get(position);

        if(textArrayList.size()==0){

        }
        else{

        }
        String string = textArrayList.get(position);
        holder.textView.setText(string);

        int size = fontSizeArrayList.get(position);
        holder.textView.setTextSize(size);

        float X = transXArrayList.get(position);
        holder.textView.setTranslationX(X);

        float Y = transYArrayList.get(position);
        holder.textView.setTranslationY(Y);

        boolean caps = capsArrayList.get(position);
        holder.textView.setAllCaps(caps);

        int textColor = textColorArrayList.get(position);
        if(textColor == 1){
            holder.textView.setTextColor(context.getResources().getColor(R.color.light));
        }
        else if(textColor == 2){
            holder.textView.setTextColor(context.getResources().getColor(R.color.gray));
        }
        else{
            holder.textView.setTextColor(context.getResources().getColor(R.color.dark));
        }

        int bgColor = bgColorArrayList.get(position);
        if(bgColor == 1){
            holder.imageview.setBackgroundColor(context.getResources().getColor(R.color.light));
        }
        else if(bgColor == 2){
            holder.imageview.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        else{
            holder.imageview.setBackgroundColor(context.getResources().getColor(R.color.dark));
        }

        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SharedBigImage.class);
                intent.putExtra("drawablenumber", drawablenumber);
                intent.putExtra("string", string);
                intent.putExtra("size", Integer.toString(size));
                intent.putExtra("x", Float.toString(X));
                intent.putExtra("y", Float.toString(Y));
                intent.putExtra("caps", Boolean.toString(caps));
                intent.putExtra("textColor", Integer.toString(textColor));
                intent.putExtra("bgColor", Integer.toString(bgColor));
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
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

    // ???????????? ??????
    public void setImageArrayData(Drawable drawable) {
        imageArrayList.add(drawable);
    }
    public void setImageNumberArrayData(String drawablenumber) {
        imageNumberArrayList.add(drawablenumber);
    }
    public void setTextArrayData(String string) {
        textArrayList.add(string);
    }
    public void setFontSizeArrayList(int size) {fontSizeArrayList.add(size);}
    public void setTransXArrayList(float X) {transXArrayList.add(X);}
    public void setTransYArrayList(float Y) {transYArrayList.add(Y);}
    public void setCapsArrayList(boolean caps){capsArrayList.add(caps);}
    public void setIsWhiteArrayList(int is){textColorArrayList.add(is);}
    public void setIsBGWhiteArrayList(int is){bgColorArrayList.add(is);}
}