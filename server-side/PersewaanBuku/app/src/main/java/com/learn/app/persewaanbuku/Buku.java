package com.learn.app.persewaanbuku;

import org.json.JSONException;
import org.json.JSONObject;

public class Buku {

    private int ID;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String kategori;
    private String status;
    private int type;

    public static final int INSERT_TYPE=1;
    public static final int UPDATE_TYPE=2;
    public static final int DELETE_TYPE=3;

    public Buku(int id, String judul, String pengarang, String penerbit, String kategori, String status, int type) {
        this.ID = id;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.kategori = kategori;
        this.status = status;
        this.type = type;
    }

    public static Buku generateInsertObject(String judul, String pengarang, String penerbit, String kategori, String status) {
        return new Buku(-1, judul, pengarang, penerbit, kategori, status, Buku.INSERT_TYPE);
    }

    public static Buku generateUpdateObject(int id, String judul, String pengarang, String penerbit, String kategori, String status) {
        return new Buku(id, judul, pengarang, penerbit, kategori, status, Buku.UPDATE_TYPE);
    }

    public static Buku generateDeleteObject(int id){
        return new Buku(id, null, null, null, null, null,Buku.DELETE_TYPE);
    }

    public JSONObject getJsonBuku(){
        JSONObject obj =  new JSONObject();
        try {
            switch (type) {
                case Buku.INSERT_TYPE:/*A*/
                    obj.put("judul_buku", this.judul);
                    obj.put("pengarang", this.pengarang);
                    obj.put("penerbit", this.penerbit);
                    obj.put("kategori_buku", this.kategori);
                    obj.put("status_buku", this.status);
                    break;
                case Buku.UPDATE_TYPE:/*B*/
                    obj.put("kode_buku  ", this.ID);
                    obj.put("judul_buku", this.judul);
                    obj.put("pengarang", this.pengarang);
                    obj.put("penerbit", this.penerbit);
                    obj.put("kategori_buku", this.kategori);
                    obj.put("status_buku", this.status);
                    break;
                case Buku.DELETE_TYPE:/*C*/
                    obj.put("kode_buku", this.ID);
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  obj;
    }

    public String getJsonString() {
        return getJsonBuku().toString();
    }
}
