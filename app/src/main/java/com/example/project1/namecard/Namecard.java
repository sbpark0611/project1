package com.example.project1.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.project1.R;
import com.example.project1.gallery.gallery;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Namecard extends AppCompatActivity {

    RecyclerView recyclerView;
    NamecardAdapter adapter;
    ArrayList<String> received_image;
    ArrayList<String> received_text;
    public static int numofarray = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namecard);

        received_image = new ArrayList<String>();
        received_text = new ArrayList<String>();

        ImageView selectImage = (ImageView)findViewById(R.id.add_button);
        selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), about.class);
                startActivity(intent);
            }
        });
        //about -> combineImage -> Namecard 통해서 보내온 file 저장.
        Intent getNameCardIntent = getIntent();
        if(getNameCardIntent.getStringExtra("ImageNum")!=null){
            received_image.add(getNameCardIntent.getStringExtra("ImageNum"));
            received_text.add(getNameCardIntent.getStringExtra("text"));
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

            adapter = new NamecardAdapter(getApplicationContext());
            for (int i = 1; i < numofarray + 1; i++) {
                Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(),"pic_" + received_image.get(i-1),"drawable"));
                adapter.setImageArrayData(drawable);
                adapter.setTextArrayData(received_text.get(i-1));
            }

            recyclerView.setAdapter(adapter);

        }

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