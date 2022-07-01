package com.example.project1.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.gallery.ImageAdapter;
import com.example.project1.gallery.gallery;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class about extends AppCompatActivity {
    NameSelectAdapter nameSelectAdapter;
    ImageSelectAdapter imageSelectAdapter;

    String currentNameText, currentPhonenumberText, currentDrawableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //텍스트 버튼 클릭 시
        TextView selectText = (TextView) findViewById(R.id.selectText);
        selectText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), NameSelection.class);
                startActivity(intent);

            }

        });
        //텍스트 가져오기
        SharedPreferences textSharedPreferences = getSharedPreferences("about_phonenumber",MODE_PRIVATE);
        String savedNameTextData = textSharedPreferences.getString("name",null);
        String savedPhonenumberTextData = textSharedPreferences.getString("phonenumber",null);

        Intent getContactIntent = getIntent();

        String received_name = getContactIntent.getStringExtra("name");
        String received_phonenumber = getContactIntent.getStringExtra("phonenumber");
        //이름을 선택 했을 때
        if(received_name!=null && received_phonenumber !=null){
            selectText.setText(received_name+ " "+ received_phonenumber);

            SharedPreferences.Editor textEditor = textSharedPreferences.edit();
            textEditor.putString("name", received_name);
            textEditor.putString("phonenumber", received_phonenumber);
            textEditor.commit();

            currentNameText = received_name;
            currentPhonenumberText = received_phonenumber;
        }
        //이름이 선택은 안 되었는 데, 이미 기존에 선택 된 것이 있을 때
        else if(savedNameTextData != null && savedPhonenumberTextData != null){
            selectText.setText(savedNameTextData+ " "+ savedPhonenumberTextData);

            currentNameText = savedNameTextData;
            currentPhonenumberText = savedPhonenumberTextData;
        }
        else{
            currentNameText = null;
            currentPhonenumberText = null;
        }


        //이미지 버튼 클릭 시
        ImageView selectImage = (ImageView)findViewById(R.id.selectImage);
        selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ImageSelection.class);
                startActivity(intent);
            }
        });

        //이미지 가져오기
        SharedPreferences drawableSharedPreferences = getSharedPreferences("drawable_number",MODE_PRIVATE);
        String savedDrawableNumber = drawableSharedPreferences.getString("drawable_number",null);

        Intent getImageIntent = getIntent();
        String getDrawableNumber = getImageIntent.getStringExtra("drawable_number");

        if(getDrawableNumber!=null) {
            Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_" + getDrawableNumber, "drawable"));
            selectImage.setImageDrawable(drawable);

            SharedPreferences.Editor drawableEditor = drawableSharedPreferences.edit();
            drawableEditor.putString("drawable_number", getDrawableNumber);
            drawableEditor.commit();

            currentDrawableNumber = getDrawableNumber;
        }
        else if(savedDrawableNumber != null){
            Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_" + savedDrawableNumber, "drawable"));
            selectImage.setImageDrawable(drawable);

            currentDrawableNumber = savedDrawableNumber;
        }
        else{
            currentDrawableNumber = null;
        }


        ImageButton combineButton = (ImageButton)findViewById(R.id.combine_button);
        combineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(currentNameText != null && currentPhonenumberText != null && currentDrawableNumber != null) {
                    Intent intent = new Intent(getApplicationContext(), combineImage.class);
                    intent.putExtra("name", currentNameText);
                    intent.putExtra("phonenumber", currentPhonenumberText);
                    intent.putExtra("drawable_number", currentDrawableNumber);
                    startActivity(intent);
                }
                else if(currentNameText == null || currentPhonenumberText == null){
                    Toast.makeText(v.getContext(), "연락처를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(v.getContext(), "사진을 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
//                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_out);
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

    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }

}