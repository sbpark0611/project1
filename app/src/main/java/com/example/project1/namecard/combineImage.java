package com.example.project1.namecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.project1.R;
import com.example.project1.namecard.about;

public class combineImage extends AppCompatActivity {

    Button confirmAddButton;
    Button cancelAddButton;
    ImageView combinedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_image);

        combinedImage = (ImageView) findViewById(R.id.combinedImage);

        confirmAddButton = (Button) findViewById(R.id.confirm_add_button);
        confirmAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), about.class);

                intent.putExtra("combined", combinedImage.getId());

                startActivity(intent);

            }
        });

        cancelAddButton = (Button) findViewById(R.id.cancel_add_button);
        cancelAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), about.class);
                startActivity(intent);
            }
        });

    }
}