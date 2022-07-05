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
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.namecard.Namecard;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class PhoneGallery extends AppCompatActivity {

    RecyclerView recyclerView;
    UriImageAdapter adapter;
    ImageButton imageButton,backButton,cameraButton ;

    public static ArrayList<Uri> uriList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_gallery);

        Intent intent = getIntent();
        boolean delete = intent.getBooleanExtra("delete", false);

        if(delete){
            int idx = intent.getIntExtra("position",-1);
            if(idx != -1){
                uriList.remove(idx);
            }
        }

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

        //카메라 버튼
        cameraButton = (ImageButton) findViewById(R.id.camera_Button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 2);
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
        if(requestCode == 0 && resultCode == RESULT_OK) {
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            if(data != null){
                if (data.getClipData() == null) {
                    Log.e("single choice: ", String.valueOf(data.getData()));
                    Uri imageUri = data.getData();
                    uriList.add(imageUri);
                } else {
                    ClipData clipData = data.getClipData();
                    Log.e("clipData", String.valueOf(clipData.getItemCount()));

                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri imageUri = clipData.getItemAt(i).getUri();
                        try {
                            uriList.add(imageUri);
                        } catch (Exception e) {
                            Log.e("MultiImageActivity", "File select error", e);
                        }
                    }
                }
                adapter = new UriImageAdapter(uriList,getApplicationContext());
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                recyclerView.setAdapter(adapter);
            }
        }
        else if(requestCode == 2 && resultCode == RESULT_OK) {
            // Bundle로 데이터를 입력
            Bundle extras = data.getExtras();
                // Bitmap으로 컨버전
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Uri uri = getImageUri(getApplicationContext(), imageBitmap);
            // 이미지뷰에 Bitmap으로 이미지를 입력
            uriList.add(uri);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            adapter = new UriImageAdapter(uriList,getApplicationContext());
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setAdapter(adapter);

        }
    }

    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}