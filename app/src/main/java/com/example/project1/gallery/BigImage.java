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

public class BigImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        ImageView bigImage = (ImageView)findViewById(R.id.big_image_view);

        Intent getIntent = getIntent();

        System.out.println("pic_"+getIntent.getStringExtra("drawable_number"));

        Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_"+getIntent.getStringExtra("drawable_number"), "drawable"));
        bigImage.setImageDrawable(drawable);

        bigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), gallery.class);
                startActivity(intent);
            }
        });
    }

    public static int findByString(Context context, String resourceName, String type) {
        System.out.println(resourceName);
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
}