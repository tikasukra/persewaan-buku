package com.learn.app.persewaanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBuku extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText inpESBN,inpJudul, inpPengarang ,inpPenerbit, inpKategori;
    Button btnAdd;
    Button btnEdit;
    Button btnDelete;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buku);

        myDb = new DatabaseHelper(this);
        inpESBN = (EditText)findViewById(R.id.input_esbn);
        inpJudul = (EditText)findViewById(R.id.input_judul);
        inpPengarang = (EditText)findViewById(R.id.input_pengarang);
        inpPenerbit = (EditText)findViewById(R.id.input_penerbit);
        inpKategori = (EditText)findViewById(R.id.input_kategori);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnDelete= (Button)findViewById(R.id.btnDelete);
        btnShow= (Button)findViewById(R.id.btnShow);

        addAction();
        editAction();
        deleteAction();
        showAllAction();

    }

    public void addAction() {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(inpESBN.getText().toString(),
                                inpJudul.getText().toString(),
                                inpPengarang.getText().toString(),
                                inpPenerbit.getText().toString(),
                                inpKategori.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(AddBuku.this,
                                    "Data Berhasil Ditambahkan",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddBuku.this,
                                    "Data Gagal Ditambahkan",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void editAction()
    {
        btnEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.insertData(inpESBN.getText().toString(),
                                inpJudul.getText().toString(),
                                inpPengarang.getText().toString(),
                                inpPenerbit.getText().toString(),
                                inpKategori.getText().toString());
                        if(isUpdated == true)
                            Toast.makeText(AddBuku.this,
                                    "Data Berhasil Ditambahkan",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddBuku.this,
                                    "Data Gagal Ditambahkan",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void deleteAction()
    {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String esbn = inpESBN.getText().toString();
                        boolean deletedRows = myDb.deleteData(esbn);
                        if(deletedRows)
                            Toast.makeText(AddBuku.this,
                                    "Data Berhasil Dihapus",
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddBuku.this,
                                    "ESBN tidak terdaftar",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void showAllAction() {
        btnShow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ESBN :"+ res.getString(0)+"\n");
                            buffer.append("Judul :"+ res.getString(1)+"\n");
                            buffer.append("Pengarang :"+ res.getString(2)+"\n");
                            buffer.append("Penerbit :"+ res.getString(3)+"\n\n");
                            buffer.append("Kategori :"+ res.getString(4)+"\n\n");
                        }
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

}
