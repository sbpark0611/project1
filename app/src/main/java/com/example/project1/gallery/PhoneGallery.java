package com.example.project1.gallery;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.namecard.Namecard;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class PhoneGallery extends AppCompatActivity {

    RecyclerView recyclerView;
    UriImageAdapter adapter;
    ImageButton imageButton,backButton ;

    public static ArrayList<Uri> uriList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_gallery);

        if(uriList.size()>0){
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            adapter = new UriImageAdapter(uriList,getApplicationContext());
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setAdapter(adapter);
        }

        //이미지 추가 버튼 클릭 시 갤러리로 이동
        imageButton = (ImageButton) findViewById(R.id.add_Image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,0);
            }
        });

        //뒤로가기 버튼: 원래 갤러리로 복귀
        backButton = (ImageButton) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),gallery.class));
            }
        });

        //navigation 시작
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.phonebook:
                        startActivity(new Intent(getApplicationContext(), phonebook.class));
                        overridePendingTransition(R.anim.slide_in_left,android.R.anim.fade_out);
                        return true;
                    case R.id.gallary:
                        return true;
                    case R.id.namecard:
                        startActivity(new Intent(getApplicationContext(), Namecard.class));
                        overridePendingTransition(R.anim.slide_in_right,android.R.anim.fade_out);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                if(data == null){   // 어떤 이미지도 선택하지 않은 경우
                    Toast.makeText(getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                else {   // 이미지를 하나라도 선택한 경우
                    if (data.getClipData() == null) {     // 이미지를 하나만 선택한 경우
                        Log.e("single choice: ", String.valueOf(data.getData()));
                        Uri imageUri = data.getData();
                        uriList.add(imageUri);

                        SharedPreferences uriSharedPreferences = getSharedPreferences("uri",MODE_PRIVATE);
                        SharedPreferences.Editor editor = uriSharedPreferences.edit();
                        editor.putString("uri", imageUri.toString());
                        editor.commit();

//                        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
                    } else {      // 이미지를 여러장 선택한 경우
                        ClipData clipData = data.getClipData();
                        Log.e("clipData", String.valueOf(clipData.getItemCount()));

                        if (clipData.getItemCount() > 10) {   // 선택한 이미지가 11장 이상인 경우
                            Toast.makeText(getApplicationContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                        } else {   // 선택한 이미지가 1장 이상 10장 이하인 경우
                            Log.e("MultiImageActivity", "multiple choice");

                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                Uri imageUri = clipData.getItemAt(i).getUri();  // 선택한 이미지들의 uri를 가져온다.
                                try {
                                    uriList.add(imageUri);  //uri를 list에 담는다.
                                } catch (Exception e) {
                                    Log.e("MultiImageActivity", "File select error", e);
                                }
                            }
                            adapter = new UriImageAdapter(uriList,getApplicationContext());

                            recyclerView.setAdapter(adapter);   // 리사이클러뷰에 어댑터 세팅
                            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));     // 리사이클러뷰 수평 스크롤 적용
                        }
                    }

                    adapter = new UriImageAdapter(uriList,getApplicationContext());
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                    recyclerView.setAdapter(adapter);
                }
            }
        }
    }
}