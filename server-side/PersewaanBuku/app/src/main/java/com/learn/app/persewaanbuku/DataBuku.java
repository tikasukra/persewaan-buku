package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DataBuku extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText inpKode, inpJudul, inpPengarang, inpPenerbit, inpKategori, inpStatus;
    Button btnAdd;
    Button btnEdit;
    Button btnDelete;
    Button btnShow;

    private ImageButton BtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_buku);

        BtnBack = (ImageButton) findViewById(R.id.back_dataBuku);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(DataBuku.this, DashboardAdmin.class);
                startActivity(kembali);
            }
        });

        myDB = new DatabaseHelper(this);
        inpKode = (EditText) findViewById(R.id.db_kodeBuku);
        inpJudul = (EditText) findViewById(R.id.db_judulBuku);
        inpPengarang = (EditText) findViewById(R.id.db_pengarang);
        inpPenerbit = (EditText) findViewById(R.id.db_penerbit);
        inpKategori = (EditText) findViewById(R.id.db_kategori);
        inpStatus = (EditText) findViewById(R.id.db_status);
        btnAdd = (Button) findViewById(R.id.db_add);
        btnEdit = (Button) findViewById(R.id.db_edit);
        btnDelete = (Button) findViewById(R.id.db_delete);
        btnShow = (Button) findViewById(R.id.db_showAll);

        addAction();
        editAction();
        deleteAction();
        showAllAction();

    }

    public void showMessage(String title, String message) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void addAction() {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Buku buku = Buku.generateInsertObject(
                                inpJudul.getText().toString(),
                                inpPengarang.getText().toString(),
                                inpPenerbit.getText().toString(),
                                inpKategori.getText().toString(),
                                inpStatus.getText().toString());
                        new ApiConnect(DataBuku.this, buku).execute(ApiConnect.INSERT_ACTION + "");
                    }
                });
    }

    public void showAllAction() {
        btnShow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ApiConnect(DataBuku.this).execute(ApiConnect.VIEW_ACTION + "");
                    }
                });
    }

    public void editAction() {
        btnEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Buku buku = Buku.generateUpdateObject(
                                Integer.parseInt(inpKode.getText().toString()),
                                inpJudul.getText().toString(),
                                inpPengarang.getText().toString(),
                                inpPenerbit.getText().toString(),
                                inpKategori.getText().toString(),
                                inpStatus.getText().toString());
                        new ApiConnect(DataBuku.this, buku).execute(ApiConnect.UPDATE_ACTION + "");
                    }
                });
    }

    public void deleteAction() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Buku buku = Buku.generateDeleteObject(
                                Integer.parseInt(inpKode.getText().toString()));
                        new ApiConnect(DataBuku.this, buku).execute(ApiConnect.DELETE_ACTION + "");
                    }
                });
    }

}