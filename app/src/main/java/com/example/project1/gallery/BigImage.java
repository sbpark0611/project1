package com.example.project1.gallery;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.phonebook.AddPhonenumber;
import com.example.project1.phonebook.DetailedPhonebook;

public class BigImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        ImageView bigImage = (ImageView)findViewById(R.id.big_image_view);

        Intent getIntent = getIntent();

        if(getIntent.getStringExtra("drawable_number") != null) {
            Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_" + getIntent.getStringExtra("drawable_number"), "drawable"));
            bigImage.setImageDrawable(drawable);
        }
        else {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_account_box_24);
            bigImage.setImageDrawable(drawable);
        }

        bigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent.getStringExtra("caller") != null && getIntent.getStringExtra("caller").equals("detailedphonebook")) {
                    Intent intent = new Intent(getApplicationContext(), DetailedPhonebook.class);
                    intent.putExtra("name", getIntent.getStringExtra("name"));
                    intent.putExtra("phonenumber", getIntent.getStringExtra("phonenumber"));
                    intent.putExtra("profilenumber", getIntent.getStringExtra("profilenumber"));
                    intent.putExtra("explanation", getIntent.getStringExtra("explanation"));
                    intent.putExtra("drawable_number", getIntent.getStringExtra("drawable_number"));
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), gallery.class);
                    startActivity(intent);
                }
            }
        });
    }

    public static int findByString(Context context, String resourceName, String type) {
        System.out.println(resourceName);
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
}