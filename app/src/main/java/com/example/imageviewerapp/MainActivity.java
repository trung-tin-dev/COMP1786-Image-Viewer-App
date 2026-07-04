package com.example.imageviewerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imgPhoto;
    private TextView imgInfo;
    private int currentIndex = 0;

    private final int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgPhoto = findViewById(R.id.imgPhoto);
        imgInfo = findViewById(R.id.imgInfo);

        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);

        updateImage();

        btnNext.setOnClickListener(v -> {
            currentIndex++;
            if (currentIndex >= images.length) {
                currentIndex = 0;
            }
            updateImage();
        });

        btnPrevious.setOnClickListener(v -> {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = images.length - 1;
            }
            updateImage();
        });
    }

    private void updateImage() {
        imgPhoto.setImageResource(images[currentIndex]);

        String infoText = getString(R.string.image_info_format, currentIndex + 1, images.length);
        imgInfo.setText(infoText);
    }
}