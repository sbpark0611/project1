package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        //json 읽어오기 시작
        AssetManager assetManager= getAssets();

        try {
            InputStream is= assetManager.open("jsons/phonebook.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader (isr);
            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }
            String jsonData= buffer.toString();

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray arr = jsonObject.getJSONArray("phonenumbers");

            recyclerView = (RecyclerView)findViewById(R.id.recyceler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

            adapter = new Adapter();
            for (int i = 0; i < arr.length(); i++) {
                adapter.setArrayData(arr.getJSONObject(i));
            }

            recyclerView.setAdapter(adapter);

        }catch (IOException e) {e.printStackTrace();}
        catch (JSONException e) {e.printStackTrace();}
        //json 읽어오기 끝

        //navigation 시작
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.phonebook:
                        return true;
                    case R.id.gallary:
                        startActivity(new Intent(getApplicationContext(), gallery.class));
                        overridePendingTransition(0, 0);
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

        //연락처 추가 버튼
        ImageButton addButton = (ImageButton)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(v.getContext(), "연락처 추가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AddPhonenumber.class);
                startActivity(intent);
            }
        });
    }
}
