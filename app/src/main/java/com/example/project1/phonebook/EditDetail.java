package com.example.project1.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.project1.R;
import com.example.project1.gallery.BigImage;

public class EditDetail extends AppCompatActivity {

    ImageView edit_detailed_image;
    EditText edit_detailed_text_name;
    EditText edit_detailed_text_phonenumber;
    EditText edit_detailed_text_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        edit_detailed_image = findViewById(R.id.edit_detailed_image);
        edit_detailed_text_name = findViewById(R.id.edit_detailed_text_name);
        edit_detailed_text_phonenumber = findViewById(R.id.edit_detailed_text_phonenumber);
        edit_detailed_text_detail = findViewById(R.id.edit_detailed_text_detail);

        Intent getIntent = getIntent();
        String received_drawable_number = getIntent.getStringExtra("drawable_number");
        String received_name = getIntent.getStringExtra("name");
        String received_phonenumber = getIntent.getStringExtra("phonenumber");
        String received_explanation = getIntent.getStringExtra("explanation");
        String received_profilenumber = getIntent.getStringExtra("profilenumber");

        if(received_drawable_number!= null){
            edit_detailed_image.setImageDrawable(getResources().getDrawable(findByString(getApplicationContext(), "pic_"+received_drawable_number, "drawable")));
        }

        if(received_name!= null){
            edit_detailed_text_name.setText(received_name);
        }
        if(received_phonenumber!= null){
            edit_detailed_text_phonenumber.setText(received_phonenumber);
        }
        if(received_explanation!= null){
            edit_detailed_text_detail.setText(received_explanation);
        }

        edit_detailed_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileSelection.class);

                intent.putExtra("profilenumber", received_profilenumber);
                intent.putExtra("name", edit_detailed_text_name.getText().toString());
                intent.putExtra("phonenumber", edit_detailed_text_phonenumber.getText().toString());
                intent.putExtra("explanation", edit_detailed_text_detail.getText().toString());
                intent.putExtra("caller", "editdetail");

                startActivity(intent);
            }
        });

        Button cancel_change_detail_button = (Button)findViewById(R.id.cancel_change_detail_button);
        cancel_change_detail_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences sharedPreferences = getSharedPreferences("phonenumbers",MODE_PRIVATE);

                Intent intent = new Intent(getApplicationContext(), DetailedPhonebook.class);

                intent.putExtra("name", received_name);
                intent.putExtra("phonenumber", received_phonenumber);
                intent.putExtra("explanation", received_explanation);
                intent.putExtra("drawable_number", sharedPreferences.getString(received_profilenumber,null));
                intent.putExtra("profilenumber", received_profilenumber);

                startActivity(intent);
            }
        });

        Button confirm_change_detail_button = (Button)findViewById(R.id.confirm_change_detail_button);
        confirm_change_detail_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DetailedPhonebook.class);

                intent.putExtra("name", edit_detailed_text_name.getText().toString());
                intent.putExtra("phonenumber", edit_detailed_text_phonenumber.getText().toString());
                intent.putExtra("explanation", edit_detailed_text_detail.getText().toString());
                intent.putExtra("drawable_number", received_drawable_number);
                intent.putExtra("profilenumber", received_profilenumber);

                startActivity(intent);
            }
        });

    }
    public static int findByString(Context context, String resourceName, String type) {
        return context.getResources().getIdentifier(resourceName, type, context.getPackageName());
    }
}