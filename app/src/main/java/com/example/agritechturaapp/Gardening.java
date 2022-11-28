package com.example.agritechturaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Gardening extends AppCompatActivity {

    TextView titleTips, spade, spadeText, soil, soilText, seed,
            seedText, water, waterText, sun, sunText, climate, clText, pest, pestText, mulch,
            mulchText, harvest, harvestText;
    ImageView imageView7, imageView8, imageView, waterimg, sunImg, pestimg, mulimg, harvestimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardening);

        titleTips = findViewById(R.id.titleTips);
        spade = findViewById(R.id.spade);
        spadeText = findViewById(R.id.spadeText);
        soil = findViewById(R.id.soil);
        soilText = findViewById(R.id.soilText);
        seed = findViewById(R.id.seed);
        seedText = findViewById(R.id.seedText);
        water = findViewById(R.id.water);
        waterText = findViewById(R.id.waterText);
        sun = findViewById(R.id.sun);
        sunText = findViewById(R.id.sunText);
        climate = findViewById(R.id.climate);
        clText = findViewById(R.id.clText);
        pest = findViewById(R.id.pest);
        pestText = findViewById(R.id.pestText);
        mulch = findViewById(R.id.mulch);
        mulchText = findViewById(R.id.mulchText);
        harvest = findViewById(R.id.harvest);
        harvestText = findViewById(R.id.harvestText);


        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView = (ImageView) findViewById(R.id.imageView);
        waterimg = (ImageView) findViewById(R.id.waterimg);
        sunImg = (ImageView) findViewById(R.id.sunImg);
        pestimg= (ImageView) findViewById(R.id.pestimg);
        mulimg = (ImageView) findViewById(R.id.mulimg);
        harvestimg = (ImageView) findViewById(R.id.harvestimg);


        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gardening.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}