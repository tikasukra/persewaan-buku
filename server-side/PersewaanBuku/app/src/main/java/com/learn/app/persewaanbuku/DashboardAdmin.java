package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardAdmin extends AppCompatActivity {

    Button DataBuku;
    Button DataBestBook;
    Button DataPeminjaman;
    Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        DataBuku = (Button)findViewById(R.id.mn_dataBuku);
        DataBestBook = (Button)findViewById(R.id.mn_bestBook);
        DataPeminjaman = (Button)findViewById(R.id.mn_dataPeminjaman);
        Logout = (Button)findViewById(R.id.btn_logout);

        DataBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(DashboardAdmin.this, DataBuku.class);
                startActivity(pindah);
            }
        });

        DataBestBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(DashboardAdmin.this, DataBestBook.class);
                startActivity(pindah);
            }
        });

        DataPeminjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(DashboardAdmin.this, DataPeminjaman.class);
                startActivity(pindah);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(DashboardAdmin.this, LoginActivity.class);
                startActivity(pindah);
            }
        });

    }
}
