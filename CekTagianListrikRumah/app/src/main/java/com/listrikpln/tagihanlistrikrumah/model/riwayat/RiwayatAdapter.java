package com.listrikpln.tagihanlistrikrumah.model.riwayat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.listrikpln.tagihanlistrikrumah.MainActivity;
import com.listrikpln.tagihanlistrikrumah.R;
import com.listrikpln.tagihanlistrikrumah.RiwayatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyViewHolder> {

    public static final String DATA_NOMOR = "datanomor";
    private Context context;
    private List<RiwayatModel> data = new ArrayList<>();

    private MyDatabase myDatabase;


    public RiwayatAdapter(Context context, List<RiwayatModel> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //menyambungkan layout item
        View itemView = LayoutInflater.from(context).inflate(R.layout.riwayat_item,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //set data

        Locale id = new Locale("ID", "ID");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(id);

        holder.tvNoMeteran.setText(": "+data.get(position).getNoMeteran().toString());
        holder.tvNama.setText(data.get(position).getNama().toString());
        holder.tvPeriode.setText(data.get(position).getPeriode().toString());
        holder.tvJmlTagihan.setText(defaultFormat.format(data.get(position).getJmhTagihan()).toString());
        holder.ambilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra(DATA_NOMOR,data.get(position).getNoMeteran());
                context.startActivity(i);
            }
        });

        holder.tvDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.daoModel().delete(data.get(position));
                Intent i = new Intent(context, RiwayatActivity.class);
                context.startActivity(i);

            }
        });


    }


    @Override
    public int getItemCount() {
        //jumlah data
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoMeteran,tvNama,tvPeriode,tvJmlTagihan,tvDeleteItem;
        LinearLayout ambilData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //memperkenalkan komponen item
            tvNoMeteran = itemView.findViewById(R.id.tvmeteranL);
            tvNama = itemView.findViewById(R.id.tvNamaL);
            tvPeriode = itemView.findViewById(R.id.tv_periodeL);
            tvJmlTagihan = itemView.findViewById(R.id.tvJumlahL);

            ambilData = itemView.findViewById(R.id.LayoutAmbildata);

            tvDeleteItem = itemView.findViewById(R.id.tv_hapus_item);

            myDatabase = MyDatabase.getInstance(context);


        }
    }

}
//1. constraktor
//2 extent ke class recyclerview
//3 alt enter sampe hilang eorrr
