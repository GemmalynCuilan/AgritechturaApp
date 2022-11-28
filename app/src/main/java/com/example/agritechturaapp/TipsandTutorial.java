package com.example.agritechturaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TipsandTutorial extends AppCompatActivity {

    private ImageView menu_garden, menu_pest, menu_organic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipsand_tutorial);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipsandTutorial.this, Dashboard.class);
                startActivity(intent);
            }
        });
        ImageView menu_garden = (ImageView) findViewById(R.id.menu_garden);
        menu_garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipsandTutorial.this, Gardening.class);
                startActivity(intent);
            }
        });
        ImageView menu_pest = (ImageView) findViewById(R.id.menu_pest);
        menu_pest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipsandTutorial.this, PestControl.class);
                startActivity(intent);
            }
        });
        ImageView menu_organic = (ImageView) findViewById(R.id.menu_organic);
        menu_organic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipsandTutorial.this, OrganicFarming.class);
                startActivity(intent);
            }
        });
    }
}
