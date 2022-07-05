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
    private ArrayList<Boolean> isWhite;
    private ArrayList<Boolean> isBGWhite;


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
        isWhite = new ArrayList<>();
        isBGWhite = new ArrayList<>();
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

        boolean isWhite2 = isWhite.get(position);
        if(isWhite2){
            holder.textView.setTextColor(R.color.light);
        }
        else{
            holder.textView.setTextColor(R.color.dark);
        }

        boolean isBGWhite2 = isBGWhite.get(position);
        if(isWhite2){
            holder.imageview.setBackgroundColor(R.color.light);
        }
        else{
            holder.imageview.setBackgroundColor(R.color.dark);
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
                intent.putExtra("isWhite", Boolean.toString(isWhite2));
                intent.putExtra("isBGWhite", Boolean.toString(isBGWhite2));
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

    // 데이터를 입력
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
    public void setIsWhiteArrayList(boolean is){isWhite.add(is);}
    public void setIsBGWhiteArrayList(boolean is){isBGWhite.add(is);}
}