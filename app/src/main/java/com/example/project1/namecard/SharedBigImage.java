package com.example.project1.namecard;

import static java.lang.System.out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.gallery.gallery;
import com.example.project1.phonebook.DetailedPhonebook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

        bigImage.setImageDrawable(getResources().getDrawable(findByString(getApplicationContext(), "pic_" + drawablenumber, "drawable")));
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
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageLayout.setDrawingCacheEnabled(true);
                Bitmap bm = imageLayout.getDrawingCache();

                try {
                    onCap(bm);
                } catch (Exception e) {
                } finally {
                    bm.recycle();
                }

                Intent intent = new Intent(getApplicationContext(), Namecard.class);
                startActivity(intent);
            }
        });

        shareButton = (Button) findViewById(R.id.sharebutton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLayout.setDrawingCacheEnabled(true);
                Bitmap bm = imageLayout.getDrawingCache();

                // Define image asset URI
                Uri backgroundAssetUri = getImageUri(getApplicationContext(), bm);
                String sourceApplication = "com.my.app";

                // Instantiate implicit intent with ADD_TO_STORY action and background asset
                Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");

                intent.setDataAndType(backgroundAssetUri, "JPEG");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Instantiate activity and verify it will resolve implicit intent
                Activity activity = SharedBigImage.this;
                if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
                    activity.startActivityForResult(intent, 0);
                }
                Intent intent2 = new Intent(getApplicationContext(), Namecard.class);
                startActivity(intent2);
            }
        });
    }

    public static int findByString(Context context, String resourceName, String type) {
        out.println(resourceName);
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }

    private void onCap(Bitmap bm) throws Exception {
        File saveFile;
        FileOutputStream out = null;

        try {
            String imgFile = "save.jpg"; // 저장파일명

            String root = Environment.getExternalStorageDirectory().toString();
            StringBuffer imgPath = new StringBuffer(root + "/Pictures"); // 저장경로
            saveFile = new File(imgPath.toString());

            if (!saveFile.isDirectory()) {
                saveFile.mkdirs();
            }
            imgPath.append(imgFile);
            out = new FileOutputStream(imgPath.toString()); // 저장경로 + 파일명
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        } catch (Exception e) {

        } finally {
            if (out != null) {
                out.close();
            }
            saveFile = null;
        }
    }

    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}