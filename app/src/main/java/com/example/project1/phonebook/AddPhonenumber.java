package com.example.project1.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

public class AddPhonenumber extends AppCompatActivity {
    Button confirmAddButton;
    Button cancelAddButton;
    EditText addTextName;
    EditText addTextPhonenumber;
    RecyclerView recyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phonenumber);


        confirmAddButton = (Button)findViewById(R.id.confirm_add_button);
        confirmAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addTextName = (EditText)findViewById(R.id.add_text_name);
                addTextPhonenumber = (EditText)findViewById(R.id.add_text_phonenumber);

                if (addTextName.getText().toString().length() == 0){
                    Toast.makeText(v.getContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if (addTextPhonenumber.getText().toString().length() == 0) {
                    Toast.makeText(v.getContext(), "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), phonebook.class);

                    intent.putExtra("name", addTextName.getText().toString());
                    intent.putExtra("phonenumber", addTextPhonenumber.getText().toString());

                    startActivity(intent);
                }
            }
        });

        cancelAddButton = (Button)findViewById(R.id.cancel_add_button);
        cancelAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), phonebook.class);
                startActivity(intent);
            }
        });
    }
}
