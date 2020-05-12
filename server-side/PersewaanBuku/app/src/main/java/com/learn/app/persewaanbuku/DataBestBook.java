package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DataBestBook extends AppCompatActivity {

    private ImageButton BtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_best_book);

        BtnBack = (ImageButton)findViewById(R.id.back_dataBestBook);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(DataBestBook.this, DashboardAdmin.class);
                startActivity(kembali);
            }
        });

    }
}
