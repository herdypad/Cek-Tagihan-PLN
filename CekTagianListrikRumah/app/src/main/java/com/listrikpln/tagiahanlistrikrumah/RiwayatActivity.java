package com.listrikpln.tagiahanlistrikrumah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.listrikpln.tagiahanlistrikrumah.adapter.RiwayatAdapter;
import com.listrikpln.tagiahanlistrikrumah.model.riwayat.RiwayatModel;

import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends AppCompatActivity {

    List<RiwayatModel> dataRiwayat = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        recyclerView = findViewById(R.id.recyclerView);
        ImageView ivBack = findViewById(R.id.tv_back);


        //membuat list
        //layout item ==v
        //model data dengan dummy
        RiwayatModel riwayatModel = new RiwayatModel();
        riwayatModel.setId("1");
        riwayatModel.setNoMeteran("543200673862");
        riwayatModel.setNama("herdy");
        riwayatModel.setPeriode("12 -12 -2023");
        riwayatModel.setJmhTagihan("Rp,10000");

        for (int i = 0; i < 3; i++) {
            dataRiwayat.add(riwayatModel);
        }

        //adapter
        recyclerView.setAdapter(new RiwayatAdapter(RiwayatActivity.this, dataRiwayat));
        //layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(RiwayatActivity.this));







        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RiwayatActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}