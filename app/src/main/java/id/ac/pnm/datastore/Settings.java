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

public class Settings extends AppCompatActivity {
    EditText edtUsername, edtPass;
    Button btnSimpan;
    TextView btnBatal;
    SharedPrefManager sharedPrefManager;
    private AlertDialog progressDialog;
    String stringUsername, stringPass;
    String url = "https://student-portal4.000webhostapp.com/API2/menu/akun_user/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPrefManager = new SharedPrefManager(this);

        progressDialog = new ProgressDialog(Settings.this);
        progressDialog.setMessage("Mohon Tunggu !!!");

        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnBatal = findViewById(R.id.btnBatal);

        edtUsername.setText(sharedPrefManager.getSpUsername());
        edtPass.setText(sharedPrefManager.getSpPassword());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, MainActivity.class));
            }
        });
    }

    private void Update() {
        progressDialog.show();
        stringUsername = edtUsername.getText().toString().trim();
        stringPass = edtPass.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(Settings.this, "Update Data Sukses", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Settings.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("username",stringUsername);
                params.put("password",stringPass);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Settings.this);
        requestQueue.add(request);
    }
}