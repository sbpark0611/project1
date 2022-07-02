package com.example.project1.phonebook;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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

import com.example.project1.R;
import com.example.project1.namecard.Namecard;
import com.example.project1.namecard.about;
import com.example.project1.gallery.gallery;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class phonebook extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    JSONObject jsonObject;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        //json 읽어오기 시작
        AssetManager assetManager= getAssets();

        SharedPreferences sharedPreferences = getSharedPreferences("phonenumbers",MODE_PRIVATE);

        Intent getIntent = getIntent();

        String received_drawable_number = getIntent.getStringExtra("drawable_number");
        String received_profile_number = getIntent.getStringExtra("profile_number");

        if(received_drawable_number != null && received_profile_number != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(received_profile_number, received_drawable_number);
            editor.commit();
        }

        try {
            String savedPhonenumberData = sharedPreferences.getString("phonenumbers",null);
            if(savedPhonenumberData != null){
                jsonObject = new JSONObject(savedPhonenumberData);
            }
            else {
                InputStream is = assetManager.open("jsons/phonebook.json");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
                StringBuffer buffer = new StringBuffer();
                String line = reader.readLine();
                while (line != null) {
                    buffer.append(line + "\n");
                    line = reader.readLine();
                }
                String jsonData = buffer.toString();

                jsonObject = new JSONObject(jsonData);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("phonenumbers", jsonObject.toString());
                editor.commit();
            }

            JSONArray arr = jsonObject.getJSONArray("phonenumbers");

            recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

            adapter = new Adapter(getApplicationContext());
            for (int i = 0; i < arr.length(); i++) {
                String savedProfileImage = sharedPreferences.getString(Integer.toString(i),null);
                if(savedProfileImage != null){
                    Drawable profileDrawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_"+savedProfileImage, "drawable"));
                    adapter.setArrayData(arr.getJSONObject(i), profileDrawable);
                }
                else{
                    adapter.setArrayData(arr.getJSONObject(i), getResources().getDrawable(R.drawable.ic_baseline_account_box_24));
                }
            }

            //새로 추가하는 거 받아오는 거
            JSONObject received_jsonObject = new JSONObject();
            try {
                String received_name = getIntent.getStringExtra("name");
                String received_phonenumber = getIntent.getStringExtra("phonenumber");

                received_jsonObject.put("name", received_name);
                received_jsonObject.put("phonenumber", received_phonenumber);

                if(received_name != null && received_phonenumber != null){
                    adapter.setArrayData(received_jsonObject, getResources().getDrawable(R.drawable.ic_baseline_account_box_24));

                    arr.put(received_jsonObject);

                    JSONObject newJsonObject = new JSONObject();
                    newJsonObject.put("phonenumbers", arr);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("phonenumbers", newJsonObject.toString());
                    editor.commit();
                }

            } catch (JSONException e) {e.printStackTrace();}

            recyclerView.setAdapter(adapter);

        }catch (IOException e) {e.printStackTrace();}
        catch (JSONException e) {e.printStackTrace();}
        //json 읽어오기 끝

        //navigation 시작
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.phonebook:
                        return true;
                    case R.id.gallary:
                        startActivity(new Intent(getApplicationContext(), gallery.class));
                        overridePendingTransition(R.anim.slide_in_right,android.R.anim.fade_out);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), Namecard.class));
                        overridePendingTransition(R.anim.slide_in_right,android.R.anim.fade_out);
                        return true;
                }
                return false;
            }
        });
        //navigation 끝

        //연락처 추가 버튼
        ImageButton addButton = (ImageButton)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), AddPhonenumber.class);
                startActivity(intent);
            }
        });

        //drawable 초기화
        for(int i = 0; i < 10; i++){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Integer.toString(adapter.getItemCount()+i), null);
            editor.commit();
        }

    }

    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
}
