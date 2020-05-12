package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class IlmuPengetahuan extends AppCompatActivity {

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilmu_pengetahuan);

        btnBack = (ImageButton)findViewById(R.id.back_ilmu_pengetahuan);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(IlmuPengetahuan.this, MainActivity.class);
                startActivity(back);
            }
        });

    }
}
