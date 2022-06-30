package com.example.project1.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project1.R;
import com.example.project1.gallery.gallery;
import com.example.project1.phonebook.Adapter;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NameSelection extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_selection);
        //json 읽어오기 시작
        AssetManager assetManager= getAssets();

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("phonenumbers",MODE_PRIVATE);
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

            adapter = new Adapter();
            for (int i = 0; i < arr.length(); i++) {
                adapter.setArrayData(arr.getJSONObject(i));
            }

            recyclerView.setAdapter(adapter);

        }catch (IOException e) {e.printStackTrace();}
        catch (JSONException e) {e.printStackTrace();}
        //json 읽어오기 끝

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

}