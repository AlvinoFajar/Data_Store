package id.ac.pnm.datastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainLogin extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    EditText txtNama, txtPassword;
    Button logint,aktivasi;
    ProgressDialog pd;
    String url = "https://student-portal4.000webhostapp.com/API2/menu/akun_user/read_akun.php";
    private RequestQueue mQueue;
    String nama, password, isiNama, isiPassword, hasil,userNama;
    Button btnLogin;

    ArrayList<HashMap<String, String>> hasilNama = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> hasilPassword = new ArrayList<HashMap<String, String>>();

    public static String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_login);

        pd = new ProgressDialog(MainLogin.this);
        pd.setMessage("Mohon Tunggu !!!");

        logint = findViewById(R.id.login);

        logint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (MainLogin.this, MainActivity.class));
            }
        });


        sharedPrefManager = new SharedPrefManager(this);

//        if (sharedPrefManager.getSPSudahLogin()) {
//            startActivity(new Intent(MainActivity.this, home.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            finish();
//        }

        txtNama = findViewById(R.id.username);
        txtPassword = findViewById(R.id.edtPassword);
        mQueue = Volley.newRequestQueue(this);

        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adaInternet()) {
                    // tampilkan peta
                    cek();
                } else {
                    // tampilkan pesan
                    Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void cek() {

        if (txtNama.getText().toString().equals("")) {
            Toast.makeText(this, "Masukkan Username", Toast.LENGTH_LONG).show();
        } else if (txtPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_LONG).show();
        } else {
            jsonparse();
        }
    }

    private void jsonparse() {
        pd.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);

                                nama = result.getString("username");
                                password = result.getString("password");

                                HashMap<String, String> dataNAMA = new HashMap<>();
                                HashMap<String, String> dataPASSWORD = new HashMap<>();

                                dataNAMA.put("username", nama);
                                dataPASSWORD.put("password", password);

                                hasilNama.add(dataNAMA);
                                hasilPassword.add(dataPASSWORD);

                                isiNama = txtNama.getText().toString();
                                isiPassword = txtPassword.getText().toString();
                                if (nama.equals(isiNama) && password.equals(isiPassword)) {
                                    hasil = "berhasil";

                                    success();
                                    break;
                                } else {
                                    hasil = "gagal";
                                }
                            }
                            error();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private boolean adaInternet() {
        ConnectivityManager koneksi = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return koneksi.getActiveNetworkInfo() != null;
    }

    public void success() {
        pd.dismiss();
        if (hasil.equals("berhasil")) {
            Intent intent = new Intent(MainLogin.this, MainActivity.class);
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
            sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, nama);
            userNama = sharedPrefManager.getSpUsername();
            startActivity(intent);
            ambil();
        }
    }

    private void ambil() {
        String namaku = "username";
        Intent i = new Intent(MainLogin.this, MainActivity.class);
        i.putExtra(namaku, userNama);
        startActivity(i);
    }

    public void error() {
        pd.dismiss();
        if (hasil.equals("gagal")) {
            Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_LONG).show();
        }
    }
}