package com.example.project1.namecard;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.namecard.about;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class combineImage extends AppCompatActivity {

    Button confirmAddButton;
    Button cancelAddButton;
    ImageView combinedImage;
    TextView combinedText;
    boolean setAllCaps = false;
    int fontSize = 10;
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
        Drawable asd = combinedImage.getDrawable();

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


        //상하좌우 버튼을 구현 텍스트 위치 조정
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

        //폰트 사이즈 조정
        ImageButton fontUp = (ImageButton) findViewById(R.id.fontUp);
        fontUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fontSize += 10;
                combinedText.setTextSize(Dimension.SP,fontSize);
            }
        });

        ImageButton fontDown = (ImageButton) findViewById(R.id.fontDown);
        fontDown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fontSize-=10;
                if(fontSize<=0){
                    fontSize=5;
                    Toast.makeText(v.getContext(), "텍스트 사이즈를 더 줄일 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
                combinedText.setTextSize(Dimension.SP,fontSize);
            }
        });

        //폰트 대소문자 조정
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

        //합쳐진 사진을 보내는 버튼.
        confirmAddButton = (Button) findViewById(R.id.confirm_add_button);
        confirmAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Namecard.class);
                Namecard.numofarray+=1;
                intent.putExtra("ImageNum", savedDrawableNumber);
                intent.putExtra("text",savedNameTextData +"\n" +savedPhonenumberTextData );
                startActivity(intent);

            }
        });

        cancelAddButton = (Button) findViewById(R.id.cancel_add_button);
        cancelAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Namecard.class);
                startActivity(intent);
            }
        });
    }
    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
    private void screenshot(Bitmap bm, int num) {
        try {
            File path = new File("/drawable");
            if(! path.isDirectory()) {
                path.mkdirs();
            }
            String temp = "/drawable/";
            temp = temp + "namecard" + Integer.toString(num);
            temp = temp + ".png";
            FileOutputStream out = new FileOutputStream(temp);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        } catch (FileNotFoundException e) {
            Log.d("FileNotFoundException:", e.getMessage());
        }

    }


}