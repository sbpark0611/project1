package com.example.project1.gallery;

import static com.example.project1.gallery.PhoneGallery.uriList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project1.R;
import com.example.project1.phonebook.DetailedPhonebook;

public class UriBigImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uri_big_image);

        ImageView bigImage = (ImageView)findViewById(R.id.big_image_view);

        Intent getIntent = getIntent();
        int position =getIntent.getIntExtra("position",-1);
        if(0<= position && position<= uriList.size()) {
            Uri uri = uriList.get(position);
            Glide.with(this).load(uri).into(bigImage);
        }
        else {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_account_box_24);
            bigImage.setImageDrawable(drawable);
        }

        bigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PhoneGallery.class);
                startActivity(intent);

            }
        });
    }

    public static int findByString(Context context, String resourceName, String type) {
        System.out.println(resourceName);
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
}
