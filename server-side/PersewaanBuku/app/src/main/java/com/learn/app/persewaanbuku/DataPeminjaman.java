package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DataPeminjaman extends AppCompatActivity {

    private ImageButton BtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_peminjaman);

        BtnBack = (ImageButton)findViewById(R.id.back_dataPeminjaman);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(DataPeminjaman.this, DashboardAdmin.class);
                startActivity(kembali);
            }
        });

    }
}
