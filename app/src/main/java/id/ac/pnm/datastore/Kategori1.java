package id.ac.pnm.datastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Kategori1 extends AppCompatActivity {

    private RecyclerView lvhape;

    ProgressDialog pd;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ImageButton btnSearchKat1;
    EditText search_kategori1;
    String nama_produk, stok_produk, harga_produk,kategori, SearchAll, hasilSearch;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori1);

        search_kategori1 = (EditText) findViewById(R.id.search_kategori1);

        requestQueue = Volley.newRequestQueue(Kategori1.this);
        pd = new ProgressDialog(Kategori1.this);
        pd.setMessage("Mencari");

        lvhape = (RecyclerView) findViewById(R.id.lvhape);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvhape.setLayoutManager(llm);

        hasilSearch = "";
        String kosong = "";

        list_data = new ArrayList<HashMap<String, String>>();


        btnSearchKat1 = (ImageButton) findViewById(R.id.btnSearchKat1);
        btnSearchKat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                list_data.clear();
                SearchAll = search_kategori1.getText().toString().toLowerCase();
                if (SearchAll.equals(hasilSearch)){
                    jsonParse();
                }else {
                    jsonParseSearch();
                }
            }
        });

        if (hasilSearch.equals(kosong)){
            jsonParse();
        }else {
            jsonParseSearch();
        }

    }


    private void jsonParse() {
        String url = "https://student-portal4.000webhostapp.com/API2/menu/data_produk/read_data.php";

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);

                        kategori = json.getString("kategori").toLowerCase();

                        SearchAll = search_kategori1.getText().toString().toLowerCase();
                        String kategorinya = "sabun";

                        if (kategori.equals(kategorinya)) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("id_produk", json.getString("id_produk"));
                            map.put("nama_produk", json.getString("nama_produk"));
                            map.put("stok_produk", json.getString("stok_produk"));
                            map.put("harga_produk", json.getString("harga_produk"));
                            map.put("kategori", json.getString("kategori"));
                            list_data.add(map);
                            pd.dismiss();
                            AdapterList adapter = new AdapterList(Kategori1.this, list_data);
                            lvhape.setAdapter(adapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Kategori1.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void jsonParseSearch() {
        String url = "https://student-portal4.000webhostapp.com/API2/menu/data_produk/read_data.php";

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);

                        nama_produk = json.getString("nama_produk").toLowerCase();
                        kategori = json.getString("kategori").toLowerCase();

                        SearchAll = search_kategori1.getText().toString().toLowerCase();
                        String kategorinya = "sabun";

                        if (kategori.equals(kategorinya) && nama_produk.equals(SearchAll)) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("id_produk", json.getString("id_produk"));
                            map.put("nama_produk", json.getString("nama_produk"));
                            map.put("stok_produk", json.getString("stok_produk"));
                            map.put("harga_produk", json.getString("harga_produk"));
                            map.put("kategori", json.getString("kategori"));
                            list_data.add(map);
                            pd.dismiss();
                            AdapterList adapter = new AdapterList(Kategori1.this, list_data);
                            lvhape.setAdapter(adapter);
                        }else {
                            pd.dismiss();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Kategori1.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }
}