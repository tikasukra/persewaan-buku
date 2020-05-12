package com.learn.app.persewaanbuku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Button ShowAll;
    ImageButton FiksiSastra;
    ImageButton GayaHidup;
    ImageButton IlmuPengetahun;
    ImageButton KompTeknologi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowAll = (Button)findViewById(R.id.main_showAll);
        ShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bestbook = new Intent(MainActivity.this,BestBook.class);
                startActivity(bestbook);
            }
        });

        FiksiSastra = (ImageButton) findViewById(R.id.main_fiksiSastra);
        FiksiSastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fiksisastra = new Intent(MainActivity.this,FiksiSastra.class);
                startActivity(fiksisastra);
            }
        });

        GayaHidup = (ImageButton) findViewById(R.id.main_gayaHidup);
        GayaHidup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gayahidup = new Intent(MainActivity.this,GayaHidup.class);
                startActivity(gayahidup);
            }
        });

        IlmuPengetahun = (ImageButton)findViewById(R.id.main_ilmuPengetahuan);
        IlmuPengetahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ilmupengetahuan = new Intent(MainActivity.this,IlmuPengetahuan.class);
                startActivity(ilmupengetahuan);
            }
        });

        KompTeknologi = (ImageButton) findViewById(R.id.main_kompTeknologi);
        KompTeknologi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kompteknologi = new Intent(MainActivity.this,KomputerTeknologi.class);
                startActivity(kompteknologi);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.nota:
                        startActivity(new Intent(getApplicationContext(), NotaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
