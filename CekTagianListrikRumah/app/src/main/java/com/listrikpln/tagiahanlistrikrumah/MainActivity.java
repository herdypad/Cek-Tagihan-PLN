package com.listrikpln.tagiahanlistrikrumah;

import static com.listrikpln.tagiahanlistrikrumah.adapter.RiwayatAdapter.DATA_NOMOR;

import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.InputGetToken;
import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.InputLogin;
import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.ResponTokenLogin;
import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.ResponTokenPln;
import com.listrikpln.tagiahanlistrikrumah.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String hasil_token = "";
    ResponTokenPln hasilRespomTokenPln = new ResponTokenPln();
    EditText ednoPln;
    LinearLayout layoutHasil,layoutError;
    TextView tvError;
    Button btnClear, btnSimpanRiwayat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ednoPln = findViewById(R.id.ed_token);
        layoutHasil = findViewById(R.id.layoutHasil);
        layoutError = findViewById(R.id.layouterror);
        tvError =  findViewById(R.id.tvError);
        btnClear = findViewById(R.id.clearall);
        btnSimpanRiwayat = findViewById(R.id.btn_simpan_riwayat);

        Button btnRiwayat = findViewById(R.id.btn_riwayat);
        Button cekTagihan = findViewById(R.id.btn_cek);



        //=========================status awal load============================
        //untuk extra intent
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (getIntent().getStringExtra(DATA_NOMOR) != null){
            String h = getIntent().getStringExtra(DATA_NOMOR);
            ednoPln.setText(h);

        }



        btnSimpanRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fitur Akan Segera Hadir", Toast.LENGTH_SHORT).show();
            }
        });


        cekTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataTokenloginBl();
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

    private void getTokenPln() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Waiting.....");
        dialog.show();

        if (hasil_token == ""){
            getDataTokenloginBl();
        }


        InputGetToken input2 =new  InputGetToken();
        input2.setCustomerNumber(ednoPln.getText().toString());

        Call<ResponTokenPln> request = RetrofitConfig.apiService2().abiltokenBl(hasil_token,input2);
        request.enqueue(new Callback<ResponTokenPln>() {
            @Override
            public void onResponse(Call<ResponTokenPln> call, Response<ResponTokenPln> response) {
                dialog.dismiss();
                Log.e("TAG", "onResponse: "+response.code() );

                if (response.code() == 401){
                    responError(401);

                }else if (response.code() == 422){
                    responError(422);
                }

                if (response.isSuccessful()){
                    hasilRespomTokenPln = response.body();
                    setHasil(response.body());


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

        TextView tvid = findViewById(R.id.tv_idmeteran);
        TextView tvnama = findViewById(R.id.tv_nama);
        TextView tvtarif = findViewById(R.id.tv_tarif);
        TextView tvperiode = findViewById(R.id.tv_periode);
        TextView tvJmltagihan = findViewById(R.id.tv_jmltagihan);

        tvid.setText(": "+hasil.getData().getCustomerNumber());
        tvnama.setText(": "+hasil.getData().getCustomerName());
        tvtarif.setText(": "+hasil.getData().getSegmentation()+"/"+hasil.getData().getPower());
        tvperiode.setText(": "+hasil.getData().getPeriod().get(0).toString());
        tvJmltagihan.setText("Rp. "+hasil.getData().getBills().get(0).getAmount());
    }

    private void responError(Integer code){
        layoutHasil.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        if (code == 401){
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






}