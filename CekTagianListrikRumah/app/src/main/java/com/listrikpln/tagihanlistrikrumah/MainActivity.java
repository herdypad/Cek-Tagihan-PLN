package com.listrikpln.tagihanlistrikrumah;

import static com.listrikpln.tagihanlistrikrumah.model.riwayat.RiwayatAdapter.DATA_NOMOR;

import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.listrikpln.tagihanlistrikrumah.model.bukalapak.InputGetToken;
import com.listrikpln.tagihanlistrikrumah.model.bukalapak.InputLogin;
import com.listrikpln.tagihanlistrikrumah.model.bukalapak.ResponTokenLogin;
import com.listrikpln.tagihanlistrikrumah.model.bukalapak.ResponTokenPln;
import com.listrikpln.tagihanlistrikrumah.model.config.ResponseConfig;
import com.listrikpln.tagihanlistrikrumah.model.riwayat.MyDatabase;
import com.listrikpln.tagihanlistrikrumah.model.riwayat.RiwayatModel;
import com.listrikpln.tagihanlistrikrumah.retrofit.RetrofitConfig;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String hasil_token = "";
    private static Integer hasil_tagihan= 0;
    private ResponTokenPln hasilRespomTokenPln = new ResponTokenPln();
    private ResponseConfig responseConfig = new ResponseConfig();
    private EditText ednoPln;
    private LinearLayout layoutHasil,layoutError;
    private TextView tvError,tvid,tvnama,tvtarif,tvperiode,tvJmltagihan;
    private Button btnClear, btnSimpanRiwayat;

    private RiwayatModel riwayatModel = new RiwayatModel();

    private MyDatabase myDatabase;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvid = findViewById(R.id.tv_idmeteran);
        tvnama = findViewById(R.id.tv_nama);
        tvtarif = findViewById(R.id.tv_tarif);
        tvperiode = findViewById(R.id.tv_periode);
        tvJmltagihan = findViewById(R.id.tv_jmltagihan);

        ednoPln = findViewById(R.id.ed_token);
        layoutHasil = findViewById(R.id.layoutHasil);
        layoutError = findViewById(R.id.layouterror);
        tvError =  findViewById(R.id.tvError);
        btnClear = findViewById(R.id.clearall);
        btnSimpanRiwayat = findViewById(R.id.btn_simpan_riwayat);

        Button btnRiwayat = findViewById(R.id.btn_riwayat);
        Button cekTagihan = findViewById(R.id.btn_cek);


        //database koneksi
        myDatabase = MyDatabase.getInstance(getApplicationContext());

        //admob
        AdView adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.benner));
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //===



        //=========================status awal load============================
        layoutHasil.setVisibility(View.GONE);
        tvJmltagihan.setText("");
        //untuk extra intent
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (getIntent().getStringExtra(DATA_NOMOR) != null){
            String h = getIntent().getStringExtra(DATA_NOMOR);
            ednoPln.setText(h);

        }


        //=============================/////
        cekTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasil_token == ""){
                    getDataTokenloginBl();
                }
                getTokenPln();

            }
        });

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RiwayatActivity.class);
                startActivity(i);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });



    }


    //...............................metode tambahan................................


    private void ambilDataConfig(){
        Call<ResponseConfig> request = RetrofitConfig.cekConfig().ambilConfig();
        request.enqueue(new Callback<ResponseConfig>() {
            @Override
            public void onResponse(Call<ResponseConfig> call, Response<ResponseConfig> response) {

                if (response.code() == 200 && response.isSuccessful()){
                    Log.e("TAG", "onResponse: 3"+response.code() );
                    responseConfig = response.body();
                    dialogShow(responseConfig);

                }
            }

            @Override
            public void onFailure(Call<ResponseConfig> call, Throwable t) {

            }
        });
    }

    //remote
    private void dialogShow(ResponseConfig config)
    {
        Integer statusVesi = cekVersi();
        Integer statusVesiOnline = Integer.valueOf(config.getVersion());

        Log.e("TAG", "dialogShow: "+config.getVersion()+statusVesi.toString() );

        //status versi harus lebih rendah kalau tidak popup akan muncul
        if(statusVesi == statusVesiOnline  ){
            return;
        }else if (statusVesi <= statusVesiOnline ){
            CustomDialog dialog=new CustomDialog(this, config);
            dialog.show();
        }
        else {
            return;
        }


    }
    private Integer cekVersi(){
        PackageManager manager = getBaseContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    getBaseContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Integer version = info.versionCode;
        return version;
    }





    private void getTokenPln() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Waiting.....");
        dialog.show();

        InputGetToken input2 =new  InputGetToken();
        input2.setCustomerNumber(ednoPln.getText().toString());

        Call<ResponTokenPln> request = RetrofitConfig.apiService2().abiltokenBl(hasil_token,input2);
        request.enqueue(new Callback<ResponTokenPln>() {
            @Override
            public void onResponse(Call<ResponTokenPln> call, Response<ResponTokenPln> response) {
                dialog.dismiss();
                Log.e("TAG", "onResponse: "+response.code() );

                switch (response.code()){
                    case 401:
                        responError(401);
                        break;
                    case 422:
                        responError(422);
                        break;
                    case 200:
                        Log.e("TAG", "onResponse: final "+response.code() );
                        hasilRespomTokenPln = response.body();
                        setHasil(response.body());
                        break;
                }

            }

            @Override
            public void onFailure(Call<ResponTokenPln> call, Throwable t) {
                dialog.dismiss();
                //jika error ambil data
                layoutError.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getDataTokenloginBl() {
        String[] hasilToken = {"kosong"};
        InputLogin input1 = new InputLogin();
        input1.setAuthenticityToken("");
        input1.setApplicationId(1);

        Call<ResponTokenLogin> request = RetrofitConfig.apiService().abiltokenlogin(input1);
        request.enqueue(new Callback<ResponTokenLogin>() {
            @Override
            public void onResponse(Call<ResponTokenLogin> call, Response<ResponTokenLogin> response) {
                if (response.isSuccessful()){

                    hasilToken[0] =  response.body().getAccessToken();
                    hasil_token = (hasilToken[0]);

                    if (response.code() != 200){
                        Log.e("TAG", "onResponse ada error: "+ response.toString() );
                    }
                    getTokenPln();

                }else {
                    //jika error ambil data
                    layoutError.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ResponTokenLogin> call, Throwable t) {
                //jika error ambil data
                layoutError.setVisibility(View.VISIBLE);
            }
        });

    }

    private void setHasil(ResponTokenPln hasil) {
        layoutHasil.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);

        hasil_tagihan = hasil.getData().getBills().get(0).getAmount();

        //rubah format currency
        Locale id = new Locale("ID", "ID");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(id);


        tvid.setText(": "+hasil.getData().getCustomerNumber());
        tvnama.setText(": "+hasil.getData().getCustomerName());
        tvtarif.setText(": "+hasil.getData().getSegmentation()+"/"+hasil.getData().getPower());
        tvperiode.setText(": "+hasil.getData().getPeriod().get(0).toString());
        tvJmltagihan.setText(defaultFormat.format(hasil.getData().getBills().get(0).getAmount()));


        //simpan hasil jika ada
        try {
            RiwayatModel a = myDatabase.daoModel().findPeriode(tvperiode.getText().toString()).get(0);
        }catch (Exception e){
            riwayatModel.setPeriode(tvperiode.getText().toString());
            riwayatModel.setJmhTagihan(Integer.valueOf(hasil_tagihan));
            riwayatModel.setNoMeteran(ednoPln.getText().toString());
            riwayatModel.setNama(tvnama.getText().toString());
            if (tvJmltagihan.getText().toString() == ""){
                Toast.makeText(MainActivity.this, "Tidak Dapat Menyimpan", Toast.LENGTH_SHORT).show();
            }else {
                myDatabase.daoModel().insert(riwayatModel);
                Toast.makeText(MainActivity.this, "Berhasil Menyimpan", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void responError(Integer code){
        layoutHasil.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        if (code == 401){
            hasil_token = "";
            tvError.setText("auth error");

        }else if (code == 422){
            tvError.setText("Tagihan Tidak Ditemukan \nAtau Sudah Lunas ");
        }else {
            tvError.setText("Tagihan Tidak Ditemukan\n Atau Sudah Lunas  ");
        }

        if (ednoPln.length() != 12){
            tvError.setText("ID Pelanggan maks. 12 digit");
        }
    }

    private void clearAll(){
        layoutError.setVisibility(View.GONE);
        layoutHasil.setVisibility(View.GONE);
        ednoPln.setText("");

    }

    private void setDataPref(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("IDIPEL", ednoPln.getText().toString());
        myEdit.apply();
        Log.e("TAG1", "ambilDataPref: save" );
    }

    private void ambilDataPref(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("IDIPEL", "");
        if (s1 !=""| s1 ==null){
            ednoPln.setText(s1);
        }


    }


    @Override
    protected void onStop() {
        super.onStop();
        setDataPref();
        Log.e("TAG1", "ambilDataPref: "+"onStop" );
    }


    @Override
    protected void onStart() {
        super.onStart();
        ambilDataPref();
        Log.e("TAG1", "ambilDataPref: "+"onStar" );
//        layoutHasil.setVisibility(View.VISIBLE);
    }
}