package com.example.project1.phonebook;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.example.project1.gallery.gallery;
import com.example.project1.namecard.ImageSelectAdapter;
import com.example.project1.namecard.about;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileSelection extends AppCompatActivity {
    RecyclerView recyclerView;
    ProfileSelectAdapter adapter;
    int imagenum = 30;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);

        Intent getIntent = getIntent();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        adapter = new ProfileSelectAdapter(getApplicationContext(), getIntent.getStringExtra("profilenumber"), getIntent.getStringExtra("name"),
                getIntent.getStringExtra("phonenumber"), getIntent.getStringExtra("explanation"), getIntent.getStringExtra("caller"));

        for (int i = 1; i <= imagenum; i++) {
            Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_"+Integer.toString(i), "drawable"));
            adapter.setArrayData(drawable);
        }

        recyclerView.setAdapter(adapter);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
//        // Set Home selected
//        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.phonebook:
                        return true;
                    case R.id.gallary:
                        startActivity(new Intent(getApplicationContext(), gallery.class));
                        overridePendingTransition(R.anim.slide_in_right,android.R.anim.fade_out);
                        return true;
                    case R.id.namecard:
                        startActivity(new Intent(getApplicationContext(), about.class));
                        overridePendingTransition(R.anim.slide_in_right,android.R.anim.fade_out);
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