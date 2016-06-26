package com.vnpt.hddtcustomer.views.activity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;

public class ChargesDetailActivity extends AppCompatActivity {

    private TextView btnToSeeChart, btnToBack, btnToSearch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charges_detail);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        btnToSeeChart = (TextView) findViewById(R.id.btnToSeeChart);
        btnToSeeChart.setTypeface(fontAwesome);
        btnToSeeChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChargesDetailActivity.this, MainActivity.class);
                intent.putExtra("FragmentName", "ChartFragment");
                startActivity(intent);
                finish();
            }
        });
        btnToBack = (TextView) findViewById(R.id.btnToBack);
        btnToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnToSearch = (TextView) findViewById(R.id.btnToSearch);
        btnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChargesDetailActivity.this, MainActivity.class);
                intent.putExtra("FragmentName", "SearchBill");
                startActivity(intent);
                finish();
            }
        });
    }
}
