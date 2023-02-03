package com.listrikpln.tagihanlistrikrumah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.listrikpln.tagihanlistrikrumah.model.riwayat.MyDatabase;
import com.listrikpln.tagihanlistrikrumah.model.riwayat.RiwayatAdapter;
import com.listrikpln.tagihanlistrikrumah.model.riwayat.RiwayatModel;
import com.listrikpln.tagihanlistrikrumah.model.riwayat.TotalTagihanModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RiwayatActivity extends AppCompatActivity {

    private List<RiwayatModel> dataRiwayat = new ArrayList<>();
    private List<RiwayatModel> dataRiwayat2 = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyDatabase myDatabase;

    private AdView mAdView;

    private TextView tvTotalTagihan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        recyclerView = findViewById(R.id.recyclerView);
        ImageView ivBack = findViewById(R.id.tv_back);
        tvTotalTagihan = findViewById(R.id.tvTotalTagihan);

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


        Locale id = new Locale("ID", "ID");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(id);

        //ambil data total tagihan
        TotalTagihanModel tagihanModel1;
        tagihanModel1 = myDatabase.daoModel().total();
        if (tagihanModel1 != null){
            try {
                tvTotalTagihan.setText(defaultFormat.format(tagihanModel1.getTotalTagihan()));
//                tvTotalTagihan.setText(" RP. "+tagihanModel1.getTotalTagihan().toString());
//                Log.e("TAG", "onCreate:11 "+tagihanModel1.getTotalTagihan() );
            }catch (Exception i){
                tvTotalTagihan.setText("RP. 000");
//                Log.e("TAG", "onCreate:11 "+"eror" );
            }
        }








        //1. membuat list
        //2.layout item ==v
        //3. model data
        dataRiwayat.addAll(myDatabase.daoModel().getAll());

        //3.a model data dengan dummy
//        RiwayatModel riwayatModel = new RiwayatModel();
//        riwayatModel.setId(1);
//        riwayatModel.setNoMeteran("543200673862");
//        riwayatModel.setNama("herdy");
//        riwayatModel.setPeriode("12 -12 -2023");
//        riwayatModel.setJmhTagihan(10000);
//
//        for (int i = 0; i < 3; i++) {
//            dataRiwayat2.add(riwayatModel);
//        }


        //4. adapter
        recyclerView.setAdapter(new RiwayatAdapter(RiwayatActivity.this, dataRiwayat));
        //5. layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(RiwayatActivity.this));


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RiwayatActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
    }

    //saat restar
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("FragmentX","Step OnStart");
        dataRiwayat.clear();
        dataRiwayat.addAll(myDatabase.daoModel().getAll());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}