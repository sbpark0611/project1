package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);

        //처음화면

        //바텀 네비게이션뷰 안의 아이템 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //item을 클릭시 id값을 가져와 FrameLayout에 fragment.xml띄우기
                    case R.id.item_fragment1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new phonebook()).commit();
                        break;
                    case R.id.item_fragment2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Gallary()).commit();
                        break;
                    case R.id.item_fragment3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new three()).commit();
                        break;

                }
                return true;
            }
        });
    }
}