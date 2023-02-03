package com.listrikpln.tagihanlistrikrumah;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.listrikpln.tagihanlistrikrumah.model.config.ResponseConfig;


public class CustomDialog extends Dialog
{
    private Context ctx;
    private ResponseConfig config;
    public CustomDialog(Context context, ResponseConfig config )
    {
        super(context);
        this.ctx = context;
        this.config = config;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        TextView textTitle = findViewById(R.id.textTile);
        TextView textBody = findViewById(R.id.text_info);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_r = findViewById(R.id.btn_2);

        textTitle.setText(config.getTitle());
        textBody.setText(config.getWhatNew());


        if(config.getIsForce() != "true")
        {
            btn_1.setVisibility(View.GONE);
            setCancelable(false);
        }
        else
        {
            btn_1.setVisibility(View.VISIBLE);
        }
        btn_1.setOnClickListener((v) -> {
            dismiss();
        });

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(config.getUrlApp()));
                ctx.startActivity(i);
            }
        });



    }

    @Override
    public void onBackPressed()
    {
        if (config.getIsForce()!="true") ((Activity)ctx).finish();

    }
}