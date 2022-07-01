package com.example.project1.namecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.namecard.about;

public class combineImage extends AppCompatActivity {

    Button confirmAddButton;
    Button cancelAddButton;
    ImageView combinedImage;
    TextView combinedText;
    boolean setAllCaps = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_image);

        Intent getAboutIntent = getIntent();
        //받아온 세개의 데이터
        String received_name = getAboutIntent.getStringExtra("name");
        String received_phonenumber = getAboutIntent.getStringExtra("phonenumber");
        String received_drawable_number = getAboutIntent.getStringExtra("drawable_number");

        combinedImage = (ImageView) findViewById(R.id.combinedImage);
        combinedText = (TextView) findViewById(R.id.combinedText);
        //Image 저장하는 부분
        Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(), "pic_" + received_drawable_number, "drawable"));
        combinedImage.setImageDrawable(drawable);

        //sharedPreference로 사진 저장하는 부분
        SharedPreferences drawableSharedPreferences = getSharedPreferences("drawable_number",MODE_PRIVATE);
        String savedDrawableNumber = drawableSharedPreferences.getString("drawable_number",null);

        SharedPreferences.Editor drawableEditor = drawableSharedPreferences.edit();
        drawableEditor.putString("drawable_number", savedDrawableNumber);
        drawableEditor.commit();

        //text 저장 하는 부분
        combinedText.setText(received_name+ "\n"+ received_phonenumber);

        //sharedPreference로 텍스트 저장하는 부분
        SharedPreferences textSharedPreferences = getSharedPreferences("about_phonenumber",MODE_PRIVATE);
        String savedNameTextData = textSharedPreferences.getString("name",null);
        String savedPhonenumberTextData = textSharedPreferences.getString("phonenumber",null);

        SharedPreferences.Editor textEditor = textSharedPreferences.edit();
        textEditor.putString("name", savedNameTextData);
        textEditor.putString("phonenumber", savedPhonenumberTextData);
        textEditor.commit();

        ImageButton upButton = (ImageButton) findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinedText.setTranslationY(combinedText.getTranslationY()-10);
            }
        });
        ImageButton downButton = (ImageButton) findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinedText.setTranslationY(combinedText.getTranslationY()+10);

            }
        });
        ImageButton rightButton = (ImageButton) findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinedText.setTranslationX(combinedText.getTranslationX()+10);

            }
        });
        ImageButton leftButton = (ImageButton) findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinedText.setTranslationX(combinedText.getTranslationX()-10);

            }
        });

        ImageButton fontUp = (ImageButton) findViewById(R.id.fontUp);
        fontUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                combinedText.setTextSize(combinedText.getTextSize()+10);
            }
        });

        ImageButton fontDown = (ImageButton) findViewById(R.id.fontDown);
        fontDown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                combinedText.setTextSize(combinedText.getTextSize()-10);
            }
        });
        ImageButton fontIcon = (ImageButton) findViewById(R.id.fontIcon);
        fontIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!setAllCaps) {
                    combinedText.setAllCaps(true);
                    setAllCaps = true;
                }
                else{
                    combinedText.setAllCaps(false);
                    setAllCaps = false;
                }
            }
        });

        confirmAddButton = (Button) findViewById(R.id.confirm_add_button);
        confirmAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Namecard.class);
                //현재 combinedImage상에 띄워진 애를 Namecard로 전송
                intent.putExtra("combined", combinedImage.getId());
                startActivity(intent);
            }
        });

        cancelAddButton = (Button) findViewById(R.id.cancel_add_button);
        cancelAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), about.class);
                startActivity(intent);
            }
        });
    }
    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
}