package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GayaHidup extends AppCompatActivity {

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaya_hidup);

        btnBack = (ImageButton)findViewById(R.id.back_gaya_hidup);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(GayaHidup.this, MainActivity.class);
                startActivity(back);
            }
        });

    }
}
