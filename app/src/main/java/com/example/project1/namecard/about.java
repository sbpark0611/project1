package com.example.project1.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.gallery.ImageAdapter;
import com.example.project1.gallery.gallery;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class about extends AppCompatActivity {
    NameSelectAdapter nameSelectAdapter;
    ImageSelectAdapter imageSelectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView selectText = (TextView) findViewById(R.id.selectText);
        selectText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), NameSelection.class);
                startActivity(intent);

            }

        });
        //텍스트 가져오기
        Intent getContactIntent = getIntent();

        String received_name = getContactIntent.getStringExtra("name");
        String received_phonenumber = getContactIntent.getStringExtra("phonenumber");
        if(received_name!=null && received_phonenumber !=null){
            selectText.setText(received_name+ " "+ received_phonenumber);
        }


        ImageView selectImage = (ImageView)findViewById(R.id.selectImage);
        selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ImageSelection.class);
                startActivity(intent);
            }
        });

        //이미지 가져오기
        Intent getImageIntent = getIntent();
        System.out.println("pic_"+getImageIntent.getStringExtra("drawable_number"));
        if(getImageIntent.getStringExtra("drawable_number")!=null) {
            Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_" + getImageIntent.getStringExtra("drawable_number"), "drawable"));
            selectImage.setImageDrawable(drawable);
        }


        ImageButton combineButton = (ImageButton)findViewById(R.id.combine_button);
        combineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), combineImage.class);
                startActivity(intent);
            }
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
//        // Set Home selected
//        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.phonebook:
                        startActivity(new Intent(getApplicationContext(), phonebook.class));
//                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_out);
                        overridePendingTransition(R.anim.slide_in_left,android.R.anim.fade_out);
                        return true;
                    case R.id.gallary:
                        startActivity(new Intent(getApplicationContext(), gallery.class));
                        overridePendingTransition(R.anim.slide_in_left,android.R.anim.fade_out);
                        return true;
                    case R.id.about:
                        return true;
                }
                return false;
            }
        });

    }

    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }

}