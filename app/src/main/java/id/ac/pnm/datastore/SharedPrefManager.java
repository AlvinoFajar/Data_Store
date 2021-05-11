package id.ac.pnm.datastore;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_IDUSER = "spIduser";
    public static final String SP_BUKUIN_APP = "spBukuinApp";
    public static final String SP_USERNAME = "spUsername";
    public static final String SP_PASSWORD = "spPassword";

    public static final String SP_SALDO = "spSaldo";
    public static final String SP_PEMASUKAN = "spPemasukan";
    public static final String SP_PENGELUARAN = "spPengeluaran";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_BUKUIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpIduser(){
        return sp.getString(SP_IDUSER, "");
    }
    public String getSpUsername(){
        return sp.getString(SP_USERNAME, "");
    }
    public String getSpPassword(){
        return sp.getString(SP_PASSWORD, "");
    }
    public String getSpSaldo(){
        return sp.getString(SP_SALDO, "");
    }
    public String getSpPemasukan(){
        return sp.getString(SP_PEMASUKAN, "");
    }
    public String getSpPengeluaran(){
        return sp.getString(SP_PENGELUARAN, "");
    }


    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}