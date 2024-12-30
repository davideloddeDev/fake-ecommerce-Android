package com.nuboo.fakeecommerce;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class ImageViewerActivity extends AppCompatActivity {

    private ImageView fullScreenImage;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image_viewer);

        fullScreenImage = findViewById(R.id.full_screen_image);
        backButton = findViewById(R.id.back_button);

        String imageUrl = getIntent().getStringExtra("image_url");
        Glide.with(this)
                .load(imageUrl)
                .into(fullScreenImage);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Chiude l'attività corrente
            }
        });


    }
}