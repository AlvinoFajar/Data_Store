package id.ac.pnm.datastore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditProduk extends AppCompatActivity {
    Button btnSimpanProduk;
    TextView btnBatal;
    private AlertDialog progressDialog;
    String txtIdProduk,txtNamaProduk,txtHargaProduk,txtStokProduk,txtKategoriProduk;
    EditText idProduk,namaProduk,stokProduk,hargaProduk,kategoriProduk;
    String url = "https://student-portal4.000webhostapp.com/API2/menu/data_produk/update_data.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produk);

        progressDialog = new ProgressDialog(EditProduk.this);
        progressDialog.setMessage("Mohon Tunggu !!!");

        Bundle bundle = getIntent().getExtras();
        txtIdProduk = bundle.getString("id_produk");
        txtNamaProduk = bundle.getString("namaProduk");
        txtStokProduk = bundle.getString("stokProduk");
        txtHargaProduk = bundle.getString("hargaProduk");
        txtKategoriProduk = bundle.getString("kategoriProduk");

        idProduk = findViewById(R.id.edtIdProduk);
        namaProduk = findViewById(R.id.edtNamaProduk);
        stokProduk = findViewById(R.id.edtStok);
        hargaProduk = findViewById(R.id.edtHarga);
        kategoriProduk = findViewById(R.id.edtKategori);
        btnSimpanProduk = findViewById(R.id.btnSimpanProduk);
        btnBatal = findViewById(R.id.btnBatal);
        idProduk.setText(txtIdProduk);
        namaProduk.setText(txtNamaProduk);
        stokProduk.setText(txtStokProduk);
        hargaProduk.setText(txtHargaProduk);
        kategoriProduk.setText(txtKategoriProduk);

        btnSimpanProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProduk.this, MainActivity.class));
            }
        });
    }
    private void Update() {
        progressDialog.show();
        txtIdProduk = idProduk.getText().toString().trim();
        txtNamaProduk = namaProduk.getText().toString().trim();
        txtStokProduk = stokProduk.getText().toString().trim();
        txtHargaProduk = hargaProduk.getText().toString().trim();
        txtKategoriProduk = kategoriProduk.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(EditProduk.this, "Update Data Sukses", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EditProduk.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_produk", txtIdProduk);
                params.put("nama_produk",txtNamaProduk);
                params.put("stok_produk",txtStokProduk);
                params.put("harga_produk",txtHargaProduk);
                params.put("kategori",txtKategoriProduk);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditProduk.this);
        requestQueue.add(request);
    }
}