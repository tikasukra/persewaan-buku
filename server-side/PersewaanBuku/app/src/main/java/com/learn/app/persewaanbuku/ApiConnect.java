package com.learn.app.persewaanbuku;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiConnect extends AsyncTask<String, String, String> {

    private ProgressDialog pd;
    private Context context;
    private Buku buku;
    private int action;

    public static final int INSERT_ACTION = 1;
    public static final int UPDATE_ACTION = 2;
    public static final int DELETE_ACTION = 3;
    public static final int VIEW_ACTION = 4;

    protected void onPreExecute(){
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }
    @Override
    protected String doInBackground(String... params) {
        this.action = Integer.parseInt(params[0]);
        switch (action){
            case ApiConnect.VIEW_ACTION :
                return readAPI();
            case ApiConnect.UPDATE_ACTION :
                return updateAPI();
            case ApiConnect.DELETE_ACTION :
                return deleteAPI();
            case ApiConnect.INSERT_ACTION:
                return insertAPI();
            default:
                return "something is went wrong";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (pd.isShowing()){
            pd.dismiss();
        }
        switch (action){
            case ApiConnect.VIEW_ACTION:
                ((DataBuku)context).showMessage("Data",result);
                break;
            default:
                Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    }

    private String JSONDecoder(String in){
        try {/*D*/
            JSONObject reader = new JSONObject(in);
            JSONArray records = reader.getJSONArray("records");
            String result = "";
            for(int i=0;i<records.length();i++){
                JSONObject items = records.getJSONObject(i);
                String judul = items.getString("judul_buku");
                String pengarang = items.getString("pengarang");
                String penerbit = items.getString("penerbit");
                String kategori = items.getString("kategori_buku");
                String status = items.getString("status_buku");
                result = result+"Judul Buku : "+judul+"\nPengarang : "+pengarang+"\nPenerbit : "+penerbit+"\nKategori : "+kategori+"\nStatus Buku :"+status+"\n\n";
            }
            Log.d("result",result);
            return result;

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("result","NULL");
            return null;
        }
    }

    private String readAPI(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {/*E*/
            URL url = new URL("http://10.0.2.2/api-persewaan-buku/product/read.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }
            return JSONDecoder(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {/*F*/
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String insertAPI(){
        HttpURLConnection connection = null;
        if (buku == null){
            return "buku is null";
        }
        try{/*G*/
            URL url = new URL("http://10.0.2.2/api-persewaan-buku/product/create.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = buku.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String updateAPI(){
        HttpURLConnection connection = null;
        if (buku == null){
            return "buku is null";
        }
        try{
            URL url = new URL("http://10.0.2.2/api-persewaan-buku/product/update.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = buku.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String deleteAPI(){
        HttpURLConnection connection = null;
        if (buku == null){
            return "product is null";
        }
        try{
            URL url = new URL("http://10.0.2.2/api-persewaan-buku/product/delete.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = buku.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public ApiConnect(Context context){
        this.context = context;
    }

    public ApiConnect(Context context,Buku buku){
        this.context = context;
        this.buku = buku;
    }

}

