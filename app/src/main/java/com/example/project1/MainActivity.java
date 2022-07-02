package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project1.gallery.gallery;
import com.example.project1.namecard.Namecard;
import com.example.project1.namecard.about;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        //껐다키면 데이터 초기화
        SharedPreferences textSharedPreferences = getSharedPreferences("about_phonenumber",MODE_PRIVATE);
        SharedPreferences.Editor textEditor = textSharedPreferences.edit();
        textEditor.putString("name", null);
        textEditor.putString("phonenumber", null);
        textEditor.commit();

        SharedPreferences drawableSharedPreferences = getSharedPreferences("drawable_number",MODE_PRIVATE);
        SharedPreferences.Editor drawableEditor = drawableSharedPreferences.edit();
        drawableEditor.putString("drawable_number", null);
        drawableEditor.commit();

//
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
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.gallary:
                        startActivity(new Intent(getApplicationContext(), gallery.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.namecard:
                        startActivity(new Intent(getApplicationContext(), Namecard.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
