package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    Button DataBuku;
    LinearLayout BtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataBuku = (Button)findViewById(R.id.btn_data_buku);
        BtnLogout = (LinearLayout)findViewById(R.id.linear_logout);

        DataBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data_buku = new Intent(HomeActivity.this,DataBuku.class);
                startActivity(data_buku);
            }
        });

        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(logout);
            }
        });

    }
}
