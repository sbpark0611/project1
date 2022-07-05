package com.example.project1.namecard;

import static com.example.project1.MainActivity.namecardnumber;
import static java.lang.System.out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    @SuppressLint("ResourceAsColor")
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
        String caps = getIntent.getStringExtra("caps");
        String textColor = getIntent.getStringExtra("textColor");
        String bgColor = getIntent.getStringExtra("bgColor");

        bigImage.setImageDrawable(getResources().getDrawable(findByString(getApplicationContext(), "pic_" + drawablenumber, "drawable")));
        sharedText.setText(string);
        sharedText.setTextSize(Integer.parseInt(size));
        sharedText.setTranslationX(Float.parseFloat(x));
        sharedText.setTranslationY(Float.parseFloat(y));
        sharedText.setAllCaps(Boolean.parseBoolean(caps));

        if(Integer.parseInt(textColor) == 1) {
            sharedText.setTextColor(getResources().getColor(R.color.light));
        }
        else if(Integer.parseInt(textColor) == 2) {
            sharedText.setTextColor(getResources().getColor(R.color.gray));
        }
        else{
            sharedText.setTextColor(getResources().getColor(R.color.dark));
        }

        if(Integer.parseInt(bgColor) == 1) {
            bigImage.setBackgroundColor(getResources().getColor(R.color.light));
        }
        else if(Integer.parseInt(bgColor) == 2) {
            bigImage.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        else{
            bigImage.setBackgroundColor(getResources().getColor(R.color.dark));
        }

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
                Uri bgUri = getImageUri(getApplicationContext(), bm);
                String sourceApplication = "com.khs.instagramshareexampleproject";

                // Instantiate implicit intent with ADD_TO_STORY action,
                // sticker asset, and background colors
                Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                intent.putExtra("source_application", sourceApplication);
                intent.setType("image/png");
                intent.setDataAndType(bgUri, "image/png");

                // Instantiate activity and verify it will resolve implicit intent
                grantUriPermission(
                        "com.instagram.android", null, Intent.FLAG_GRANT_READ_URI_PERMISSION
                );
                grantUriPermission(
                        "com.instagram.android", bgUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                );
                Intent moveIntent = new Intent(getApplicationContext(),Namecard.class);
                startActivity(moveIntent);
            }
        });

        shareButton = (Button) findViewById(R.id.sharebutton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLayout.setDrawingCacheEnabled(true);
                Bitmap bm = imageLayout.getDrawingCache();
                Uri bgUri = getImageUri(getApplicationContext(), bm);
                String sourceApplication = "com.khs.instagramshareexampleproject";

                // Instantiate implicit intent with ADD_TO_STORY action,
                // sticker asset, and background colors
                Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                intent.putExtra("source_application", sourceApplication);
                intent.setType("image/png");
                intent.setDataAndType(bgUri, "image/png");

                // Instantiate activity and verify it will resolve implicit intent
                grantUriPermission(
                        "com.instagram.android", null, Intent.FLAG_GRANT_READ_URI_PERMISSION
                );
                grantUriPermission(
                        "com.instagram.android", bgUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                );
                startActivity(intent);

            }
        });
    }

    public static int findByString(Context context, String resourceName, String type) {
        out.println(resourceName);
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }

    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}