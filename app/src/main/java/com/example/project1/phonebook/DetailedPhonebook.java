package com.example.project1.phonebook;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.gallery.BigImage;
import com.example.project1.gallery.gallery;
import com.example.project1.namecard.Namecard;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailedPhonebook extends AppCompatActivity {

    ImageView detailed_profile_image;
    TextView detailed_text_name;
    TextView detailed_text_phonenumber;
    TextView detailed_text_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_phonebook);

        detailed_profile_image = findViewById(R.id.detailed_profile_image);
        detailed_text_name = findViewById(R.id.detailed_text_name);
        detailed_text_phonenumber = findViewById(R.id.detailed_text_phonenumber);
        detailed_text_detail = findViewById(R.id.detailed_text_detail);

        Intent getIntent = getIntent();
        String received_name = getIntent.getStringExtra("name");
        String received_phonenumber = getIntent.getStringExtra("phonenumber");
        String received_explanation = getIntent.getStringExtra("explanation");
        String received_drawable_number = getIntent.getStringExtra("drawable_number");
        String received_profilenumber = getIntent.getStringExtra("profilenumber");

        SharedPreferences sharedPreferences = getSharedPreferences("phonenumbers",MODE_PRIVATE);

        if(received_explanation != null) {
            detailed_text_detail.setText(received_explanation);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(received_profilenumber + "explanation", received_explanation);
            editor.commit();
        }
        else {
            detailed_text_detail.setText(sharedPreferences.getString(received_profilenumber + "explanation", null));
        }

        String savedProfileImage = sharedPreferences.getString(received_profilenumber,null);
        if(received_drawable_number != null){
            detailed_profile_image.setImageDrawable(getResources().getDrawable(findByString(getApplicationContext(), "pic_"+received_drawable_number, "drawable")));

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(received_profilenumber, received_drawable_number);
            editor.commit();

            String savedPhonenumberData = sharedPreferences.getString("phonenumbers",null);

            try {
                JSONObject jsonObject = new JSONObject(savedPhonenumberData);
                JSONArray jsonArray = jsonObject.getJSONArray("phonenumbers");

                JSONArray newJsonArray = new JSONArray();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonobj = jsonArray.getJSONObject(i);
                    if(Integer.parseInt(received_profilenumber) != i){
                        newJsonArray.put(jsonobj);
                    }
                    else{
                        jsonobj.put("name", received_name);
                        jsonobj.put("phonenumber", received_phonenumber);
                        newJsonArray.put(jsonobj);
                    }
                }
                JSONObject newJsonObject = new JSONObject();
                newJsonObject.put("phonenumbers", newJsonArray);

                SharedPreferences.Editor editor2 = sharedPreferences.edit();
                editor2.putString("phonenumbers", newJsonObject.toString());
                editor2.commit();

            } catch (JSONException e) {e.printStackTrace();}
        }
        else if(savedProfileImage != null){
            detailed_profile_image.setImageDrawable(getResources().getDrawable(findByString(getApplicationContext(), "pic_"+savedProfileImage, "drawable")));
        }
        else{
            detailed_profile_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_account_box_24));
        }

        detailed_text_name.setText(received_name);
        detailed_text_phonenumber.setText(received_phonenumber);

        detailed_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BigImage.class);
                if(received_drawable_number != null){
                    intent.putExtra("drawable_number", received_drawable_number);
                }
                else if(savedProfileImage != null){
                    intent.putExtra("drawable_number", savedProfileImage);
                }
                intent.putExtra("name", received_name);
                intent.putExtra("phonenumber", received_phonenumber);
                intent.putExtra("profilenumber", received_profilenumber);
                intent.putExtra("caller", "detailedphonebook");
                startActivity(intent);
            }
        });

        Button backbutton = (Button)findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), phonebook.class);
                startActivity(intent);
            }
        });

        Button deletebutton = (Button)findViewById(R.id.delete_button);
        deletebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String savedPhonenumberData = sharedPreferences.getString("phonenumbers",null);

                try {
                    JSONObject jsonObject = new JSONObject(savedPhonenumberData);
                    JSONArray jsonArray = jsonObject.getJSONArray("phonenumbers");

                    JSONArray newJsonArray = new JSONArray();
                    boolean deleted = false;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobj = jsonArray.getJSONObject(i);
                        if(Integer.parseInt(received_profilenumber) != i){
                            newJsonArray.put(jsonobj);
                            if(deleted){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(Integer.toString(i-1), sharedPreferences.getString(Integer.toString(i),null));
                                editor.commit();
                            }
                        }
                        else if(!deleted){
                            deleted = true;
                        }
                    }
                    JSONObject newJsonObject = new JSONObject();
                    newJsonObject.put("phonenumbers", newJsonArray);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("phonenumbers", newJsonObject.toString());
                    editor.commit();

                } catch (JSONException e) {e.printStackTrace();}

                Intent intent = new Intent(getApplicationContext(), phonebook.class);
                startActivity(intent);
            }
        });

        Button editbutton = (Button)findViewById(R.id.edit_button);
        editbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), EditDetail.class);
                intent.putExtra("drawable_number", savedProfileImage);
                intent.putExtra("name", received_name);
                intent.putExtra("phonenumber", received_phonenumber);
                intent.putExtra("explanation", sharedPreferences.getString(received_profilenumber + "explanation", null));
                intent.putExtra("profilenumber", received_profilenumber);
                startActivity(intent);
            }
        });

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
}