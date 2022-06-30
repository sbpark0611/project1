package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class gallery extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageAdapter adapter;
    int imagenum = 20;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new ImageAdapter();
        for (int i = 1; i <= imagenum; i++) {
            Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_"+Integer.toString(i), "drawable"));
            adapter.setArrayData(drawable);
        }

        recyclerView.setAdapter(adapter);

        //navigation 시작
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.phonebook:
                        startActivity(new Intent(getApplicationContext(), phonebook.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.gallary:
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), about.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        //navigation 끝
    }

    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
}
