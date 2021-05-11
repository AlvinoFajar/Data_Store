package id.ac.pnm.datastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TambahProduk extends AppCompatActivity {

    EditText namaProduk,stokProduk, hargaProduk;
    Spinner kategoriProduk;
    Button btnSimpan;
    TextView btnBatal;
    private AlertDialog progressDialog;
    private RequestQueue mQueue;
    String  strNamaProduk, strStokProduk, strHargaProduk, strKategori;
    String url = "https://student-portal4.000webhostapp.com/API2/menu/data_produk/create_produk.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        progressDialog = new ProgressDialog(TambahProduk.this);
        progressDialog.setMessage("Mohon Tunggu !!!");

        namaProduk = findViewById(R.id.namaProduk);
        stokProduk = findViewById(R.id.stokProduk);
        hargaProduk = findViewById(R.id.hargaProduk);
        kategoriProduk = findViewById(R.id.kategoriProduk);
        mQueue = Volley.newRequestQueue(this);

        btnBatal = findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(TambahProduk.this, MainActivity.class);
                startActivity(login);
            }
        });

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Create();
            }
        });
    }


    private void Create() {
        progressDialog.show();
        strNamaProduk = namaProduk.getText().toString().trim();
        strStokProduk = stokProduk.getText().toString().trim();
        strHargaProduk = hargaProduk.getText().toString().trim();
        strKategori = kategoriProduk.getSelectedItem().toString().trim();


        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(TambahProduk.this, "Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(TambahProduk.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("nama_produk",strNamaProduk);
                params.put("stok_produk",strStokProduk);
                params.put("harga_produk",strHargaProduk);
                params.put("kategori",strKategori);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(TambahProduk.this);
        requestQueue.add(request);
    }

}