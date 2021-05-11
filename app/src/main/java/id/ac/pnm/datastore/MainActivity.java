package id.ac.pnm.datastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton tambah, susu, mie, obat, sabun, minuman, snack, kebutuhanpokok,lainnya;
    Button SearchData;
    ImageView setting,btnSearch;
    TextView menuSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = (ImageSlider) findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.pengumuman));
        slideModels.add(new SlideModel(R.drawable.pengumuman4));
        slideModels.add(new SlideModel(R.drawable.pengumuman3));
        slideModels.add(new SlideModel(R.drawable.pengumuman2));
        imageSlider.setImageList(slideModels, true);

        tambah = findViewById(R.id.btntambah);
        sabun = findViewById(R.id.btnSabun);
        mie = findViewById(R.id.btnMie);
        sabun = findViewById(R.id.btnSabun);
        susu = findViewById(R.id.btnSusu);
        obat = findViewById(R.id.btnObat);
        snack = findViewById(R.id.btnSnack);
        minuman = findViewById(R.id.btnMinuman);
        kebutuhanpokok = findViewById(R.id.btnPokok);
        lainnya = findViewById(R.id.btnLainnya);
        setting = findViewById(R.id.setting);
        SearchData = findViewById(R.id.SearchData);


        SearchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AllKategori.class));
            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahProduk.class));
            }
        });
        sabun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori1.class));
            }
        });
        mie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori2.class));
            }
        });
        susu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori3.class));
            }
        });
        obat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori4.class));
            }
        });
        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori5.class));
            }
        });
        minuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori6.class));
            }
        });
        kebutuhanpokok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori7.class));
            }
        });
        lainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Kategori8.class));
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });

    }
}