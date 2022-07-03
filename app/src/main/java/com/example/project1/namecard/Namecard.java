package com.example.project1.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.project1.R;
import com.example.project1.gallery.gallery;
import com.example.project1.phonebook.phonebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Namecard extends AppCompatActivity {

    RecyclerView recyclerView;
    NamecardAdapter adapter;
    static ArrayList<String> received_image= new ArrayList<>();
    static ArrayList<String> received_text= new ArrayList<>();
    static ArrayList<Integer> received_size= new ArrayList<>();
    static ArrayList<Float> received_X= new ArrayList<>();
    static ArrayList<Float> received_Y= new ArrayList<>();

    public static int numofarray = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namecard);

        ImageView selectImage = (ImageView)findViewById(R.id.add_button);
        selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), about.class);
                startActivity(intent);
            }
        });
        //about -> combineImage -> Namecard 통해서 보내온 file 저장.
        Intent getNameCardIntent = getIntent();
        if(getNameCardIntent.getStringExtra("ImageNum")!=null) {

            received_image.add(getNameCardIntent.getStringExtra("ImageNum"));
            received_text.add(getNameCardIntent.getStringExtra("text"));
            received_X.add(getNameCardIntent.getFloatExtra("TransX", 0));
            received_Y.add(getNameCardIntent.getFloatExtra("TransY", 0));
            received_size.add(getNameCardIntent.getIntExtra("TextSize", 10));
        }
        if(numofarray!=0){
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//            DividerItemDecoration dividerItemDecoration =   new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
            RecyclerDecoration spaceDecoration = new RecyclerDecoration(20);

            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.addItemDecoration(spaceDecoration);

            adapter = new NamecardAdapter(getApplicationContext());
            for (int i = 1; i < numofarray + 1; i++) {
                Drawable drawable = getResources().getDrawable(findByString(getApplicationContext(),"pic_" + received_image.get(i-1),"drawable"));
                adapter.setImageArrayData(drawable);
                adapter.setImageNumberArrayData(received_image.get(i-1));
                adapter.setTextArrayData(received_text.get(i-1));
                adapter.setFontSizeArrayList(received_size.get(i-1));
                adapter.setTransXArrayList(received_X.get(i-1));
                adapter.setTransYArrayList(received_Y.get(i-1));
            }

            recyclerView.setAdapter(adapter);

        }

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
                    case R.id.namecard:
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