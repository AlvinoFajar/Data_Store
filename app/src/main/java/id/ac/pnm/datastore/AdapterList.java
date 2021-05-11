package id.ac.pnm.datastore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AJISETYA.
 */
public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> list_data;
    private Kategori1 kategori1;
    String namaProduk,stokProduk,hargaProduk,kategoriProduk;

    public AdapterList(Kategori1 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        kategori1 = mainActivity;
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(Kategori2 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(Kategori3 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(Kategori4 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(Kategori5 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(Kategori6 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(Kategori7 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(Kategori8 mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    public AdapterList(AllKategori mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_produk2, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Glide.with(context)
//                .load("http://192.168.95.77/app_blogvolley/img/" + list_data.get(position).get("gambar"))
//                .crossFade()
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.imghape);
        holder.txtNamaProduk.setText(list_data.get(position).get("nama_produk"));
        holder.txtStok.setText(list_data.get(position).get("stok_produk"));
        holder.txtHarga.setText(list_data.get(position).get("harga_produk"));
        holder.txtKategori.setText(list_data.get(position).get("kategori"));

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, EditProduk.class);
                intent.putExtra("id_produk",list_data.get(position).get("id_produk"));
                intent.putExtra("namaProduk", list_data.get(position).get("nama_produk"));
                intent.putExtra("stokProduk", list_data.get(position).get("stok_produk"));
                intent.putExtra("hargaProduk", list_data.get(position).get("harga_produk"));
                intent.putExtra("kategoriProduk", list_data.get(position).get("kategori"));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaProduk,txtStok,txtHarga,txtKategori;
        Button btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNamaProduk = (TextView) itemView.findViewById(R.id.txtNamaProduk);
            txtStok = (TextView) itemView.findViewById(R.id.txtStok);
            txtHarga = (TextView) itemView.findViewById(R.id.txtHarga);
            txtKategori = (TextView) itemView.findViewById(R.id.txtKategori);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
        }
    }
}
