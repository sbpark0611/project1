package com.example.project1.namecard;

import static java.lang.System.out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.R;
import com.example.project1.gallery.gallery;
import com.example.project1.phonebook.DetailedPhonebook;

import java.io.File;
import java.io.FileOutputStream;

public class SharedBigImage extends AppCompatActivity {
    ImageView bigImage;
    TextView sharedText;
    Button saveButton;
    Button shareButton;
    ConstraintLayout imageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_big_image);

        bigImage = (ImageView) findViewById(R.id.shared_big_image_view);
        sharedText = (TextView) findViewById(R.id.shared_text);

        Intent getIntent = getIntent();

        String string = getIntent.getStringExtra("string");
        String drawablenumber = getIntent.getStringExtra("drawablenumber");
        String size = getIntent.getStringExtra("size");
        String x = getIntent.getStringExtra("x");
        String y = getIntent.getStringExtra("y");

        bigImage.setImageDrawable(getResources().getDrawable(findByString(getApplicationContext(),"pic_" + drawablenumber,"drawable")));
        sharedText.setText(string);
        sharedText.setTextSize(Integer.parseInt(size));
        sharedText.setTranslationX(Float.parseFloat(x));
        sharedText.setTranslationY(Float.parseFloat(y));

        bigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Namecard.class);
                startActivity(intent);
            }
        });

        imageLayout = (ConstraintLayout) findViewById(R.id.image_layout);
        saveButton = (Button) findViewById(R.id.savebutton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                imageLayout.setDrawingCacheEnabled(true);
//
//                Bitmap bm = imageLayout.getDrawingCache();
//
//                try {
//
//                    onCap(bm);
//
//                } catch (Exception e) {
//
//                } finally {
//
//                    bm.recycle();
//
//                }



                Intent intent = new Intent(getApplicationContext(), Namecard.class);
                startActivity(intent);
            }
        });

        shareButton = (Button) findViewById(R.id.sharebutton);
        shareButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Namecard.class);
                startActivity(intent);
            }
        });

    }

    public static int findByString(Context context, String resourceName, String type) {
        out.println(resourceName);
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }

//    private void onCap(Bitmap bm) throws Exception {
//        File saveFile;
//
//        try {
//            String imgFile = "save.jpg"; // 저장파일명
//
//            StringBuffer imgPath = new StringBuffer("sdcard/Download/"); // 저장경로
//            saveFile = new File(imgPath.toString());
//
//            if(!saveFile.isDirectory()) {
//                saveFile.mkdirs();
//            }
//            imgPath.append(imgFile);
//            out = new FileOutputStream(imgPath.toString()); // 저장경로 + 파일명
//            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
//        } catch (Exception e) {
//
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//            saveFile = null;
//        }
//    }
}